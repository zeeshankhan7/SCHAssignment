package com.channa.mobiledatausageapp.di.component;

import android.content.Context;

import com.channa.mobiledatausageapp.di.module.ApplicationContextModule;
import com.channa.mobiledatausageapp.di.module.DataModule;
import com.channa.mobiledatausageapp.network.APIClient;
import com.channa.mobiledatausageapp.repository.MobileDataRepository;
import com.channa.mobiledatausageapp.view.MobileDataUsageActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationContextModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MobileDataUsageActivity mobileDataUsageActivity);

    void inject(MobileDataRepository mobileDataRepository);

    APIClient apiClient();

    Context context();

}
