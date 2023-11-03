package com.feature.movie_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.movie_list.domain.MovieRepository
import com.core.util.Resource
import com.shared.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _state = MutableStateFlow(MovieListUiState())
    val state: StateFlow<MovieListUiState> = _state.asStateFlow()

    private var _input = MutableStateFlow("")

    init {
        getMovieListFromCache()
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            try {
                movieRepository.toggleFavorite(movie.id, !movie.isFavorite)
                getMovieListFromCache()
            } catch (e: Exception) {
                Log.d("ERROR", e.localizedMessage)
                _state.value = MovieListUiState(errorMessage = "Something went wrong")
            }
        }
    }

    fun searchMovie(input: String) {
        _input.value = input
        viewModelScope.launch {
            _input.debounce(1000).collectLatest {input ->
                getMovies(input)
            }
        }
    }

    private fun getMovies(input: String) {
        movieRepository.getMovieList(input)
            .onEach { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.value = MovieListUiState(
                            isLoading = true,
                            movieList = result.data ?: emptyList()
                        )
                    }
                    is Resource.Success -> {
                        _state.value = MovieListUiState(movieList = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = MovieListUiState(
                            movieList = result.data ?: emptyList(),
                            errorMessage = result.message ?: "Oops, something went wrong"
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getMovieListFromCache() {
        viewModelScope.launch {
            _state.value = MovieListUiState(
                movieList = movieRepository.getMovieListFromCache()
            )
        }
    }
}