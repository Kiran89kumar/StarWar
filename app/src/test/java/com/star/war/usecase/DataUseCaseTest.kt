package com.star.war.usecase

import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchCharacter
import com.star.war.repo.model.SearchResponse
import com.star.war.repo.network.NetworkRepo
import com.star.war.repo.network.exceptions.ServerException
import com.star.war.ui.helper.BaseTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class DataUseCaseTest: BaseTest() {

    private val query = "QUERY"

    private lateinit var dataUseCase: DataUseCase

    @MockK
    private lateinit var networkRepo: NetworkRepo

    override fun setUp() {
        super.setUp()
        dataUseCase = DataUseCase(networkRepo)
    }

    @Test
    fun searchCharacterTest() {
        val searchCharacter = SearchCharacter("1990", emptyList(), "male", "200", "URL", "NAME", emptyList())
        val response = SearchResponse(5, 10, 10, listOf(searchCharacter))
        every { networkRepo.searchCharacterName(any()) } returns Single.just(response)
        val testRes = dataUseCase.searchCharacter(query)
        testRes.test().assertNoErrors()
        testRes.test().assertValue(response)
        testRes.test().assertComplete()
    }

    @Test
    fun getDetailsTest() {
        val response = mockk<DetailResponse>()
        every { networkRepo.getDetails(any()) } returns Single.just(response)
        val testRes = dataUseCase.getDetails(query)
        testRes.test().assertNoErrors()
        testRes.test().assertValue(response)
        testRes.test().assertComplete()
    }

    @Test
    fun getFilmDetailsTest() {
        val response = mockk<FilmResponse>()
        every { networkRepo.getFilmDetails(any()) } returns Single.just(response)
        val testRes = dataUseCase.getFilmDetails(query)
        testRes.test().assertNoErrors()
        testRes.test().assertValue(response)
        testRes.test().assertComplete()
    }

    @Test
    fun getFilmDetailsFailTest() {
        val errorResponse = mockk<ServerException>(relaxed = true)
        every { networkRepo.getFilmDetails(any()) } returns Single.error(errorResponse)
        val testRes = dataUseCase.getFilmDetails(query)
        testRes.test().assertError(errorResponse)
        testRes.test().assertNoValues()
        testRes.test().assertNotComplete()
    }
}