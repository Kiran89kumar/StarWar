package com.star.war.ui.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.star.war.usecase.DataUseCase
import com.star.war.ui.search.SearchItemAdapter
import com.star.war.ui.search.SearchViewModel
import dagger.Lazy
import javax.inject.Inject

class SearchViewModelProviderFactory @Inject constructor(
    private val useCase: Lazy<DataUseCase>,
    private val searchItemAdapter: Lazy<SearchItemAdapter>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        SearchViewModel::class.java -> {
            SearchViewModel(useCase.get(), searchItemAdapter.get()) as T
        }
        else -> error("Invalid View model request")
    }
}