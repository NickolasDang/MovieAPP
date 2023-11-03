package com.feature.movie_detail.domain

import com.myapp.network.model.PosterResponse

data class Poster(
    val posterUrl: String
)

fun PosterResponse.toPoster() = Poster(this.posterUrl)
