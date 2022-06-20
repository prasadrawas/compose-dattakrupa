package com.example.composegrocceryapp.ui.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.InputField
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.ui.widgets.ScreenTitle
import com.example.composegrocceryapp.utils.isValidEmail
import com.example.composegrocceryapp.utils.isValidPhone
import com.example.composegrocceryapp.utils.isValidText

@ExperimentalComposeUiApi
@Composable
fun AddressScreen(
    navigator: NavController,
    viewModel: AddressViewModel
){
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { ReactiveAppbar(navigator = navigator) }
    ) {
        Column(
            modifier = Modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp).verticalScroll(scrollState)
        ){
            ScreenTitle(title = stringResource(id = R.string.your_address))
            Height(height = 20)
            InputField(
                state = viewModel.name,
                placeholder = "Enter name",
                leadingIcon = Icons.Rounded.Person,
                validator = ::isValidText)

            InputField(
                state = viewModel.email,
                placeholder = "Enter email",
                leadingIcon = Icons.Rounded.Email,
                keyboardType = KeyboardType.Email,
                validator = ::isValidEmail)


            InputField(
                state = viewModel.phone,
                placeholder = "Enter email",
                leadingIcon = Icons.Rounded.Phone,
                keyboardType = KeyboardType.Number,
                validator = ::isValidPhone)


            InputField(
                state = viewModel.city,
                placeholder = "Enter city",
                leadingIcon = Icons.Rounded.LocationCity,
                validator = ::isValidText)


            InputField(
                state = viewModel.pinCode,
                placeholder = "Enter pin",
                leadingIcon = Icons.Rounded.Pin,
                validator = ::isValidText)


            InputField(
                state = viewModel.landmark,
                placeholder = "Enter full address",
                leadingIcon = Icons.Rounded.MyLocation,
                validator = ::isValidText)

            Height(height = 25)

            ReactiveButton(title = "Checkout") {

            }
        }
    }
}