package com.star.war.di.module

import android.content.Context
import com.star.war.StartWarApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: StartWarApp): Context = application.applicationContext
}