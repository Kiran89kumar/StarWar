package com.star.war.ui.detail

import androidx.databinding.ObservableField
import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.repo.model.SearchCharacter
import com.star.war.repo.network.exceptions.ServerException
import com.star.war.ui.base.BaseViewModel
import com.star.war.ui.helper.ActionableLiveData
import com.star.war.ui.helper.BaseLiveData
import com.star.war.ui.helper.ObservableData
import com.star.war.usecase.DataUseCase
import com.star.war.utils.async
import com.stepango.rxdatabindings.ObservableString
import io.reactivex.Single
import java.util.*
import kotlin.math.roundToInt

class DetailViewModel(
    private val useCase: DataUseCase,
    private val filmItemAdapter: FilmItemAdapter
): BaseViewModel() {
    val title = ObservableString()
    val birthYr = ObservableString()
    val height = ObservableString()
    val planet = ObservableString()
    val population = ObservableString()

    lateinit var searchCharacter: SearchCharacter

    val filmAdapterField = ObservableField(filmItemAdapter)

    private val detailLiveData = disposableLiveData {
        ActionableLiveData<DetailResponse>()
    }

    private val filmsLiveData = disposableLiveData {
        ActionableLiveData<List<FilmResponse>>()
    }

    fun init(data: SearchCharacter) {
        title.set(data.name)
        birthYr.set(data.birth_year)
        height.set(getHeight(data.height))
        searchCharacter = data
    }

    fun getDetails(): BaseLiveData<ObservableData<DetailResponse, Throwable>> =
        detailLiveData.apply {
            actionBlock = {
                useCase.getDetails(searchCharacter.homeworld)
                    .async()
                    .doOnSuccess {
                        planet.set(it.name)
                        population.set(it.population)
                    }
            }
        }

    fun getFilms(): BaseLiveData<ObservableData<List<FilmResponse>, Throwable>> =
        filmsLiveData.apply {
            actionBlock = {
                if(searchCharacter.films.isEmpty()) {
                    Single.just(emptyList())
                } else {
                    fetchFilms()
                }
            }
        }

    private fun fetchFilms(): Single<List<FilmResponse>> =
        Single.just(searchCharacter.films)
            .flatMap {
                val filmResponses: MutableList<Single<*>> = arrayListOf()
                it.forEach { filmUrl ->
                    filmResponses.add(
                        useCase.getFilmDetails(filmUrl)
                            /**
                             * Returning the Empty Film Response so that execution should not break in middle.
                             */
                            .onErrorReturn { getEmptyFilmResponse() }
                    )
                }
                Single.zip(filmResponses, Arrays::asList)
            }.map { it ->
                /**
                 * Filtering the Empty Film Response from the list.
                 */
                it.filterIsInstance<FilmResponse>()
                    .filter { !it.isEmpty }
            }
            .async()
            .doOnSuccess {
                filmItemAdapter.setItems(it)
            }


    private fun getHeight(height: String): String {
        val feet = height.toFloat() / 30.48
        val inch = height.toFloat() / 2.54
        return height.plus(" cm | ")
            .plus(feet.roundToInt().toString()).plus(" ft | ")
            .plus(inch.roundToInt().toString()).plus(" in")
    }

    private fun getEmptyFilmResponse() =
        FilmResponse("", "", "", "", "", "", "", 0, true)
}