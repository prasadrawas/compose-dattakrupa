package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.db.categoryList
import com.example.composegrocceryapp.model.Category
import com.example.composegrocceryapp.navigation.AppScreens

@Composable
fun CategoryCard(navigator: NavController, category: Category = categoryList[0]) {

    Column(
        modifier = Modifier
            .width(110.dp)
            .padding(end = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .clickable {
                    navigator.navigate(AppScreens.ProductsLister.name + "/${category.name}")
                },
            shape = CircleShape
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(3.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(category.image),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
            }
        }
        Height(height = 8)
        Text(
            text = category.name!!,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
            )
        )
    }
}