package com.example.composegrocceryapp.ui.screens.checkout

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.composegrocceryapp.constants.INVALID_EMAIL
import com.example.composegrocceryapp.constants.INVALID_PHONE
import com.example.composegrocceryapp.constants.INVALID_TEXT
import com.example.composegrocceryapp.model.CustomerDetails
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.repository.AppRepository
import com.example.composegrocceryapp.utils.isValidEmail
import com.example.composegrocceryapp.utils.isValidPhone
import com.example.composegrocceryapp.utils.isValidText
import com.example.composegrocceryapp.utils.validateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val name: MutableState<String> = mutableStateOf("")
    val nameErrorMsg: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val emailErrorMsg: MutableState<String> = mutableStateOf("")
    val phone: MutableState<String> = mutableStateOf("")
    val phoneErrorMsg: MutableState<String> = mutableStateOf("")
    val city: MutableState<String> = mutableStateOf("")
    val cityErrorMsg: MutableState<String> = mutableStateOf("")
    val pinCode: MutableState<String> = mutableStateOf("")
    val pinCodeErrorMsg: MutableState<String> = mutableStateOf("")
    val landmark: MutableState<String> = mutableStateOf("")
    val landmarkErrorMsg: MutableState<String> = mutableStateOf("")


    private fun isFormValid(): Boolean {
        if (!validateInput(
                inputValue = name.value.trim(),
                error = nameErrorMsg,
                errorMsg = INVALID_TEXT,
                validator = ::isValidText
            )
        ) return false
        if (!validateInput(
                inputValue = email.value.trim(),
                error = emailErrorMsg,
                errorMsg = INVALID_EMAIL,
                validator = ::isValidEmail
            )
        ) return false
        if (!validateInput(
                inputValue = phone.value.trim(),
                error = phoneErrorMsg,
                errorMsg = INVALID_PHONE,
                validator = ::isValidPhone
            )
        ) return false
        if (!validateInput(
                inputValue = city.value.trim(),
                error = cityErrorMsg,
                errorMsg = INVALID_TEXT,
                validator = ::isValidText
            )
        ) return false
        if (!validateInput(
                inputValue = pinCode.value.trim(),
                error = pinCodeErrorMsg,
                errorMsg = INVALID_TEXT,
                validator = ::isValidText
            )
        ) return false
        if (!validateInput(
                inputValue = landmark.value.trim(),
                error = landmarkErrorMsg,
                errorMsg = INVALID_TEXT,
                validator = ::isValidText
            )
        ) return false

        return true
    }

    fun onSaveAddress(navigator: NavController) {
        if(isFormValid()){
            val details = CustomerDetails(
                name = name.value.trim(),
                email = email.value.trim(),
                phone = phone.value.trim(),
                city = city.value.trim(),
                pinCode = pinCode.value.trim(),
                landmark = landmark.value.trim()
            )

            viewModelScope.launch {
                try {
                    repository.insertCustomerDetails(details = details)
                    Log.d("AddressScreen", "CRUD: Success")
                    navigator.navigate(AppScreens.SelectAddress.name)
                }catch (e: Exception) {
                    Log.d("AddressScreen", "Exception: ${e.message}")
                }
            }
        }
    }


}