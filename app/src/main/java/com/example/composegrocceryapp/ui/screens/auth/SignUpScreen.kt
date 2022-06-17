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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import com.example.composegrocceryapp.utils.isValidPhone
import com.example.composegrocceryapp.utils.isValidText

@ExperimentalComposeUiApi
//@Preview
@Composable
fun SignUpScreen(
    navigator: NavController,
    viewModel: SignUpViewModel
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
            ScreenTitle(title = stringResource(id = R.string.create_your_naccount))
            Height(height = 25)
            InputField(
                state = viewModel.name,
                placeholder = "Enter name",
                leadingIcon = Icons.Default.Person,
                validator = ::isValidText,
                isValid = viewModel.isValid
            )

            InputField(
                state = viewModel.email,
                placeholder = "Enter email",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                validator = ::isValidEmail,
                isValid = viewModel.isValid
            )

            InputField(
                state = viewModel.phone,
                placeholder = "Enter phone",
                leadingIcon = Icons.Default.Phone,
                keyboardType = KeyboardType.Email,
                validator = ::isValidPhone,
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
            Height(height = 20)
            ReactiveButton(title = stringResource(id = R.string.sign_up)) {

            }
            SignInBox(navigator = navigator)
        }
    }
}


@Composable
private fun SignInBox(navigator: NavController) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(id = R.string.already_have_an_account),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                ),
            )

            TextButton(onClick = {
                navigator.navigate(AppScreens.SignIn.name)
            }) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
    }
}
