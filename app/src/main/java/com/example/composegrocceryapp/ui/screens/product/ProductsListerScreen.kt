package com.example.composegrocceryapp.ui.screens.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.ui.shimmer.ShimmerProductCard
import com.example.composegrocceryapp.ui.widgets.BottomCartSheet
import com.example.composegrocceryapp.ui.widgets.DisplayException
import com.example.composegrocceryapp.ui.widgets.ProductCard
import com.example.composegrocceryapp.utils.LifecycleHandler

@ExperimentalFoundationApi
@Composable
fun ProductListerScreen(
    category: String,
    navigator: NavController,
    viewModel: ProductsListerViewModel
) {
    LifecycleHandler { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.getCartItems()
            viewModel.getProductsByCategory(category = category)
        }
    }

    Scaffold(topBar = { ReactiveAppbar(navigator = navigator, category) }) {
        val dataOrException = viewModel.dataOrException
        MainContent(dataOrException, navigator, viewModel)
        CartCheckoutButton(viewModel = viewModel, navigator = navigator)
    }
}

@Composable
private fun CartCheckoutButton(viewModel: ProductsListerViewModel, navigator: NavController) {
    if (viewModel.cartSheetData.value.first > 0) {
        BottomCartSheet(data = viewModel.cartSheetData.value){
            navigator.navigate(AppScreens.Cart.name)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun MainContent(
    dataOrException: MutableState<Any>,
    navigator: NavController,
    viewModel: ProductsListerViewModel
) {
    when (dataOrException.value) {
        is Boolean -> {
            DataLoading()
        }
        is ArrayList<*> -> {
            val data = dataOrException.value as ArrayList<Product>
            ProductsGrid(data = data, navigator = navigator, viewModel = viewModel)
        }
        else -> {
            val exception = dataOrException.value as Exception
            DisplayException(exception = exception)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun ProductsGrid(
    data: ArrayList<Product>,
    viewModel: ProductsListerViewModel,
    navigator: NavController
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 6.dp)
    ) {
        items(data) {
            ProductCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(start = 15.dp, end = 15.dp, bottom = 12.dp),
                navigator = navigator,
                viewModel = viewModel,
                product = it
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun DataLoading() {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 6.dp)
    ) {
        items(10) {
            ShimmerProductCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .padding(start = 15.dp, end = 15.dp, bottom = 12.dp),
            )
        }
    }
}

