package com.star.war.ui.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.star.war.usecase.DataUseCase
import com.star.war.ui.detail.DetailViewModel
import com.star.war.ui.detail.FilmItemAdapter
import dagger.Lazy
import javax.inject.Inject

class DetailViewModelProviderFactory @Inject constructor(
    private val useCase: Lazy<DataUseCase>,
    private val filmItemAdapter: Lazy<FilmItemAdapter>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        DetailViewModel::class.java -> {
            DetailViewModel(useCase.get(), filmItemAdapter.get()) as T
        }
        else -> error("Invalid View model request")
    }
}