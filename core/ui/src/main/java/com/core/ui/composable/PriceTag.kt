package com.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.theme.Grey50

@Composable
fun PriceTag(
    price: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Grey50),
    ) {
        Text(
            text = "$price$",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 10.dp)
        )
    }
}