package com.example.composegrocceryapp.ui.screens.product

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val repository: AppRepository) :
    ViewModel() {
    val dataOrException = mutableStateOf(Any())
    val quantity = mutableStateOf(0)

    fun getProductItem(id: String) {
        viewModelScope.launch {
            dataOrException.value = true
            val res = repository.getProductItem(id = id)
            if (res is Product) {
                Log.d("Home", "Data: $res")
                dataOrException.value = res
            } else {
                Log.d("Home", "Exception: $res")
                dataOrException.value = res as Exception
            }
        }
    }

    fun getCartQuantity(id: String){
        viewModelScope.launch {
            try {
                val res = repository.getCartItem(id = id)
                if(res!=null)
                    quantity.value = res.quantity
            }catch (e: Exception){
                Log.d("ROOM", "Exception: ${e.message}")
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            try {
                val res = repository.getCartItem(id = product.id)
                if (res != null) {
                    res.quantity++
                    quantity.value = res.quantity
                    repository.updateQuantity(product = res)
                } else {
                    product.quantity = 1
                    quantity.value = 1
                    repository.addCartItem(product = product)
                }
                Log.d("ROOM", "CRUD: Success")
            } catch (e: Exception) {
                Log.d("ROOM", "Exception: ${e.message}")
            }
        }
    }

    fun removeFromCart(id: String) {
        viewModelScope.launch {
            try {
                val res = repository.getCartItem(id = id)
                if (res != null) {
                    res.quantity--
                    quantity.value = res.quantity
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