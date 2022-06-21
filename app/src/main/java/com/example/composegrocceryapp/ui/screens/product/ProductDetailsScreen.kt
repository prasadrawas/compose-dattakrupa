package com.example.composegrocceryapp.ui.screens.product

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.*
import com.example.composegrocceryapp.utils.LifecycleHandler

@Composable
fun ProductDetailsScreen(
    id: String,
    navigator: NavController,
    viewModel: ProductDetailsViewModel
) {

    LifecycleHandler { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.getCartQuantity(id = id)
            viewModel.getProductItem(id = id)
        }
    }
    val dataOrException = viewModel.dataOrException
    val scrollState = rememberScrollState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ReactiveAppbar(navigator = navigator)
        }
    ) {
        when (dataOrException.value) {
            is Boolean -> {
                LoadingScreen()
            }
            is Product -> {
                val product = dataOrException.value as Product
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
                    Text(
                        text = "Quantity", style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp
                        )
                    )
                    Height(height = 15)
                    QuantityRow(viewModel = viewModel, product = product)
                    Height(height = 25)
                    ReactiveButton(
                        title = "Buy now",
                        backgroundColor = Color.White,
                        foregroundColor = Green200
                    ) {

                    }
                    Height(height = 25)
                    ReactiveButton(title = "Add to cart") {
                        viewModel.addToCart(product)
                    }
                }
            }
            else -> {
                val exception = dataOrException.value as Exception
                DisplayException(exception = exception)
            }
        }
    }
}

@Composable
private fun QuantityRow(viewModel: ProductDetailsViewModel, product: Product) {
    Row(
        modifier = Modifier.width(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        QuantityButton(title = "-") { viewModel.removeFromCart(id = product.id) }
        BoldText(text = "${viewModel.quantity.value}")
        QuantityButton(title = "+") { viewModel.addToCart(product = product) }
    }
}



