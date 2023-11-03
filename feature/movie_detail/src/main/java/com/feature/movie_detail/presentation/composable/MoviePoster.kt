package com.feature.movie_detail.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.feature.movie_detail.R


@Composable
fun MoviePoster(posterUrl: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(50.dp))
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(450.dp),
            contentScale = ContentScale.FillWidth,
            error = painterResource(id = com.core.ui.R.drawable.placeholder)
        )
    }
}
