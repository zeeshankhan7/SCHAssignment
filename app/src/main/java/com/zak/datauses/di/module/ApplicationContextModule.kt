package com.zak.datauses.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private val context: Context) {
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }
}