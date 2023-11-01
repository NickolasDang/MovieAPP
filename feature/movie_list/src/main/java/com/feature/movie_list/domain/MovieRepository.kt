package com.feature.movie_list.domain

import com.myapp.network.model.ITunesResponse

interface MovieRepository {

    suspend fun getMovieList(input: String): List<Movie>
}