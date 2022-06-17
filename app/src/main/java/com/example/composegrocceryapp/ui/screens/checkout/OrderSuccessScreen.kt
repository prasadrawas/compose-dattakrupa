package com.example.composegrocceryapp.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.ui.widgets.ScreenTitle
import com.example.composegrocceryapp.navigation.AppScreens

@Composable
fun OrderSuccessScreen(
    navigator: NavController
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            ReactiveAppbar(navigator = navigator)
        }
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
        ) {
            ScreenTitle(title = "Order Success")
            Height(height = 10)
            Text(text = "Your order is placed successfully. We'll deliver it to your address with 24 hours or less.", style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontWeight = FontWeight.Light,
            )
            )
            Height(height = 20)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(id = R.drawable.img_order_success), contentDescription = null,
                modifier = Modifier.height(300.dp))
            }
            Height(height = 30)
            ReactiveButton(title = stringResource(id = R.string.continue_shopping), width = 0.7f) {
                navigator.navigate(AppScreens.SignIn.name)
            }
        }
    }
}