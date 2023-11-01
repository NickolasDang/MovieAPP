package com.feature.movie_list.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.core.ui.theme.Grey50
import com.core.ui.theme.Grey60
import com.feature.movie_list.domain.Movie

@Composable
fun MovieList(
    movieList: List<Movie>
) {
    
    LazyColumn() {
        items(movieList) {movie ->
            MovieListIem(movie = movie)
        }
    }
}

@Composable
fun MovieListIem(
    movie: Movie
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
            AsyncImage(
                model = movie.imgUrl,
                contentDescription = "",
                modifier = Modifier
                    .height(120.dp)
                    .width(80.dp),
                alignment = Alignment.CenterStart
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
                    .width(250.dp)
            )

            Text(
                text = movie.genre,
                fontFamily = FontFamily.Serif,
                fontSize = 14.sp,
                color = Grey60
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Grey50),
            ) {
                Text(
                    text = "${movie.price}$",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 10.dp)
                )
            }
        }

        Icon(
            painter = painterResource(id = com.core.ui.R.drawable.star_border_48),
            contentDescription = "",
        )
    }
}