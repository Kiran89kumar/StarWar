package com.star.war.di.module

import com.star.war.di.FragmentScope
import com.star.war.ui.detail.DetailFragment
import com.star.war.ui.detail.di.DetailModule
import com.star.war.ui.search.SearchFragment
import com.star.war.ui.search.di.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentComponentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun provideSearchFragmentComponent(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun provideDetailFragmentComponent(): DetailFragment
}