package com.star.war.ui.detail

import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchCharacter
import com.star.war.repo.network.exceptions.ServerException
import com.star.war.ui.base.BaseViewModelTest
import com.star.war.usecase.DataUseCase
import com.star.war.utils.observedError
import com.star.war.utils.observedValue
import com.star.war.utils.testObserve
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class DetailViewModelTest :
    BaseViewModelTest<DetailViewModel, DataUseCase>()  {

    override val viewModel: DetailViewModel = DetailViewModel(useCase, FilmItemAdapter())

    private lateinit var searchCharacter : SearchCharacter

    @Before
    fun setup(){
        super.setUp()
        searchCharacter =  getMockSearchCharacter()
        viewModel.init(searchCharacter)
    }

    @Test
    fun initTest() {
        assertThat(viewModel.title.get()).isEqualTo(searchCharacter.name)
        assertThat(viewModel.birthYr.get()).isEqualTo(searchCharacter.birth_year)
        assertThat(viewModel.height.get()).isEqualTo("200 cm | 7 ft | 79 in")
        assertThat(viewModel.searchCharacter).isEqualTo(searchCharacter)
    }

    @Test
    fun details_pass_test() {
        val response = mockk<DetailResponse>(relaxed = true)
        every { response.name } returns "Name"
        every { response.population } returns "100000"
        every { useCase.getDetails(any()) } returns Single.just(response)
        val result = viewModel.getDetails().testObserve()
        assertThat(result.observedValue).isEqualTo(response)
        assertThat(viewModel.planet.get()).isEqualTo("Name")
        assertThat(viewModel.population.get()).isEqualTo("100000")
    }

    @Test
    fun details_fail_test() {
        val urlSlot = slot<String>()
        val response = mockk<ServerException>(relaxed = true)
        every { useCase.getDetails(capture(urlSlot)) } returns Single.error(response)
        val result = viewModel.getDetails().testObserve()
        assertThat(result.observedError).isEqualTo(response)
        assertThat(urlSlot.captured).isEqualTo("URL")
    }

    @Test
    fun get_films_empty_films_test() {
        val response = mockk<FilmResponse>(relaxed = true)
        every { useCase.getFilmDetails(any()) } returns Single.just(response)
        val result = viewModel.getFilms().testObserve()
        assertThat(result.observedError).isEqualTo(null)
        assertThat(result.observedValue).isEqualTo(emptyList<Any>())
    }

    @Test
    fun get_films_pass_test() {
        viewModel.searchCharacter = getMockSearchCharacter(listOf("url1", "url2"))
        val response = mockk<FilmResponse>(relaxed = true)
        every { useCase.getFilmDetails(any()) } returns Single.just(response)
        val result = viewModel.getFilms().testObserve()
        assertThat(result.observedValue).isEqualTo(listOf(response, response))
    }

    /**
     * Testing when one of the Film API fails:
     * Expected: Need to return the Passed/Successful API call to UI
     */
    @Test
    fun get_films_complex_case_test() {
        viewModel.searchCharacter = getMockSearchCharacter(listOf("url1", "url2", "url3"))
        val errorResponse = mockk<ServerException>(relaxed = true)
        val response = mockk<FilmResponse>(relaxed = true)
        every { useCase.getFilmDetails("url1") } returns Single.just(response)
        every { useCase.getFilmDetails("url2") } returns Single.error(errorResponse)
        every { useCase.getFilmDetails("url3") } returns Single.just(response)
        val result = viewModel.getFilms().testObserve()
        assertThat(result.observedValue).isEqualTo(listOf(response, response))
    }

    private fun getMockSearchCharacter(films: List<String> = emptyList()) =
        SearchCharacter("1990", films, "male", "200", "URL", "NAME", emptyList())
}