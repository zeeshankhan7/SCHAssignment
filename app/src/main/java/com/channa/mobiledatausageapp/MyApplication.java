package com.channa.mobiledatausageapp;

import android.app.Application;

import com.channa.mobiledatausageapp.di.component.ApplicationComponent;
import com.channa.mobiledatausageapp.di.component.DaggerApplicationComponent;
import com.channa.mobiledatausageapp.di.module.ApplicationContextModule;
import com.channa.mobiledatausageapp.di.module.DataModule;


public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .dataModule(new DataModule())
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

