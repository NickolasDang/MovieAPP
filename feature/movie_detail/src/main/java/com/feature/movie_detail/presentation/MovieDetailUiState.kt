package com.feature.movie_detail.presentation

import com.shared.movie.Movie

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val errorMessage: String = ""
)
