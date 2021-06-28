package com.zak.datauses.data.model

class Quarter {
    var id: Int? = null
    var usage: Float? = null
    var year: Int? = null
    var quarterName: String? = null
    var usageGrowth: Float? = null

    constructor() {}

    //    public Quarter(Integer id, Float usage, Integer year, String quarterName, Float usageGrowth) {
    //        this.id = id;
    //        this.usage = usage;
    //        this.year = year;
    //        this.quarterName = quarterName;
    //        this.usageGrowth = usageGrowth;
    //    }
    constructor(id: Int?, usage: Float?, year: Int?, quarterName: String?) {
        this.id = id
        this.usage = usage
        this.year = year
        this.quarterName = quarterName
    }
}