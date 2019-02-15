package com.channa.mobiledatausageapp.dagger;

import android.app.Application;

import com.channa.mobiledatausageapp.repository.MobileDataRepository;
import com.channa.mobiledatausageapp.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    MobileDataRepository provideQuoteRepository() {
        return new MobileDataRepository();
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(MobileDataRepository mobileDataRepository) {
        return new CustomViewModelFactory(mobileDataRepository);
    }

}