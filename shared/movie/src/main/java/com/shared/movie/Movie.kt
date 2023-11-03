package com.shared.movie

data class Movie(
    val id: Int = -1,
    val title: String = "",
    val imgUrl: String = "",
    val isFavorite: Boolean = false,
    val trailerUrl: String = "",
    val price: Double = 0.0,
    val genre: String = "",
    val description: String = ""
)
