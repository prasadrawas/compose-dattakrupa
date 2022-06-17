package com.example.composegrocceryapp.ui.screens.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.data.productList
import com.example.composegrocceryapp.ui.widgets.ProductCard

@ExperimentalFoundationApi
@Composable
fun ProductListerScreen(
    navigator: NavController,
    viewModel: ProductsListerViewModel
) {
    Scaffold(topBar = { ReactiveAppbar(navigator = navigator, "Products") }) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(start = 6.dp)
        ) {
            items(productList) {
                ProductCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .padding(start = 15.dp, end = 15.dp, bottom = 12.dp),
                    product = it
                )
            }
        }
    }
}

