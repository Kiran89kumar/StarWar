package com.star.war.ui.search.di

import androidx.lifecycle.ViewModelProvider
import com.star.war.di.FragmentScope
import com.star.war.ui.search.SearchFragment
import com.star.war.ui.search.SearchItemAdapter
import com.star.war.ui.search.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @FragmentScope
    fun provideAdapter(fragment: SearchFragment): SearchItemAdapter = SearchItemAdapter(fragment)

    @Provides
    @FragmentScope
    fun getViewModel(
        fragment: SearchFragment,
        searchViewModelProviderFactory: SearchViewModelProviderFactory
    ): SearchViewModel = ViewModelProvider(fragment, searchViewModelProviderFactory).get(
        SearchViewModel::class.java
    )
}