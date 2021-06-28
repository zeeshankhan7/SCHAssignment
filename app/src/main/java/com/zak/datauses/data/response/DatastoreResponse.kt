package com.zak.datauses.data.response

class DatastoreResponse {
    var help: String? = null
    var success: Boolean? = null
    var result: ResultResponse? = null

    constructor() {}
    constructor(help: String?, success: Boolean?, result: ResultResponse?) {
        this.help = help
        this.success = success
        this.result = result
    }
}