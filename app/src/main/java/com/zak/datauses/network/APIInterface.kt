package com.zak.datauses.network

import com.zak.datauses.data.response.DatastoreResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    // get mobile data usage
    @GET("action/datastore_search")
    fun getMobileDataUsage(@Query("resource_id") resourceId: String?, @Query("limit") limit: Int?): Single<DatastoreResponse?>?
}