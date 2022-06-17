package com.example.composegrocceryapp.ui.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val phone = mutableStateOf("")
    val password = mutableStateOf("")
    val isValid = mutableStateOf(true)
}