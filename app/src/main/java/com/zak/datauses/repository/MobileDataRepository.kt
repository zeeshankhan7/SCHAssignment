package com.channa.mobiledatausageapp.repository;

import android.util.Log;

import com.channa.mobiledatausageapp.data.model.Quarter;
import com.channa.mobiledatausageapp.data.model.Year;
import com.channa.mobiledatausageapp.data.model.YearListWrapper;
import com.channa.mobiledatausageapp.data.response.DatastoreResponse;
import com.channa.mobiledatausageapp.data.response.QuarterResponse;
import com.channa.mobiledatausageapp.network.APIClient;
import com.channa.mobiledatausageapp.network.action.OnDatastoreResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MobileDataRepository {

    private static final String TAG = "MobileDataRepository";

    APIClient apiClient;
    private List<Year> yearList = new ArrayList<>();


    private MutableLiveData<YearListWrapper> mutableYearListWrapper = new MutableLiveData<>();

    @Inject
    public MobileDataRepository(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    public LiveData<YearListWrapper> getYearlyMobileDataUsage() {
        apiClient.getMobileDataUsage(new OnDatastoreResponse() {

            @Override
            public void onErrorResponse(String error) {
                mutableYearListWrapper.setValue(new YearListWrapper(error));
            }

            @Override
            public void onSuccessDatastoreResponse(DatastoreResponse datastoreResponse) {

                List<Quarter> quarterList = new ArrayList<>();

                int yearIndex = 2008;
                int yearEndIndex = 2018;

                for (int i = 0; i < datastoreResponse.getResult().getRecords().size(); i++) {

                    QuarterResponse q = datastoreResponse.getResult().getRecords().get(i);

                    Log.d(TAG, "Quarter: " + q.get_id() + " : " + q.getQuarter());

                    String quarterInfo[] = q.getQuarter().split("-");
                    Integer year = Integer.parseInt(quarterInfo[0]);
                    String quarterName = quarterInfo[1];

                    // this only executes within the given year range
                    if (year >= 2008 && year <= yearEndIndex) {

                        if (yearIndex == year) {
                            quarterList.add(new Quarter(q.get_id(), q.getVolume_of_mobile_data(), year, quarterName));
                        } else {
                            yearList.add(new Year(yearIndex, quarterList));

                            quarterList = new ArrayList<>();

                            yearIndex++;

                            // add the missed iteration's value
                            quarterList.add(new Quarter(q.get_id(), q.getVolume_of_mobile_data(), year, quarterName));
                        }

                        // last element of this doesn't add to the years array as the loop stops
                        if (i == datastoreResponse.getResult().getRecords().size() - 1) {
                            yearList.add(new Year(year, quarterList));
                        }

                    }

                }
                mutableYearListWrapper.setValue(new YearListWrapper(yearList));
            }
        });

        return mutableYearListWrapper;

    }

}
