package com.example.composegrocceryapp.ui.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor() : ViewModel() {
    val email = mutableStateOf("")
    val isValid = mutableStateOf(true)
}