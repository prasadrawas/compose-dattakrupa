package com.example.composegrocceryapp.ui.screens.cart

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private val _cartList = MutableStateFlow<List<Product>>(emptyList())
    val cartList = _cartList.asStateFlow()

    fun getAllCartItems() {
        viewModelScope.launch {
            try {
                repository.getAllCartItemsAsFlow().distinctUntilChanged().collect {
                    _cartList.value = it
                }
                Log.d("CartScreen", "CRUD: Success")
            } catch (e: Exception) {
                Log.d("CartScreen", "Exception: ${e.message}")
            }
        }
    }

    fun deleteItemFromCart(product: Product) {
        viewModelScope.launch {
            try {
                repository.removeCartItem(product = product)
                Log.d("CartScreen", "CRUD: Success")
            } catch (e: Exception) {
                Log.d("CartScreen", "Exception: ${e.message}")
            }
        }
    }

    fun addToCart(id: String) {
        viewModelScope.launch {
            try {
                val res = repository.getCartItem(id = id)
                if (res != null) {
                    res.quantity++
                    repository.updateQuantity(product = res)
                    Log.d("CartScreen", "CRUD: Success")
                }
            } catch (e: Exception) {
                Log.d("CartScreen", "Exception: ${e.message}")
            }
        }
    }

    fun removeFromCart(id: String) {
        viewModelScope.launch {
            try {
                val res = repository.getCartItem(id = id)
                if (res != null) {
                    res.quantity--
                    if (res.quantity == 0) {
                        repository.removeCartItem(res)
                    } else {
                        repository.updateQuantity(product = res)
                    }
                }
                Log.d("ROOM", "CRUD: Success")
            } catch (e: Exception) {
                Log.d("ROOM", "Exception: ${e.message}")
            }
        }
    }

}