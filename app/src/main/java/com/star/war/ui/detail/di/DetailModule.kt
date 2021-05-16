package com.star.war.ui.detail.di

import androidx.lifecycle.ViewModelProvider
import com.star.war.di.FragmentScope
import com.star.war.ui.detail.DetailFragment
import com.star.war.ui.detail.DetailViewModel
import com.star.war.ui.detail.FilmItemAdapter
import com.star.war.ui.search.SearchFragment
import com.star.war.ui.search.SearchItemAdapter
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    @FragmentScope
    fun provideAdapter(): FilmItemAdapter = FilmItemAdapter()

    @Provides
    @FragmentScope
    fun getViewModel(
        fragment: DetailFragment,
        detailViewModelProviderFactory: DetailViewModelProviderFactory
    ): DetailViewModel = ViewModelProvider(fragment, detailViewModelProviderFactory).get(
        DetailViewModel::class.java
    )
}