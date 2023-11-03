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
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(NeutralBlack.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = null,
                modifier = modifier.size(30.dp)
            )
        }
    }
}