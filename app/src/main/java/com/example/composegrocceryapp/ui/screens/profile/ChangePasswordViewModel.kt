package com.example.composegrocceryapp.ui.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(): ViewModel() {
    val email = mutableStateOf("")
    val currentPassword = mutableStateOf("")
    val newPassword = mutableStateOf("")
}