package com.star.war.repo.network

import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchResponse
import io.reactivex.Single

interface NetworkRepo {
    fun searchCharacterName(searchName: String): Single<SearchResponse>
    fun getDetails(url: String): Single<DetailResponse>
    fun getFilmDetails(url: String): Single<FilmResponse>
}