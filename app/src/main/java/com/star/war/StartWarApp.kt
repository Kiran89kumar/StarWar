package com.star.war

import com.star.war.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

class StartWarApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler {
            //Need to log this.
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = injector

    private val injector by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}