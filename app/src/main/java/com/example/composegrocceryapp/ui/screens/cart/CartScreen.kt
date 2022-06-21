package com.example.composegrocceryapp.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.ui.widgets.QuantityButton
import com.example.composegrocceryapp.utils.LifecycleHandler

@Composable
fun CartScreen(
    navigator: NavController,
    viewModel: CartViewModel
) {
    LifecycleHandler { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.getAllCartItems()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ReactiveAppbar(navigator = navigator, title = "My Cart") }
    ) {
        val cartList = viewModel.cartList.collectAsState().value
        MainContent(cartList, viewModel)

        BottomCheckoutButton(size = cartList.size)
    }
}

@Composable
private fun MainContent(
    cartList: List<Product>,
    viewModel: CartViewModel
) {
    if (cartList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No items to display here", style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontSize = 15.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn {
            items(cartList) {
                CartCard(product = it, viewModel = viewModel)
            }
        }
    }


}

@Composable
private fun BottomCheckoutButton(size: Int) {
    if (size > 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            ReactiveButton(title = "Checkout", width = 0.95f) {

            }
        }
    }
}

@Composable
fun CartCard(product: Product, viewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier.height(90.dp)
            )
            Width(width = 12)
            Column {
                Text(
                    text = product.name!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = product.category!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "$ ${product.price}", style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    )

                    Width(width = 40)
                    QuantityButton(title = "-", size = 30) {
                        viewModel.removeFromCart(id = product.id)
                    }
                    Width(width = 10)
                    Text(
                        text = "${product.quantity}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                    Width(width = 10)
                    QuantityButton(title = "+", size = 30) {
                        viewModel.addToCart(id = product.id)
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = { viewModel.deleteItemFromCart(product) }) {
                Icon(
                    imageVector = Icons.Rounded.DeleteOutline,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}
