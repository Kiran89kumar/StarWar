package com.star.war.ui.search

import android.view.View
import android.view.View.GONE
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.star.war.usecase.DataUseCase
import com.star.war.repo.model.SearchResponse
import com.star.war.ui.base.BaseViewModel
import com.star.war.ui.helper.ActionableLiveData
import com.star.war.ui.helper.BaseLiveData
import com.star.war.ui.helper.ObservableData
import com.star.war.utils.async

class SearchViewModel(
    private val useCase: DataUseCase,
    val searchItemAdapter: SearchItemAdapter
): BaseViewModel() {

    val searchAdapterField = ObservableField(searchItemAdapter)
    val noDataFoundVisibility = ObservableInt(GONE)
    var searchQuery: String? = null

    val searchLiveData = disposableLiveData {
        ActionableLiveData<SearchResponse>()
    }

    fun searchCharacter(searchName: String): BaseLiveData<ObservableData<SearchResponse, Throwable>> =
        searchLiveData.apply {
            actionBlock = {
                searchQuery = searchName
                useCase.searchCharacter(searchName)
                    .async()
                    .doOnSuccess {
                        noDataFoundVisibility.set(if(it.results.isEmpty()) View.VISIBLE else View.GONE)
                        searchItemAdapter.setItems(it.results)
                    }
            }
        }
}