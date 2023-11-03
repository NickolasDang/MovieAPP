package com.myapp.network.model

import com.squareup.moshi.Json

data class PosterResponse(
    @Json(name = "Poster")
    val posterUrl: String,
)