package com.channa.mobiledatausageapp.dagger;

import android.app.Application;

import com.channa.mobiledatausageapp.view.MobileDataUsageActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MobileDataUsageActivity mobileDataUsageActivity);

    Application application();
}
