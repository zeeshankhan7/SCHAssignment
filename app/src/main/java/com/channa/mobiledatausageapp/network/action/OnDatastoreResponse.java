package com.channa.mobiledatausageapp.network.action;

import com.channa.mobiledatausageapp.data.response.DatastoreResponse;

public interface OnDatastoreResponse extends OnErrorResponse {
    void onSuccessDatastoreResponse(DatastoreResponse datastoreResponse);
}
