package com.feature.movie_list.presentation

import com.feature.movie_list.domain.Movie

data class MovieListUiState(
    val isLoading: Boolean = false,
    val movieList: List<Movie> = emptyList(),
    val errorMessage: String = ""
)