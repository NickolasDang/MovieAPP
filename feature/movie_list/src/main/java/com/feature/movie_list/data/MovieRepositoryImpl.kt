package com.feature.movie_list.data

import com.feature.movie_list.domain.Movie
import com.feature.movie_list.domain.MovieRepository
import com.myapp.network.MovieListRemoteDataSource
import com.myapp.network.di.IoDispatcher
import com.myapp.network.model.ITunesResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieListRemoteDataSource: MovieListRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getMovieList(input: String): List<Movie> =
        withContext(dispatcher) {
            movieListRemoteDataSource.getMovieList(input).toMovieList()
        }
}