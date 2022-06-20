package com.example.composegrocceryapp.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val name = mutableStateOf("")

    init {
        viewModelScope.launch {
            val list = repository.getCustomerFromLocal()
            if(list.isNotEmpty()){
                name.value+= list[0].name
            }
        }
    }
}