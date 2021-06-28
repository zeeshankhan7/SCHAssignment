package com.zak.datauses.data.response

class QuarterResponse {
    var volume_of_mobile_data = 0f
    var quarter: String? = null
    var _id = 0

    constructor() {}
    constructor(volume_of_mobile_data: Float, quarter: String?, _id: Int) {
        this.volume_of_mobile_data = volume_of_mobile_data
        this.quarter = quarter
        this._id = _id
    }

    fun getid(): Int {
        return _id
    }

    fun setid(_id: Int) {
        this._id = _id
    }
}