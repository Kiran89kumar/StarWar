package com.star.war.usecase

import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchResponse
import com.star.war.repo.network.NetworkRepo
import io.reactivex.Single
import javax.inject.Inject

/**
 * DataUseCase is used to get the data either from DB or from Network -
 * Currently its directly fetching from Network.
 */
class DataUseCase @Inject constructor(private val networkRepo: NetworkRepo) {

    fun searchCharacter(searchName: String): Single<SearchResponse>  =
        networkRepo.searchCharacterName(searchName)

    fun getDetails(url: String): Single<DetailResponse> =
        networkRepo.getDetails(url)

    fun getFilmDetails(url: String): Single<FilmResponse> {
        return networkRepo.getFilmDetails(url)
    }
}