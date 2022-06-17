package com.example.composegrocceryapp.ui.screens.order

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.db.ordersList
import com.example.composegrocceryapp.ui.widgets.OrderCard

@Composable
fun OrdersScreen(
    navigator: NavController,
    viewModel: OrdersViewModel
) {
    Scaffold(
        topBar = { ReactiveAppbar(navigator = navigator, title = "My Orders") }
    ) {
        LazyColumn {
            items(ordersList) {
                OrderCard(order = it)
            }
        }
    }
}

