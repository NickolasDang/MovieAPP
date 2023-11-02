package com.feature.movie_list.domain

data class Movie(
    val id: Int,
    val title: String,
    val imgUrl: String,
    val isFavorite: Boolean,
    val trailerUrl: String,
    val price: Double,
    val genre: String,
    val description: String
)
