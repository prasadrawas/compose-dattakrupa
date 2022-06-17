package com.example.composegrocceryapp.ui.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(): ViewModel() {
    val currentEmail = mutableStateOf("")
    val newEmail = mutableStateOf("")
    val password = mutableStateOf("")
}