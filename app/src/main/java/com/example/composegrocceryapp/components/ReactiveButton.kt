package com.example.composegrocceryapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.ui.theme.Green200

@Composable
fun ReactiveButton(
    title: String,
    isLoading: MutableState<Boolean> = mutableStateOf(false),
    backgroundColor: Color = Green200,
    foregroundColor: Color = Color.White,
    width: Float = 0.7f,
    height: Int = 47,
    onClick: () -> Unit
) {
    val btnWidth = (LocalConfiguration.current.screenWidthDp.toFloat()*width).toInt()
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.width(btnWidth.dp).height(height.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
            ),
            enabled = !isLoading.value
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(
                    color = foregroundColor,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            }else {
                Text(
                    text = title,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = foregroundColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}