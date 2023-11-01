package com.myapp.network.model

data class ITunesItemResponse(
    val artistName: String = "",
    val artworkUrl100: String = "",
    val contentAdvisoryRating: String = "",
    val country: String = "",
    val currency: String = "",
    val kind: String = "",
    val longDescription: String = "",
    val previewUrl: String = "",
    val primaryGenreName: String = "",
    val releaseDate: String = "",
    val trackPrice: Double = 0.0,
    val trackId: Int = 0,
    val trackName: String = "",
    val trackViewUrl: String = "",
)
