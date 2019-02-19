package com.channa.mobiledatausageapp.data.model;

import java.util.List;

public class YearListWrapper {

    private List<Year> yearList;
    private Throwable throwable;

    public YearListWrapper(Throwable throwable) {
        this.throwable = throwable;
    }

    public YearListWrapper(List<Year> yearList) {
        this.yearList = yearList;
    }

    public List<Year> getYearList() {
        return yearList;
    }

    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
