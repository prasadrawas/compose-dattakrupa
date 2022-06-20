package com.example.composegrocceryapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.constants.BLANK_NAME
import com.example.composegrocceryapp.constants.BLANK_VALUE

@ExperimentalComposeUiApi
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    state: MutableState<String> = mutableStateOf(""),
    errorMsg: MutableState<String> = mutableStateOf(""),
    placeholder: String,
    leadingIcon: ImageVector,
    obscureText: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    validator: (String) -> Boolean,
    isValid: MutableState<Boolean> = mutableStateOf(true),
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true
) {
    val controller = LocalSoftwareKeyboardController.current

    val isPasswordVisible = rememberSaveable {
        mutableStateOf(!obscureText)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        OutlinedTextField(
            modifier = modifier
                .padding(top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            value = state.value,
            onValueChange = { state.value = it },
            label = {
                Text(
                    text = placeholder,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            },
            visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
            trailingIcon = {
                if (obscureText) {
                    IconButton(onClick = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    }) {
                        Icon(
                            imageVector = if (isPasswordVisible.value) Icons.Default.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            singleLine = true,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = KeyboardActions {
                if (!validator(state.value.trim())) return@KeyboardActions
                controller?.hide()
            },
            isError = errorMsg.value.isNotEmpty()
        )

        if (errorMsg.value.isNotEmpty()) {
            Text(
                text = errorMsg.value, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_extra_light)),
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 12.sp,
                    color = Color.Red
                )
            )
        }
    }

}