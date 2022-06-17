package com.example.composegrocceryapp.ui.screens.admin.inventory.category

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
import com.example.composegrocceryapp.db.categoryList
import com.example.composegrocceryapp.model.Category

@ExperimentalFoundationApi
@Composable
fun InventoryCategoryScreen(
    navigator: NavController,
    viewModel: InventoryCategoryViewModel
) {
    Scaffold(topBar = { ReactiveAppbar(navigator = navigator, title = "Categories") }) {
        LazyColumn {
            items(categoryList) {
                InventoryCategoryCard(category = it)

            }
        }
    }
}

@Composable
fun InventoryCategoryCard(category: Category) {
    Card(modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.image),
                contentDescription = null,
                modifier = Modifier.height(50.dp)
            )
            Width(width = 15)
            Column {
                Text(
                    text = category.name!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}
