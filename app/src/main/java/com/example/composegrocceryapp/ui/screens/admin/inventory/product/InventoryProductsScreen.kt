package com.example.composegrocceryapp.ui.screens.admin.inventory.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.data.productList
import com.example.composegrocceryapp.model.Product

@ExperimentalFoundationApi
@Composable
fun InventoryProductsScreen(
    navigator: NavController,
    viewModel: InventoryProductsViewModel
) {
    Scaffold(topBar = { ReactiveAppbar(navigator = navigator, title = "Products") }) {
        LazyColumn {
            items(productList) {
                InventoryProductCard(product = it)
            }
        }
    }
}

@Composable
fun InventoryProductCard(product: Product) {
    Card(modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier.height(70.dp)
            )
            Width(width = 15)
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
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.poppins_light))
                    )
                )

                Text(
                    text = "$ ${product.price}  |  Rating : ${product.rating}", style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.poppins_light))
                    )
                )
            }
        }
    }
}
