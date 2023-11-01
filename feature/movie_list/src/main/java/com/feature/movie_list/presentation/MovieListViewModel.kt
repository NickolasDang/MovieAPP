package com.feature.movie_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.feature.movie_list.data.toMovieList
import com.feature.movie_list.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _state = MutableStateFlow(MovieListUiState())
    val state: StateFlow<MovieListUiState> = _state.asStateFlow()

    private var _input = MutableStateFlow("star")

    init {
        viewModelScope.launch {
            _input.debounce(1000).collectLatest {input ->
                if (input.isNotBlank()) {
                    getMovieList(input)
                }
            }
        }
    }

    fun searchMovie(input: String) {
        _input.value = input
    }

    private fun getMovieList(input: String = "star") {
        viewModelScope.launch {
            try {
                _state.value = MovieListUiState(isLoading = true)

                val movieList = movieRepository.getMovieList(input)
                _state.value = MovieListUiState(movieList = movieList)
                Log.d("MOVIE", movieList.toString())

            } catch (exception: IOException) {
                _state.value = MovieListUiState(errorMessage = exception.localizedMessage ?: "Error occurred")
                Log.d("MOVIE", exception.localizedMessage)
            }
        }
    }
}