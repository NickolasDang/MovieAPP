package com.feature.movie_list.data

import com.core.util.Resource
import com.data.database.dao.MovieDao
import com.feature.movie_list.MainDispatcherRule
import com.myapp.network.data_source.MovieListRemoteDataSource
import com.myapp.network.model.ITunesResponse
import com.shared.movie.Movie
import com.shared.movie.toItunesItemResponseList
import com.shared.movie.toMovieEntityList
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MovieRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var movieListRemoteDataSource: MovieListRemoteDataSource

    @Mock
    private lateinit var movieDao: MovieDao

    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = MovieRepositoryImpl(movieListRemoteDataSource, movieDao, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `getMovieList should return success with data`() = runTest {
        val input = "search_input"
        val expectedMovieList = listOf(Movie(id = 1, title = "Movie 1"))
        val remoteMovieList = ITunesResponse(
            resultCount = 1,
            iTunesItemList = expectedMovieList.toItunesItemResponseList()
        )
        `when`(movieListRemoteDataSource .getMovieList(input)).thenReturn(remoteMovieList)
        `when`(movieDao.getAllMovies()).thenReturn(expectedMovieList.toMovieEntityList())


        val result = repository.getMovieList(input).toList()


        assertTrue(result[2] is Resource.Success)
        assertEquals(expectedMovieList, (result[2] as Resource.Success).data)
    }


    @Test
    fun `getMovieList should return error when remote data fetch fails`() = runTest {
        val input = "search_input"
        `when`(movieListRemoteDataSource.getMovieList(input)).thenThrow(NullPointerException())

        val result = repository.getMovieList(input).toList()

        assertTrue(result.last() is Resource.Error)
    }


    @Test
    fun `getMovieListFromCache should return cached movies`() = runTest {
        val cachedMovies = listOf(Movie(id = 1, title = "Movie 1"))
        `when`(movieDao.getAllMovies()).thenReturn(cachedMovies.toMovieEntityList())

        val result = repository.getMovieListFromCache()

        assertEquals(cachedMovies, result)
    }


    @Test
    fun `toggleFavorite should call addToFavorite when isFavorite is true`() = runTest {
        val movieId = 1
        val isFavorite = true

        repository.toggleFavorite(movieId, isFavorite)

        verify(movieDao).addToFavorite(movieId)
        verify(movieDao, never()).removeFromFavorite(movieId)
    }

    @Test
    fun `toggleFavorite should call removeFromFavorite when isFavorite is false`() = runTest {
        val movieId = 1
        val isFavorite = false

        repository.toggleFavorite(movieId, isFavorite)

        verify(movieDao).removeFromFavorite(movieId)
        verify(movieDao, never()).addToFavorite(movieId)
    }
}