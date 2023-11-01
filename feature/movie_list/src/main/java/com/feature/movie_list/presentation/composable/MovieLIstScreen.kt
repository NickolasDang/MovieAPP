package com.feature.movie_list.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.core.ui.Loader
import com.core.ui.theme.NeutralBlack
import com.feature.movie_list.presentation.MovieListViewModel

@Composable
fun MovieListScreen(
    movieListViewModel: MovieListViewModel = viewModel()
) {

    val movieListUiState by movieListViewModel.state.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            SearchBar(
                hint = "Search",
                onSearch = { input ->
                    movieListViewModel.searchMovie(input)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                movieListUiState.isLoading -> {
                    Loader()
                }
                movieListUiState.errorMessage.isNotBlank() -> {

                }
                movieListUiState.movieList.isNotEmpty() -> {
                    MovieList(movieList = movieListUiState.movieList)
                }
            }
        }
    }
}
