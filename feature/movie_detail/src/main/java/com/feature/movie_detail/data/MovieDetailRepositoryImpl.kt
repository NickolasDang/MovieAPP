package com.feature.movie_detail.data

import android.util.Log
import com.core.util.IoDispatcher
import com.core.util.Resource
import com.data.database.dao.MovieDao
import com.feature.movie_detail.domain.MovieDetailRepository
import com.feature.movie_detail.domain.Poster
import com.feature.movie_detail.domain.toPoster
import com.myapp.network.api.OmdbApiService
import com.myapp.network.data_source.PosterRemoteDataSource
import com.shared.movie.Movie
import com.shared.movie.toMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val posterRemoteDataSource: PosterRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieDetailRepository {

    override fun getMovieById(movieId: Int): Flow<Resource<out Movie>> =
        flow {
            emit(Resource.Loading())
            try {
                val movie = movieDao.getMovieById(movieId).toMovie()
                emit(Resource.Success(data = movie))
            } catch (e: Exception) {
                emit(Resource.Error(
                    errorMessage = e.localizedMessage ?: "Something went wrong",
                    data = null
                ))
            }
        }.flowOn(dispatcher)

    override fun getPosterByMovieTitle(movieTitle: String): Flow<Resource<Poster>> =
        flow<Resource<Poster>> {
            emit(Resource.Loading())
            try {
                val poster = posterRemoteDataSource.getPoster(movieTitle).toPoster()
                emit(Resource.Success(data = poster))
            } catch (e: Exception) {
                emit(Resource.Error(
                    errorMessage = e.localizedMessage ?: "Something went wrong",
                    data = null
                ))
            }
        }.flowOn(dispatcher)
}