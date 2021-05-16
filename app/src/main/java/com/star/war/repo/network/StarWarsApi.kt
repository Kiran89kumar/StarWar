package com.star.war.repo.network

import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

const val BASE_URL = "https://swapi.dev/api/"
const val SEARCH = "people/?"

interface StarWarsApi {

    @GET(SEARCH)
    fun searchCharacterName(@Query("search") searchName: String): Single<SearchResponse>

    @GET
    fun getDetails(@Url url: String): Single<DetailResponse>

    @GET
    fun getFilmDetails(@Url url: String): Single<FilmResponse>
}