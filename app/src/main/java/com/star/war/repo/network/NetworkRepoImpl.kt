package com.star.war.repo.network

import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchResponse
import io.reactivex.Single

class NetworkRepoImpl(
    private val api: StarWarsApi
): NetworkRepo {

    override fun searchCharacterName(searchName: String): Single<SearchResponse> =
        api.searchCharacterName(searchName)

    override fun getDetails(url: String): Single<DetailResponse> =
        api.getDetails(url)

    override fun getFilmDetails(url: String): Single<FilmResponse> =
        api.getFilmDetails(url)
}