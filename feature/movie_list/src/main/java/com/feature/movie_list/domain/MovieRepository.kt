package com.feature.movie_list.domain

import com.myapp.network.model.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(input: String): Flow<Resource<List<Movie>>>

    suspend fun getMovieListFromCache(): List<Movie>
}