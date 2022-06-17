package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.data.productList
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.ui.theme.Green200

@Composable
fun ProductCard(modifier: Modifier, product: Product = productList[0]) {
    Card(
        modifier = modifier
            .clickable { },
        shape = RoundedCornerShape(10.dp)
    ) {
        val isFav = rememberSaveable {
            mutableStateOf(false)
        }

        FavouriteButton(isFav)
        MainContent(product)
        AddButton()
    }
}

@Composable
private fun AddButton() {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Card(
            backgroundColor = Green200,
            contentColor = Color.White,
            modifier = Modifier
                .size(35.dp)
                .clickable { }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "+", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun MainContent(product: Product) {
    Column(
        modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp).fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = product.name!!,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
            )
        )
        Text(
            text = product.category!!,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
            )
        )
        Height(height = 8)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$ ${product.price}",
                modifier = Modifier.padding(end = 5.dp),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(
                text = "$ ${product.price!! + 2}", style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textDecoration = TextDecoration.LineThrough
                )
            )
        }
    }
}

@Composable
private fun FavouriteButton(isFav: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, end = 6.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            onClick = {
                isFav.value = !isFav.value
            },
            modifier = Modifier.size(20.dp)
        ) {
            Icon(
                imageVector = if (isFav.value) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                tint = if (isFav.value) Color.Red else Color.Black
            )
        }
    }
}