package com.feature.movie_detail.data

import com.core.util.IoDispatcher
import com.core.util.Resource
import com.data.database.dao.MovieDao
import com.feature.movie_detail.domain.MovieDetailRepository
import com.feature.movie_detail.domain.Poster
import com.feature.movie_detail.domain.toPoster
import com.myapp.network.data_source.PosterRemoteDataSourceImpl
import com.shared.movie.Movie
import com.shared.movie.toMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val posterRemoteDataSource: PosterRemoteDataSourceImpl,
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

    override suspend fun toggleFavorite(movieId: Int, isFavorite: Boolean) {
        withContext(dispatcher) {
            if (isFavorite) {
                movieDao.addToFavorite(movieId)
            } else {
                movieDao.removeFromFavorite(movieId)
            }
        }
    }

    override suspend fun getMovieFromCacheById(movieId: Int): Movie =
        withContext(dispatcher) {
            movieDao.getMovieById(movieId).toMovie()
        }
}