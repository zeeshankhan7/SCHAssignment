package com.zak.datauses.di.component

import android.content.Context
import com.zak.datauses.di.module.ApplicationContextModule
import com.zak.datauses.di.module.DataModule
import com.zak.datauses.network.APIClient
import com.zak.datauses.repository.MobileDataRepository
import com.zak.datauses.views.MobileDataUsageActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, DataModule::class])
interface ApplicationComponent {
    fun inject(mobileDataUsageActivity: MobileDataUsageActivity?)
    fun inject(mobileDataRepository: MobileDataRepository?)
    fun apiClient(): APIClient?
    fun context(): Context?
}