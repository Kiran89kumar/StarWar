package com.star.war.di

import com.star.war.StartWarApp
import com.star.war.di.module.ActivityComponentModule
import com.star.war.di.module.ApplicationModule
import com.star.war.repo.network.di.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityComponentModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<StartWarApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<StartWarApp>
}