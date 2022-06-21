package com.example.composegrocceryapp.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SentimentSatisfied
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.InputField
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.ui.shimmer.ShimmerCategoryCard
import com.example.composegrocceryapp.ui.shimmer.ShimmerProductCard
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.*
import com.example.composegrocceryapp.utils.LifecycleHandler
import com.example.composegrocceryapp.utils.isValidText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(
    navigator: NavController,
    viewModel: HomeViewModel
) {

    LifecycleHandler { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.getCustomerName()
            viewModel.getCartItems()
            viewModel.getCategories()
            viewModel.getProducts()
        }
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            SetupAppBar(navigator, scope, scaffoldState)
        },
        drawerContent = { ReactiveDrawer(name = viewModel.name.value) }
    ) {

        val scrollState = rememberScrollState()
        MainContent(scrollState, viewModel, navigator)
        CartCheckoutButton(viewModel = viewModel, navigator = navigator)
    }
}

@Composable
private fun CartCheckoutButton(viewModel: HomeViewModel, navigator: NavController) {
    if (viewModel.cartSheetData.value.first > 0) {
        BottomCartSheet(data = viewModel.cartSheetData.value){
            navigator.navigate(AppScreens.Cart.name)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun MainContent(
    scrollState: ScrollState,
    viewModel: HomeViewModel,
    navigator: NavController
) {
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(15.dp)
    ) {
        ScreenTitle(title = "Hello ${viewModel.name.value.split(" ")[0]} !")
        InputField(
            placeholder = stringResource(id = R.string.search_here),
            leadingIcon = Icons.Rounded.Search, validator = ::isValidText,
            enabled = false
        )

        Height(height = 18)

        HelperText(title = stringResource(id = R.string.category))
        if (viewModel.categoryList.value.isEmpty()) {
            LazyRow {
                items(5) {
                    ShimmerCategoryCard(
                    )
                }
            }
        } else {
            LazyRow {
                items(viewModel.categoryList.value) {
                    CategoryCard(navigator = navigator, category = it)
                }
            }
        }


        Height(height = 55)

        HelperText(title = stringResource(id = R.string.hot_products))
        Height(height = 8)

        if (viewModel.productList.value.isEmpty()) {
            LazyRow {
                items(4) {
                    ShimmerProductCard(
                        modifier = Modifier.padding(end = 15.dp)
                    )
                }
            }
        } else {
            LazyRow {
                items(viewModel.productList.value) {
                    ProductCard(
                        modifier = Modifier.padding(end = 15.dp),
                        navigator = navigator,
                        viewModel = viewModel,
                        product = it
                    )
                }
            }
        }

        Height(height = 55)

        HelperText(title = stringResource(id = R.string.top_offers))
        Height(height = 8)
        if (viewModel.productList.value.isEmpty()) {
            LazyRow {
                items(4) {
                    ShimmerProductCard(
                        modifier = Modifier.padding(end = 15.dp)
                    )
                }
            }
        } else {
            LazyRow {
                items(viewModel.productList.value.reversed()) {
                    ProductCard(
                        modifier = Modifier.padding(end = 15.dp),
                        navigator = navigator,
                        viewModel = viewModel,
                        product = it
                    )
                }
            }
        }

        Height(height = 58)
    }
}

@Composable
private fun SetupAppBar(
    navigator: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    ReactiveAppbar(
        navigator = navigator,
        leadingIcon = Icons.Rounded.Sort,
        onLeadingIconClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.SentimentSatisfied,
                    contentDescription = null,
                    tint = Green200
                )
            }
        }
    )
}

@Composable
private fun HelperText(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Green200
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}