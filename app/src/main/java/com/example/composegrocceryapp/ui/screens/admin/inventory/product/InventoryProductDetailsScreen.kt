package com.example.composegrocceryapp.ui.screens.admin.inventory.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.data.productList
import com.example.composegrocceryapp.ui.widgets.AnnotatedBoldText
import com.example.composegrocceryapp.ui.widgets.BoldText
import com.example.composegrocceryapp.ui.widgets.ProductImage
import com.example.composegrocceryapp.ui.widgets.RatingBar

@Composable
fun InventoryProductDetailsScreen(
    navigator: NavController,
    viewModel: InventoryProductDetailsViewModel
) {
    val product = productList[0];
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ReactiveAppbar(navigator = navigator, title = "Product Details")
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            EditableCompose(content = { ProductImage(product.image!!) }) {

            }
            Height(height = 15)
            EditableCompose(content = { BoldText(product.name!!) }) {

            }
            EditableCompose(content = { RatingBar(count = product.rating!!) }) {

            }
            EditableCompose(content = { AnnotatedBoldText(boldText = "Price : ", simpleText = "$ ${product.price}") }) {

            }
            EditableCompose(content = { AnnotatedBoldText(boldText = "Category : ", simpleText = "${product.category}") }) {

            }


            Height(height = 25)
            ReactiveButton(title = "Add Product", backgroundColor = Color.White, foregroundColor = Color.Black) {

            }
            Height(height = 5)
            ReactiveButton(title = "Delete Product", backgroundColor = Color.Red) {

            }
        }
    }
}



@Composable
private fun EditableCompose(content: @Composable () -> Unit, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Surface(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)){ content() }
    }
}
