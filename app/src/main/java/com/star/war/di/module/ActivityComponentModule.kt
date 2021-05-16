package com.star.war.di.module

import com.star.war.ui.HomeScreen
import com.star.war.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentComponentModule::class])
abstract class ActivityComponentModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideHomeScreenComponent(): HomeScreen

}