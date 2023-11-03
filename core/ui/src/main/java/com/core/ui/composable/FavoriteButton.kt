package com.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.core.ui.R
import com.core.ui.theme.NeutralBlack

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = if (isFavorite) {
        R.drawable.ic_favorite_30
    } else {
        R.drawable.ic_favorite_border_30
    }

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(NeutralBlack.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}