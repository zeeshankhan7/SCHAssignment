package com.channa.mobiledatausageapp.di.module;

import com.channa.mobiledatausageapp.network.APIClient;
import com.channa.mobiledatausageapp.repository.MobileDataRepository;
import com.channa.mobiledatausageapp.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    MobileDataRepository provideMobileDataRepository(APIClient apiClient) {
        return new MobileDataRepository(apiClient);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(MobileDataRepository mobileDataRepository) {
        return new CustomViewModelFactory(mobileDataRepository);
    }

}
