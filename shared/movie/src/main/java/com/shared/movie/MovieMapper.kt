package com.shared.movie

import com.data.database.entity.MovieEntity
import com.myapp.network.model.ITunesItemResponse
import com.myapp.network.model.ITunesResponse

fun ITunesItemResponse.toMovie() = Movie(
    id = this.trackId,
    title = this.trackName,
    imgUrl = this.artworkUrl100,
    trailerUrl = this.previewUrl,
    isFavorite = false,
    price = this.trackPrice,
    genre = this.primaryGenreName,
    description = this.longDescription
)

fun ITunesResponse.toMovieList(): List<Movie> =
    this.iTunesItemList.map { iTunesItemResponse ->
        iTunesItemResponse.toMovie()
    }

fun ITunesItemResponse.toMovieEntity() = MovieEntity(
    id = this.trackId,
    title = this.trackName,
    imgUrl = this.artworkUrl100,
    trailerUrl = this.previewUrl,
    isFavorite = false,
    price = this.trackPrice,
    genre = this.primaryGenreName,
    description = this.longDescription
)

fun ITunesResponse.toMovieEntityList() =
    this.iTunesItemList.map { iTunesItemResponse ->
        iTunesItemResponse.toMovieEntity()
    }

fun MovieEntity.toMovie() = Movie(
    id = this.id,
    description = this.description,
    genre = this.genre,
    imgUrl = this.imgUrl,
    isFavorite = this.isFavorite,
    price = this.price,
    trailerUrl = this.trailerUrl,
    title = this.title
)

fun List<MovieEntity>.toMovieList(): List<Movie> =
    this.map { movieEntity ->
        movieEntity.toMovie()
    }

fun Movie.toMovieEntity() = MovieEntity(
    id = this.id,
    description = this.description,
    genre = this.genre,
    imgUrl = this.imgUrl,
    isFavorite = this.isFavorite,
    price = this.price,
    trailerUrl = this.trailerUrl,
    title = this.title
)