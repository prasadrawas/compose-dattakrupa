package com.example.composegrocceryapp.ui.screens.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.constants.INVALID_EMAIL
import com.example.composegrocceryapp.constants.INVALID_PHONE
import com.example.composegrocceryapp.constants.INVALID_TEXT
import com.example.composegrocceryapp.constants.WEAK_PASSWORD
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.repository.AppRepository
import com.example.composegrocceryapp.utils.*
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val name = mutableStateOf("")
    val nameErrorMsg = mutableStateOf("")
    val email = mutableStateOf("")
    val emailErrorMsg = mutableStateOf("")
    val phone = mutableStateOf("")
    val phoneErrorMsg = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordErrorMsg = mutableStateOf("")
    val status = mutableStateOf(Any())
    val isLoading = mutableStateOf(false)


    private fun isFormValid(): Boolean {
        if(!validateInput(name.value.trim(), nameErrorMsg, INVALID_TEXT, ::isValidText)) return false
        if(!validateInput(email.value.trim(), emailErrorMsg, INVALID_EMAIL, ::isValidEmail)) return false
        if(!validateInput(phone.value.trim(), phoneErrorMsg, INVALID_PHONE, ::isValidPhone)) return false
        if(!validateInput(password.value.trim(), passwordErrorMsg, WEAK_PASSWORD, ::isStrongPassword)) return false
        return true
    }


    fun onSignUp(){
        if(isFormValid()){
            isLoading.value = true
            viewModelScope.launch {
                var res = repository.signUpWithEmailPassword(email = email.value.trim(), password = password.value.trim())

                if(res is FirebaseUser){
                    val customer = Customer(
                        email = email.value.trim(),
                        name = name.value.trim(),
                        phone = phone.value.trim()
                    )
                    res = repository.storeCustomerToRemoteDb(customer = customer)
                    isLoading.value = false
                    if(res is String){
                        repository.deleteAllFromLocalDb()
                        customer.id = res
                        repository.storeCustomerToLocalDb(customer = customer)
                        Log.d("SignUp", "Status: Success")
                        status.value = true
                    }else{
                        Log.d("SignUp", "FirestoreException: ${status.value as Exception}")
                        status.value = res
                    }
                }else{
                    Log.d("SignUp", "AuthException: ${(res as Exception).message}")
                    isLoading.value = false
                    status.value = res
                }
            }
        }
    }
}