package com.channa.mobiledatausageapp.repository;

import com.channa.mobiledatausageapp.model.Quarter;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class MobileDataRepository {

    private List<Quarter> quarterList = new ArrayList<>();

    public MutableLiveData<List<Quarter>> getQuarterList() {
        setQuarterList();
        MutableLiveData<List<Quarter>> mutableQuarterList = new MutableLiveData<>();
        mutableQuarterList.setValue(quarterList);
        return mutableQuarterList;
    }

    private void setQuarterList() {
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
        quarterList.add(new Quarter(1, 2.22f, 2008, "Q1", 22f));
    }

}
