package com.example.composegrocceryapp.ui.screens.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.constants.INVALID_EMAIL
import com.example.composegrocceryapp.constants.WEAK_PASSWORD
import com.example.composegrocceryapp.repository.AppRepository
import com.example.composegrocceryapp.utils.isStrongPassword
import com.example.composegrocceryapp.utils.isValidEmail
import com.example.composegrocceryapp.utils.validateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val email = mutableStateOf("")
    val emailErrorMsg = mutableStateOf("")
    val status = mutableStateOf(Any())
    val isLoading = mutableStateOf(false)

    private fun isFormValid(): Boolean{
        if(!validateInput(email.value, emailErrorMsg, INVALID_EMAIL, ::isValidEmail)) return false
        return true
    }

    fun onReset(){
        if(isFormValid()){
            isLoading.value = true
            viewModelScope.launch {
                val res = repository.sendPasswordResetLink(email = email.value)
                isLoading.value = false

                if(res is Boolean){
                    Log.d("Reset", "Status : success")
                    status.value = true
                }else{
                    Log.d("Exception", "Auth: ${(res as Exception).message}")
                    status.value = res
                }
            }
        }
    }

}