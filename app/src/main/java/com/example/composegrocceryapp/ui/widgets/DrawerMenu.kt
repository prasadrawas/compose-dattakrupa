package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Width


@Composable
fun DrawerMenu(menu: Pair<ImageVector, String>, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Width(width = 15)
        Icon(
            imageVector = menu.first,
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )
        Width(width = 10)
        Text(
            text = menu.second, style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 16.sp
            )
        )
    }
}