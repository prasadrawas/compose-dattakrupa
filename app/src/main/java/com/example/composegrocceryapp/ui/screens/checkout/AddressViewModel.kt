package com.example.composegrocceryapp.ui.screens.checkout

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(): ViewModel() {
    val name: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val phone: MutableState<String> = mutableStateOf("")
    val city: MutableState<String> = mutableStateOf("")
    val pinCode: MutableState<String> = mutableStateOf("")
    val fullAddress: MutableState<String> = mutableStateOf("")
}