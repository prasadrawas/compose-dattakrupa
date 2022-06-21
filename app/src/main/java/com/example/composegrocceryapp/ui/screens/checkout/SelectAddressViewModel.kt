package com.example.composegrocceryapp.ui.screens.checkout

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.model.CustomerDetails
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectAddressViewModel @Inject constructor(private val repository: AppRepository): ViewModel(){
    private val _addressList = MutableStateFlow<List<CustomerDetails>>(emptyList())
    val addressList = _addressList.asStateFlow()

    fun getAllCustomerDetails(){
        viewModelScope.launch {
            try {
                repository.getAllCustomerDetailsAsFlow().distinctUntilChanged().collect {
                    _addressList.value = it
                }
                Log.d("SelectAddressScreen", "CRUD: Success")
            }catch (e: Exception){
                Log.d("SelectAddressScreen", "Exception: ${e.message}")
            }
        }
    }

    fun deleteAddress(details: CustomerDetails){
        viewModelScope.launch {
            try{
                repository.deleteCustomerDetails(details = details)
                Log.d("SelectAddressScreen", "CRUD: Success")
            }catch (e: Exception){
                Log.d("SelectAddressScreen", "Exception: ${e.message}")
            }
        }
    }

}