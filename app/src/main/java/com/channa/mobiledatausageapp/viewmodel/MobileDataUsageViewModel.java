package com.channa.mobiledatausageapp.viewmodel;

import com.channa.mobiledatausageapp.data.model.Year;
import com.channa.mobiledatausageapp.repository.MobileDataRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MobileDataUsageViewModel extends ViewModel {

    MobileDataRepository mobileDataRepository;

    public MobileDataUsageViewModel(MobileDataRepository mobileDataRepository) {
        this.mobileDataRepository = mobileDataRepository;
    }

    public LiveData<List<Year>> getYearlyMobileDataUsage() {
        return mobileDataRepository.getYearlyMobileDataUsage();
    }
}
