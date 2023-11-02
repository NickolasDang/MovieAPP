package com.feature.movie_list.data

import coil.network.HttpException
import com.data.database.dao.MovieDao
import com.feature.movie_list.domain.Movie
import com.feature.movie_list.domain.MovieRepository
import com.myapp.network.MovieListRemoteDataSource
import com.myapp.network.di.IoDispatcher
import com.myapp.network.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieListRemoteDataSource: MovieListRemoteDataSource,
    private val movieDao: MovieDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override fun getMovieList(input: String): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())

            val cachedMovieList = movieDao.getAllMovies().map {it.toMovie()}
            emit(Resource.Loading(cachedMovieList))

            try {
                val remoteMovieList = movieListRemoteDataSource.getMovieList(input)
                movieDao.deleteMovieList(cachedMovieList.map { it.toMovieEntity() })
                movieDao.insertMovieList(remoteMovieList.toMovieEntityList())

                val newCachedMovieList = movieDao.getAllMovies().map { it.toMovie() }
                emit(Resource.Success(newCachedMovieList))
            } catch (e: HttpException) {
                emit(Resource.Error(
                    errorMessage = "Something went wrong",
                    data = cachedMovieList
                ))
            } catch (e: IOException) {
                emit(Resource.Error(
                    errorMessage = "Couldn't reach the server, check your internet connection",
                    data = cachedMovieList
                ))
            }
        }.flowOn(dispatcher)

    suspend override fun getMovieListFromCache(): List<Movie> =
        withContext(dispatcher) {
            movieDao.getAllMovies().toMovieList()
        }
}