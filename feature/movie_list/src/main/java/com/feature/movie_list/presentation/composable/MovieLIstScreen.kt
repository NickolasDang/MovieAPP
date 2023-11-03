package com.feature.movie_list.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.ui.composable.Loader
import com.feature.movie_list.presentation.MovieListViewModel
import kotlinx.coroutines.launch

@Composable
fun MovieListScreen(
    movieListViewModel: MovieListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
) {

    val movieListUiState by movieListViewModel.state.collectAsState()
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    LaunchedEffect(movieListUiState.errorMessage) {
        if (movieListUiState.errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(message = movieListUiState.errorMessage)
        }
    }

    LaunchedEffect(Unit) {
        movieListViewModel.getMovieListFromCache()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        content = { innerPadding ->
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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
                    }

                    MovieList(
                        movieList = movieListUiState.movieList,
                        onItemClicked = onItemClicked,
                        onToggleFavorite = { movie ->
                            movieListViewModel.toggleFavorite(movie)
                        }
                    )
                }
            }
        }
    )
}
