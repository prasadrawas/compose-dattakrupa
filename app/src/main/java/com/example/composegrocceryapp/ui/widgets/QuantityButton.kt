package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegrocceryapp.ui.theme.Green200

@Composable
fun QuantityButton(
    title: String,
    size: Int = 50,
    onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(size.dp)
            .clickable { onClick.invoke() },
        backgroundColor = Color.White,
        contentColor = Green200,
        elevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = title, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}