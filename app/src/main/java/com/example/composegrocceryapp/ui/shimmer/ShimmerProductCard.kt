package com.example.composegrocceryapp.ui.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composegrocceryapp.components.Height

@Preview
@Composable
fun ShimmerProductCard(modifier: Modifier = Modifier) {
    val brush = animatedShimmerBrush()

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ){
        Spacer(
            modifier = Modifier
                .width(160.dp)
                .height(190.dp)
                .background(brush)
        )
    }
}
