package com.example.composegrocceryapp.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.InputField
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.ui.widgets.ScreenTitle
import com.example.composegrocceryapp.utils.isValidEmail

@ExperimentalComposeUiApi
//@Preview
@Composable
fun ResetPasswordScreen(
    navigator: NavController,
    viewModel: ResetPasswordViewModel
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            ReactiveAppbar(navigator = navigator)
        }
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            ScreenTitle(title = stringResource(id = R.string.reset_your_npassword))
            Height(height = 10)
            Text(text = stringResource(id = R.string.password_reset_msg), style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontWeight = FontWeight.Light,
            ))
            Height(height = 20)
            InputField(
                state = viewModel.email,
                placeholder = "Enter email",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                validator = ::isValidEmail,
                isValid = viewModel.isValid
            )
            Height(height = 20)
            ReactiveButton(title = stringResource(id = R.string.send_link)) {
                navigator.navigate(AppScreens.ResetSuccess.name)
            }
        }
    }
}

