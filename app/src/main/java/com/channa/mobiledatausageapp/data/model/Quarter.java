package com.channa.mobiledatausageapp.data.model;

public class Quarter {
    private Integer id;
    private Float usage;
    private Integer year;
    private String quarterName;
    private Float usageGrowth;

    public Quarter() {
    }

//    public Quarter(Integer id, Float usage, Integer year, String quarterName, Float usageGrowth) {
//        this.id = id;
//        this.usage = usage;
//        this.year = year;
//        this.quarterName = quarterName;
//        this.usageGrowth = usageGrowth;
//    }


    public Quarter(Integer id, Float usage, Integer year, String quarterName) {
        this.id = id;
        this.usage = usage;
        this.year = year;
        this.quarterName = quarterName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getUsage() {
        return usage;
    }

    public void setUsage(Float usage) {
        this.usage = usage;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    public Float getUsageGrowth() {
        return usageGrowth;
    }

    public void setUsageGrowth(Float usageGrowth) {
        this.usageGrowth = usageGrowth;
    }
}
