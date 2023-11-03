package com.feature.movie_detail.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.ui.composable.BackButton
import com.core.ui.composable.FavoriteButton
import com.core.ui.composable.Loader
import com.core.ui.composable.PriceTag
import com.core.ui.theme.Grey60
import com.feature.movie_detail.presentation.MovieDetailViewModel
import com.shared.movie.Movie

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onGoBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val movieDetailUiState by viewModel.state.collectAsState()
    val poster by viewModel.poster.collectAsState()

    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    LaunchedEffect(movieDetailUiState.errorMessage) {
        if (movieDetailUiState.errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(message = movieDetailUiState.errorMessage)
        }
    }

    viewModel.getMovieDetails(movieId)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                movieDetailUiState.isLoading -> {
                    Loader()
                }
                movieDetailUiState.errorMessage.isNotBlank() -> {
                    Text(text = movieDetailUiState.errorMessage)
                }
                movieDetailUiState.movie != null -> {
                    movieDetailUiState.movie?.let { movie ->
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                        ) {
                            Box {
                                MoviePoster(posterUrl = poster.posterUrl)
                                HeaderButtons(
                                    movie = movie,
                                    onBackButtonClicked = { onGoBack() },
                                    onFavoriteButtonClicked = { viewModel.toggleFavorite(it) }
                                )
                            }

                            Column (
                                modifier = Modifier.padding(horizontal = 26.dp, vertical = 12.dp),
                            ) {
                                Row (
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Genres: ${movie.genre}",
                                        style = MaterialTheme.typography.labelMedium
                                    )

                                    PriceTag(price = movie.price.toString())
                                }

                                Spacer(modifier = Modifier.height(28.dp))

                                Text(
                                    text = movie.title,
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = movie.description,
                                    color = Grey60
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HeaderButtons(
    movie: Movie,
    onBackButtonClicked: () -> Unit,
    onFavoriteButtonClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .offset(y = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(onClick = { onBackButtonClicked() })

        FavoriteButton(
            isFavorite = movie.isFavorite,
            onClick = { onFavoriteButtonClicked(!movie.isFavorite) }
        )
    }
}