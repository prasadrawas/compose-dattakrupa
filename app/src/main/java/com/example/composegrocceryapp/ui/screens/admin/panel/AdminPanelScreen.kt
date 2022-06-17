package com.example.composegrocceryapp.ui.screens.admin.panel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AllInbox
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.PlusOne
import androidx.compose.material.icons.rounded.Widgets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.ui.theme.Green200
import com.example.composegrocceryapp.ui.widgets.ScreenTitle

@ExperimentalFoundationApi
@Composable
fun AdminPanelScreen(
    navigator: NavController,
    viewModel: AdminPanelViewModel
) {
    val menus = listOf(
        Pair(Icons.Rounded.PlusOne, "Active users"),
        Pair(Icons.Rounded.Widgets, "Orders"),
        Pair(Icons.Rounded.AllInbox, "Products"),
        Pair(Icons.Rounded.Category, "Category"),
    )
    Scaffold(
        topBar = { ReactiveAppbar(navigator = navigator) }
    ) {
        Column {
            ScreenTitle(title = "Admin Panel", modifier = Modifier.padding(start = 15.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(2)
            ) {
                items(menus) {
                    InventoryCard(it)
                }
            }
        }
    }
}

@Composable
fun InventoryCard(it: Pair<ImageVector, String>) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = it.first,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Green200
            )
            Height(height = 10)
            Text(
                text = it.second, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
        }
    }
}
