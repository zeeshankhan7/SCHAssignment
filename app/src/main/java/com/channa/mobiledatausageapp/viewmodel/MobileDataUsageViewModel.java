package com.channa.mobiledatausageapp.viewmodel;

import com.channa.mobiledatausageapp.model.Quarter;
import com.channa.mobiledatausageapp.repository.MobileDataRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MobileDataUsageViewModel extends ViewModel {

    private MobileDataRepository mobileDataRepository;

    public MobileDataUsageViewModel(MobileDataRepository mobileDataRepository) {
        this.mobileDataRepository = mobileDataRepository;
    }

    public LiveData<List<Quarter>> getQuarterList() {
        return mobileDataRepository.getQuarterList();
    }
}
