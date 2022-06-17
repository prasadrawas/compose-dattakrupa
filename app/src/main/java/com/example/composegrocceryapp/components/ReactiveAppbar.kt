package com.example.composegrocceryapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composegrocceryapp.navigation.AppScreens

@Composable
fun ReactiveAppbar(
    navigator: NavController,
    title: String = "",
    leadingIcon: ImageVector = Icons.Rounded.ArrowBack,
    onLeadingIconClick: () -> Unit = { navigator.popBackStack() },
    actions: @Composable () -> Unit = {},
    elevation: Int = 1
){
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {  Text(title) },
        navigationIcon = { IconButton(onClick = onLeadingIconClick) {
            Icon(imageVector = leadingIcon, contentDescription = null)
        }},
        actions = { actions() },
        elevation = elevation.dp,
        backgroundColor = Color.White
    )
}