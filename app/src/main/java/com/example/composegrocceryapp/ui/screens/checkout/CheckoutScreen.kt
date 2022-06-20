package com.example.composegrocceryapp.ui.screens.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.db.productList
import com.example.composegrocceryapp.ui.screens.cart.CartCard
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.BoldText
import com.example.composegrocceryapp.ui.widgets.CustomDetailsSection
import com.example.composegrocceryapp.ui.widgets.ScreenTitle

@Composable
fun CheckoutScreen(
    navigator: NavController,
    viewModel: CheckoutViewModel
) {
    Scaffold(topBar = { ReactiveAppbar(navigator = navigator) }) {
        LazyColumn {
            item {
                Box(modifier = Modifier.padding(start = 15.dp)) {
                    ScreenTitle(title = "Checkout")
                }
            }
            item { CustomDetailsSection() }
            item { CartItems() }
            item { BillSection() }
            item { ReactiveButton(title = "Order", width = 0.85f) {
                
            } }
        }
    }
}

@Composable
fun BillSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoldText(text = "Total")
        BoldText(text = "$ 20", color = Green200)
    }
}

@Composable
fun CartItems() {
    productList.subList(0, 2).forEach {
        CartCard(it = it)
    }
}

