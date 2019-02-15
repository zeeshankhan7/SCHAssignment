package com.channa.mobiledatausageapp.model;

public class Quarter {
    private int id;
    private float usage;
    private int year;
    private String quarterName;
    private float usageGrowth;

    public Quarter() {
    }

    public Quarter(int id, float usage, int year, String quarterName, float usageGrowth) {
        this.id = id;
        this.usage = usage;
        this.year = year;
        this.quarterName = quarterName;
        this.usageGrowth = usageGrowth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getUsage() {
        return usage;
    }

    public void setUsage(float usage) {
        this.usage = usage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    public float getUsageGrowth() {
        return usageGrowth;
    }

    public void setUsageGrowth(float usageGrowth) {
        this.usageGrowth = usageGrowth;
    }
}
