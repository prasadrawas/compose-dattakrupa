package com.example.composegrocceryapp.ui.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.composegrocceryapp.db.ordersList
import com.example.composegrocceryapp.db.productList
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.BoldText
import com.example.composegrocceryapp.ui.widgets.CustomDetailsSection
import com.example.composegrocceryapp.ui.widgets.ScreenTitle

@Composable
fun OrderDetailsScreen(
    navigator: NavController,
    viewModel: OrderDetailsViewModel
) {
    Scaffold(
        topBar = { ReactiveAppbar(navigator = navigator) }
    ) {
        LazyColumn {
            item { ScreenTitle(title = "Order Details", modifier = Modifier.padding(start = 15.dp)) }
            item { CustomDetailsSection() }
            item { BoldText(text = "Order Value : $ 25", color = Green200, modifier = Modifier.padding(start = 15.dp)) }
            item {
                buildOrderList().forEach {
                    OrderItemCard(item = it)
                }
            }
        }
    }
}


@Composable
fun OrderItemCard(item: Product) {
    Card(modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = null,
                modifier = Modifier.height(70.dp)
            )
            Width(width = 20)
            Column {
                Text(
                    text = item.name!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Text(
                    text = item.category!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                )



                Text(
                    text = "Quantity : ${item.quantity}", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                )

                Text(
                    text = "Price : $ ${item.price}", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}

private fun buildOrderList(): MutableList<Product> {
    val list = mutableListOf<Product>()
    for (p in productList) {
        ordersList[0].items?.forEach {
            if (it.id == p.id) list.add(p)
        }
    }
    return list
}