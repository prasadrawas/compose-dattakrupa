package com.example.composegrocceryapp.ui.screens.checkout

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.model.CustomerDetails
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {
    val customerDetails = mutableStateOf(Any())

    fun getCustomerDetails(id: String){
        viewModelScope.launch {
            try {
                val res = repository.getCustomerDetails(id = id)
                if(res!=null)
                    customerDetails.value = res
                Log.d("CheckoutScreen", "CRUD: Success")
            }catch (e: Exception){
                Log.d("CheckoutScreen", "Exception: ${e.message}")
            }
        }
    }

}