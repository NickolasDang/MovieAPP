package com.feature.movie_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.util.Resource
import com.feature.movie_detail.domain.MovieDetailRepository
import com.feature.movie_detail.domain.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val INVALID_MOVIE_ID = -1

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository
): ViewModel() {

    private var _state = MutableStateFlow(MovieDetailUiState())
    val state: StateFlow<MovieDetailUiState> = _state.asStateFlow()

    private var _poster = MutableStateFlow(Poster(""))
    val poster: StateFlow<Poster> = _poster.asStateFlow()

    private var movieId = MutableStateFlow(INVALID_MOVIE_ID)

    fun getMovieDetails(movieId: Int) {
        this.movieId.value = movieId
        if (isCurrentMoveIdIncorrect()) {
            _state.value = MovieDetailUiState(errorMessage = "Incorrect movie id")
        } else {
            getMovieById(this.movieId.value)
        }
    }

    private fun isCurrentMoveIdIncorrect() : Boolean =
        movieId.value == INVALID_MOVIE_ID

    private fun getMovieById(movieId: Int) {
        repository.getMovieById(movieId)
            .onEach { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.value = MovieDetailUiState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = MovieDetailUiState(movie = result.data)
                        result.data?.title?.let { getPosterByMovieTitle(it) }
                    }
                    is Resource.Error -> {
                        _state.value = MovieDetailUiState(errorMessage = result.message ?: "Something went wrong")
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getPosterByMovieTitle(movieTitle: String) {
        repository.getPosterByMovieTitle(movieTitle)
            .onEach { result ->
                when(result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _poster.value = result.data ?: Poster("")
                    }
                    is Resource.Error -> {
                        _state.value = MovieDetailUiState(errorMessage = result.message ?: "Something went wrong")
                    }
                }
            }.launchIn(viewModelScope)
    }
}