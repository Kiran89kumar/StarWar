package com.star.war.repo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<SearchCharacter>
)

@Parcelize
data class SearchCharacter(
    val birth_year: String,
    val films: List<String>,
    val gender: String,
    val height: String,
    val homeworld: String,
    val name: String,
    val species: List<String>
): Parcelable