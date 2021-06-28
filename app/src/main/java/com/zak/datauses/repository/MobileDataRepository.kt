package com.zak.datauses.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zak.datauses.data.model.Quarter
import com.zak.datauses.data.model.Year
import com.zak.datauses.data.model.YearListWrapper
import com.zak.datauses.data.response.DatastoreResponse
import com.zak.datauses.network.APIClient
import com.zak.datauses.network.action.OnDatastoreResponse
import java.util.*
import javax.inject.Inject

class MobileDataRepository @Inject constructor(var apiClient: APIClient) {
    private val yearList: MutableList<Year> = ArrayList()
    private val mutableYearListWrapper = MutableLiveData<YearListWrapper>()// add the missed iteration's value

    // last element of this doesn't add to the years array as the loop stops
    // this only executes within the given year range
    val yearlyMobileDataUsage: LiveData<YearListWrapper>
        get() {
            apiClient.getMobileDataUsage(object : OnDatastoreResponse {
                override fun onErrorResponse(error: String?) {
                    mutableYearListWrapper.value = YearListWrapper(error)
                }

                override fun onSuccessDatastoreResponse(datastoreResponse: DatastoreResponse?) {
                    var quarterList: MutableList<Quarter?> = ArrayList()
                    var yearIndex = 2008
                    val yearEndIndex = 2018
                    for (i in datastoreResponse!!.result!!.records!!.indices) {
                        val q = datastoreResponse.result!!.records!![i]
                        Log.d(TAG, "Quarter: " + q._id + " : " + q.quarter)
                        val quarterInfo = q.quarter!!.split("-").toTypedArray()
                        val year = quarterInfo[0].toInt()
                        val quarterName = quarterInfo[1]

                        // this only executes within the given year range
                        if (year >= 2008 && year <= yearEndIndex) {
                            if (yearIndex == year) {
                                quarterList.add(Quarter(q._id, q.volume_of_mobile_data, year, quarterName))
                            } else {
                                yearList.add(Year(yearIndex, quarterList))
                                quarterList = ArrayList()
                                yearIndex++

                                // add the missed iteration's value
                                quarterList.add(Quarter(q._id, q.volume_of_mobile_data, year, quarterName))
                            }

                            // last element of this doesn't add to the years array as the loop stops
                            if (i == datastoreResponse.result!!.records!!.size - 1) {
                                yearList.add(Year(year, quarterList))
                            }
                        }
                    }
                    mutableYearListWrapper.value = YearListWrapper(yearList)
                }
            })
            return mutableYearListWrapper
        }

    companion object {
        private const val TAG = "MobileDataRepository"
    }
}