package com.star.war.ui.search

import android.view.View
import com.star.war.repo.model.SearchCharacter
import com.star.war.repo.model.SearchResponse
import com.star.war.repo.network.exceptions.ServerException
import com.star.war.ui.base.BaseViewModelTest
import com.star.war.ui.helper.OnItemClickListener
import com.star.war.usecase.DataUseCase
import com.star.war.utils.observedError
import com.star.war.utils.observedValue
import com.star.war.utils.testObserve
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SearchViewModelTest:
    BaseViewModelTest<SearchViewModel, DataUseCase>() {

    @MockK
    lateinit var onItemClickListener : OnItemClickListener<SearchCharacter>

    override val viewModel: SearchViewModel = SearchViewModel(useCase, SearchItemAdapter(onItemClickListener))

    @Test
    fun search_no_data_pass_test() {
        val response = mockk<SearchResponse>(relaxed = true)
        every { response.results } returns emptyList()
        every { useCase.searchCharacter(any()) } returns Single.just(response)
        val result = viewModel.searchCharacter("demo").testObserve()
        assertThat(viewModel.searchQuery).isEqualTo("demo")
        assertThat(result.observedValue).isEqualTo(response)
        assertThat(viewModel.noDataFoundVisibility.get()).isEqualTo(View.VISIBLE)
        assertThat(viewModel.searchItemAdapter.itemCount).isEqualTo(0)
    }

    @Test
    fun search_with_data_pass_test() {
        val searchCharacter = SearchCharacter("1990", emptyList(), "male", "200", "URL", "NAME", emptyList())
        val response = SearchResponse(5, 10, 10, listOf(searchCharacter))
        every { useCase.searchCharacter(any()) } returns Single.just(response)
        val result = viewModel.searchCharacter("NAME").testObserve()
        assertThat(viewModel.searchQuery).isEqualTo("NAME")
        assertThat(result.observedValue).isEqualTo(response)
        assertThat(result.observedError).isEqualTo(null)
        assertThat(viewModel.noDataFoundVisibility.get()).isEqualTo(View.GONE)
        assertThat(viewModel.searchItemAdapter.itemCount).isEqualTo(1)
    }

    @Test
    fun search_data_fail_test() {
        val errorResponse = mockk<ServerException>(relaxed = true)
        every { useCase.searchCharacter(any()) } returns Single.error(errorResponse)
        val result = viewModel.searchCharacter("NAME").testObserve()
        assertThat(result.observedError).isEqualTo(errorResponse)
    }
}