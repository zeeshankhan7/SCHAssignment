package com.channa.mobiledatausageapp;

import android.app.Application;

import com.channa.mobiledatausageapp.dagger.ApplicationComponent;
import com.channa.mobiledatausageapp.dagger.ApplicationModule;
import com.channa.mobiledatausageapp.dagger.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                // .roomModule(new RoomModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

