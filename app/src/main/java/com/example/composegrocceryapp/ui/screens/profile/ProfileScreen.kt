package com.example.composegrocceryapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.ui.widgets.BoldText
import com.example.composegrocceryapp.ui.widgets.DrawerMenu

@Composable
fun ProfileScreen(
    navigator: NavController,
    viewModel: ProfileViewModel
) {
    val menus = listOf(
        Pair(Icons.Rounded.ShoppingCart, "My Cart"),
        Pair(Icons.Rounded.Notes, "My Orders"),
        Pair(Icons.Rounded.Email, "Change Email"),
        Pair(Icons.Rounded.Lock, "Change Password"),
        Pair(Icons.Rounded.Logout, "Logout"),
    )
    Scaffold(
        topBar = { ReactiveAppbar(navigator = navigator, title = "My Profile") }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item { UserInfoSection() }
            item { Divider(modifier = Modifier.padding(20.dp)) }
            items(menus) { DrawerMenu(menu = it) }
        }
    }
}

@Composable
private fun UserInfoSection() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_avatar),
            contentDescription = null,
            modifier = Modifier.height(120.dp)
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        BoldText(text = "Prasad Rawas")
        Text(
            text = "prasadrawas.om@gmail.com", style = TextStyle(
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontSize = 16.sp
            )
        )
    }
}