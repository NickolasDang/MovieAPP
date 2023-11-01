package com.feature.movie_list.data

import com.feature.movie_list.domain.Movie
import com.myapp.network.model.ITunesItemResponse
import com.myapp.network.model.ITunesResponse

fun ITunesItemResponse.toMovie() = Movie(
    title = this.trackName,
    imgUrl = this.artworkUrl100,
    trailerUrl = this.previewUrl,
    price = this.trackPrice,
    genre = this.primaryGenreName,
    description = this.longDescription
)

fun ITunesResponse.toMovieList(): List<Movie> =
    this.iTunesItemList.map {iTunesItemResponse ->
        iTunesItemResponse.toMovie()
    }