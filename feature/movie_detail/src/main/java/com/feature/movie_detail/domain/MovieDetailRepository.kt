package com.feature.movie_detail.domain

import com.core.util.Resource
import com.shared.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieById(movieId: Int): Flow<Resource<out Movie>>
    fun getPosterByMovieTitle(movieTitle: String): Flow<Resource<Poster>>
}