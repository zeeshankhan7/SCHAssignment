package com.zak.datauses.network.action

import com.zak.datauses.data.response.DatastoreResponse

interface OnDatastoreResponse : OnErrorResponse {
    fun onSuccessDatastoreResponse(datastoreResponse: DatastoreResponse?)
}