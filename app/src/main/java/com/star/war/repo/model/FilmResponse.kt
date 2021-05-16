package com.star.war.repo.model

data class FilmResponse(
    val title: String,
    val opening_crawl: String,
    val release_date: String,
    val producer: String,
    val director: String,
    val created: String,
    val edited: String,
    val episode_id: Int,
    val isEmpty: Boolean = false
)
