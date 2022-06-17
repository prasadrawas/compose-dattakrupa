package com.example.composegrocceryapp.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.data.productList
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.ui.widgets.QuantityButton

@Composable
fun CartScreen(
    navigator: NavController,
    viewModel: CartViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ReactiveAppbar(navigator = navigator, title = "My Cart") }
    ) {
        LazyColumn{
            items(productList.subList(0,10)) {
                CartCard(it)
            }
        }
        
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            ReactiveButton(title = "Checkout", width = 0.85f) {

            }
        }
    }
}

@Composable
fun CartCard(it: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(12.dp)
    ) {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(it.image),
                contentDescription = null,
                modifier = Modifier.height(90.dp)
            )
            Width(width = 12)
            Column {
                Text(text = it.name!!, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ))
                Text(text = it.category!!, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light
                ))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "$ ${it.price}", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    ))

                    Width(width = 40)
                    QuantityButton(title = "-", size = 30) {

                    }
                    Width(width = 20)
                    QuantityButton(title = "+", size = 30) {}
                }
            }
        }
        Box(
            contentAlignment = Alignment.CenterEnd
        ){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.DeleteOutline, contentDescription = null, tint =  Color.Red)
            }
        }
    }
}
