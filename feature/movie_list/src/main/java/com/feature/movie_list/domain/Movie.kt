package com.feature.movie_list.domain

data class Movie(
    val title: String,
    val imgUrl: String,
    val trailerUrl: String,
    val price: Double,
    val genre: String,
    val description: String
)
