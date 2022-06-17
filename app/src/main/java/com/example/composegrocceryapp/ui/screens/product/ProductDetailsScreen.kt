package com.example.composegrocceryapp.ui.screens.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.data.productList
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.BoldText
import com.example.composegrocceryapp.ui.widgets.ProductImage
import com.example.composegrocceryapp.ui.widgets.QuantityButton
import com.example.composegrocceryapp.ui.widgets.RatingBar

@Composable
fun ProductDetailsScreen(
    navigator: NavController,
    viewModel: ProductDetailsViewModel
) {
    val product = productList[0];
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ReactiveAppbar(navigator = navigator)
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            ProductImage(product.image!!)
            Height(height = 15)
            BoldText(product.name!!)
            Height(height = 15)
            RatingBar(count = product.rating!!)
            Height(height = 25)
            Text(text = "Quantity", style = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            ))
            Height(height = 15)
            QuantityRow()
            Height(height = 25)
            ReactiveButton(title = "Buy now", backgroundColor = Color.White, foregroundColor = Green200) {
                
            }
            Height(height = 5)
            ReactiveButton(title = "Add to cart") {

            }
        }
    }
}

@Composable
private fun QuantityRow() {
    Row(
        modifier = Modifier.width(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        QuantityButton(title = "-"){}
        BoldText(text = "1")
        QuantityButton(title = "+"){}
    }
}



