package com.example.composegrocceryapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
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
import com.example.composegrocceryapp.utils.isStrongPassword
import com.example.composegrocceryapp.utils.isValidEmail

@ExperimentalComposeUiApi
@Composable
fun ChangePasswordScreen(
    navigator: NavController,
    viewModel: ChangePasswordViewModel
){
    Scaffold(topBar = { ReactiveAppbar(navigator = navigator)}) {
        Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
            ScreenTitle(title = stringResource(id = R.string.change_password))
            InputField(
                state = viewModel.email,
                placeholder = "Enter email",
                leadingIcon = Icons.Rounded.Email,
                keyboardType = KeyboardType.Email,
                validator = ::isValidEmail)
            InputField(
                state = viewModel.currentPassword,
                placeholder = "Enter current password",
                leadingIcon = Icons.Rounded.Lock,
                validator = ::isValidEmail)
            InputField(
                state = viewModel.newPassword,
                placeholder = "Enter new password",
                leadingIcon = Icons.Rounded.Lock,
                validator = ::isStrongPassword)

            Height(height = 15)

            ReactiveButton(title = "Change Email") {

            }
        }
    }
}