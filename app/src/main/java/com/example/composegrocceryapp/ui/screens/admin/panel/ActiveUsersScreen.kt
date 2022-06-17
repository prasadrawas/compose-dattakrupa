package com.example.composegrocceryapp.ui.screens.admin.panel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
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
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.db.usersList
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.ui.widgets.IconText
import com.example.composegrocceryapp.ui.widgets.ScreenTitle

@ExperimentalFoundationApi
@Composable
fun ActiveUsersScreen(
    navigator: NavController,
    viewModel: ActiveUsersViewModel
) {
    Scaffold(
        topBar = { ReactiveAppbar(navigator = navigator, title = "Active Users") }
    ) {
        LazyColumn {
            item{ ScreenTitle(title = "182 Users", modifier = Modifier.padding(start = 15.dp)) }
            items(usersList){
                UserCard(customer = it)
            }
        }
    }
}

@Composable
fun UserCard(customer: Customer) {
    Card(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img_avatar),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(12.dp)
            )
            Width(width = 10)
            Column {
                Text(
                    text = customer.name!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                IconText(icon = Icons.Rounded.Phone, text = customer.phone!!)
                IconText(icon = Icons.Rounded.Email, text = customer.email!!)
            }
        }
    }
}

