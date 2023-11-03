package com.feature.movie_list.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.core.ui.composable.FavoriteButton
import com.core.ui.composable.PriceTag
import com.feature.movie_list.R
import com.shared.movie.Movie

@Composable
fun MovieList(
    movieList: List<Movie>,
    onItemClicked: (Int) -> Unit,
    onToggleFavorite: (Movie) -> Unit
) {
    
    LazyColumn() {
        items(movieList) {movie ->
            MovieListIem(
                movie = movie,
                onItemClicked = onItemClicked,
                onToggleFavorite = { onToggleFavorite(it) }
            )
        }
    }
}

@Composable
fun MovieListIem(
    movie: Movie,
    onItemClicked: (Int) -> Unit,
    onToggleFavorite: (Movie) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 12.dp)
            .clickable {
                onItemClicked(movie.id)
            }
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
            AsyncImage(
                model = movie.imgUrl,
                contentDescription = "",
                modifier = Modifier
                    .height(120.dp)
                    .width(80.dp),
                alignment = Alignment.CenterStart,
                error = painterResource(id = com.core.ui.R.drawable.placeholder)
            )
        }

        Column (
          modifier = Modifier
              .fillMaxHeight()
              .padding(vertical = 4.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .width(220.dp)
            )

            Text(
                text = movie.genre,
                style = MaterialTheme.typography.labelSmall
            )

            PriceTag(price = movie.price.toString())
        }

        FavoriteButton(
            isFavorite = movie.isFavorite,
            onClick = { onToggleFavorite(movie) },
            modifier = Modifier.size(20.dp)
        )
    }
}