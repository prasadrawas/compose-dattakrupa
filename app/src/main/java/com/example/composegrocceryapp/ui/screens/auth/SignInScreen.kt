package com.example.composegrocceryapp.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Height
import com.example.composegrocceryapp.components.InputField
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.ui.widgets.ScreenTitle
import com.example.composegrocceryapp.utils.isStrongPassword
import com.example.composegrocceryapp.utils.isValidEmail

@ExperimentalComposeUiApi
//@Preview
@Composable
fun SignInScreen(
    navigator: NavController,
    viewModel: SignInViewModel
) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            ScreenTitle(title = stringResource(id = R.string.log_in_to_your_n_account))
            Height(height = 50)
            InputField(
                state = viewModel.email,
                placeholder = "Enter email",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                validator = ::isValidEmail,
                isValid = viewModel.isValid
            )

            InputField(
                state = viewModel.password,
                placeholder = "Enter password",
                leadingIcon = Icons.Default.Lock,
                validator = ::isStrongPassword,
                isValid = viewModel.isValid,
                obscureText = true
            )
            ForgotPassBox(navigator = navigator)
            ReactiveButton(title = stringResource(id = R.string.sign_in)) {

            }
            SignUpBox(navigator = navigator)
        }
    }
}


@Composable
private fun ForgotPassBox(navigator: NavController) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = {
            navigator.navigate(AppScreens.ResetPassword.name)
        }) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}

@Composable
private fun SignUpBox(navigator: NavController) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxWidth().padding(top = 100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(id = R.string.don_t_have_an_account),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                ),
            )

            TextButton(onClick = {
                navigator.navigate(AppScreens.SignUp.name)
            }) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
    }
}
