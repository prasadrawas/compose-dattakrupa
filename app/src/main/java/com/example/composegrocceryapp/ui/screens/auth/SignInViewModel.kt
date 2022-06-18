package com.example.composegrocceryapp.ui.screens.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composegrocceryapp.constants.*
import com.example.composegrocceryapp.utils.isStrongPassword
import com.example.composegrocceryapp.utils.isValidEmail
import com.example.composegrocceryapp.utils.validateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    val email = mutableStateOf("")
    val emailErrorMsg = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordErrorMsg = mutableStateOf("")
    val isLoading = mutableStateOf(false)


    fun isFormValid(): Boolean{
        if(!validateInput(email.value, emailErrorMsg, INVALID_EMAIL, ::isValidEmail)) return false
        if(!validateInput(password.value, passwordErrorMsg, WEAK_PASSWORD, ::isStrongPassword)) return false
        return true
    }

}
