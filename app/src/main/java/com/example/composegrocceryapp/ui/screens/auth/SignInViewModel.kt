package com.example.composegrocceryapp.ui.screens.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.constants.*
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.repository.AppRepository
import com.example.composegrocceryapp.utils.isStrongPassword
import com.example.composegrocceryapp.utils.isValidEmail
import com.example.composegrocceryapp.utils.validateInput
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val email = mutableStateOf("")
    val emailErrorMsg = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordErrorMsg = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val status = mutableStateOf(Any())


    private fun isFormValid(): Boolean{
        if(!validateInput(email.value, emailErrorMsg, INVALID_EMAIL, ::isValidEmail)) return false
        if(!validateInput(password.value, passwordErrorMsg, WEAK_PASSWORD, ::isStrongPassword)) return false
        return true
    }

    fun onSignIn(){
        if(isFormValid()){
            isLoading.value = true
            viewModelScope.launch {
                var res = repository.signInWithEmailPassword(email = email.value, password = password.value)
                if(res is FirebaseUser){
                     res = repository.getCustomerFromLocal(email = email.value)
                    if(res!=null){
                        Log.d("SignIn", "Status : success")
                        isLoading.value = false
                        status.value = true
                    }else{
                        res = repository.getCustomerFromRemote(email = email.value)
                        isLoading.value = false
                        if(res is Customer){
                            repository.storeCustomerToLocalDb(res)
                            Log.d("SignIn", "Status : success")
                            status.value = true
                        }else{
                            Log.d("SignIn", "FirestoreException: ${(res as Exception).message}")
                            status.value = res
                        }
                    }
                }else{
                    Log.d("SignIn", "AuthException: ${(res as Exception).message}")
                    isLoading.value = false
                    status.value = res
                }
            }
        }
    }


}
