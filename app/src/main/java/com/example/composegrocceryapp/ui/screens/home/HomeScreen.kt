package com.example.composegrocceryapp.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.InputField
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.db.categoryList
import com.example.composegrocceryapp.db.productList
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.CategoryCard
import com.example.composegrocceryapp.ui.widgets.ProductCard
import com.example.composegrocceryapp.ui.widgets.ReactiveDrawer
import com.example.composegrocceryapp.ui.widgets.ScreenTitle
import com.example.composegrocceryapp.utils.isValidText
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(
    navigator: NavController,
    viewModel: HomeViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
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
        },
        drawerContent = { ReactiveDrawer(name = "Prasad Rawas") }
    ) {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(15.dp)
        ) {
            ScreenTitle(title = "Hello Prasad !")
            InputField(
                placeholder = stringResource(id = R.string.search_here),
                leadingIcon = Icons.Rounded.Search, validator = ::isValidText,
                enabled = false
            )

            Height(height = 18)

            HelperText(title = stringResource(id = R.string.category))
            LazyRow {
                items(categoryList) {
                    CategoryCard(it)
                }
            }

            Height(height = 55)

            HelperText(title = stringResource(id = R.string.hot_products))
            Height(height = 8)
            LazyRow {
                items(productList) {
                    ProductCard(
                        modifier = Modifier
                            .width(140.dp)
                            .height(180.dp)
                            .padding(end = 10.dp),
                        product = it
                    )
                }
            }

            Height(height = 55)

            HelperText(title = stringResource(id = R.string.top_offers))
            Height(height = 8)
            LazyRow {
                items(productList) {
                    ProductCard(
                        modifier = Modifier
                            .width(160.dp)
                            .height(190.dp)
                            .padding(end = 10.dp),
                        product = it
                    )
                }
            }
        }
    }
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