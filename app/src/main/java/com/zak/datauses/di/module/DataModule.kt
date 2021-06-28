package com.zak.datauses.di.module

import androidx.lifecycle.ViewModelProvider
import com.zak.datauses.network.APIClient
import com.zak.datauses.repository.MobileDataRepository
import com.zak.datauses.viewmodel.ViewModelFactoryMobile
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideMobileDataRepository(apiClient: APIClient?): MobileDataRepository {
        return MobileDataRepository(apiClient!!)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(mobileDataRepository: MobileDataRepository?): ViewModelProvider.Factory {
        return ViewModelFactoryMobile(mobileDataRepository!!)
    }
}