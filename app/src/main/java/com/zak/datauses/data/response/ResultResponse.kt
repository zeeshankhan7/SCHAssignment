package com.zak.datauses.data.response

class ResultResponse {
    var resource_id: String? = null
    var records: List<QuarterResponse>? = null

    constructor() {}
    constructor(resource_id: String?, records: List<QuarterResponse>?) {
        this.resource_id = resource_id
        this.records = records
    }
}