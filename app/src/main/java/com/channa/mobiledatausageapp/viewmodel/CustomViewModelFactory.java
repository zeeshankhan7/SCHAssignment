package com.channa.mobiledatausageapp.viewmodel;

import android.util.Log;

import com.channa.mobiledatausageapp.repository.MobileDataRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private static final String TAG = "CustomViewModelFactory";

    private final MobileDataRepository mobileDataRepository;

    public CustomViewModelFactory(MobileDataRepository mobileDataRepository) {
        this.mobileDataRepository = mobileDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MobileDataUsageViewModel.class)) {
            return (T) new MobileDataUsageViewModel(mobileDataRepository);
        } else {
            Log.e(TAG, "Invalid ViewModel Type");
            throw new IllegalArgumentException("Invalid ViewModel Type");
        }

    }
}