package com.channa.mobiledatausageapp.network;

import com.channa.mobiledatausageapp.data.response.DatastoreResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    // get mobile data usage
    @GET("action/datastore_search")
    Single<DatastoreResponse> getMobileDataUsage(@Query("resource_id") String resourceId, @Query("limit") Integer limit);

}


