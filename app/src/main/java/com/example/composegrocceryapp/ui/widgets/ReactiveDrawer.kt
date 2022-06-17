package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Width

@Composable
fun ReactiveDrawer(name: String) {
    val menus = listOf(
        Pair(first = Icons.Rounded.Person, "Profile"),
        Pair(first = Icons.Rounded.ShoppingCart, "Cart"),
        Pair(first = Icons.Rounded.Notes, "My Orders"),
        Pair(first = Icons.Rounded.Inventory, "Inventory"),
        Pair(first = Icons.Rounded.Logout, "Logout"),
    )
    Surface(
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
    ) {
        LazyColumn {
            item {
                DrawerHeader(name = name)
            }
            items(menus) {
                DrawerMenu(it)
            }
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.padding(12.dp)
        ){
            Text(
                text = stringResource(id = R.string.designed_and_developed),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_extra_light)),
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}


@Composable
private fun DrawerHeader(name: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_avatar),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .padding(12.dp)
        )
        BoldText(text = name)
        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
    }

}
