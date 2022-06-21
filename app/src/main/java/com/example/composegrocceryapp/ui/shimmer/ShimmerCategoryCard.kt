package com.example.composegrocceryapp.ui.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composegrocceryapp.components.Height

@Preview
@Composable
fun ShimmerCategoryCard() {
    val brush = animatedShimmerBrush()

    Column(
        modifier = Modifier
            .width(110.dp)
            .padding(end = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Height(height = 8)
        Spacer(
            modifier = Modifier
                .width(80.dp)
                .height(10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(brush)
        )
    }
}
