package com.zak.datauses

import android.app.Application
import com.zak.datauses.di.component.ApplicationComponent
import com.zak.datauses.di.component.DaggerApplicationComponent
import com.zak.datauses.di.module.ApplicationContextModule
import com.zak.datauses.di.module.DataModule

class MyApplication : Application() {
    private var applicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContextModule(ApplicationContextModule(this))
                .dataModule(DataModule())
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return applicationComponent
    }
}