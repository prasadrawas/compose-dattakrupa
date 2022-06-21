package com.example.composegrocceryapp.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegrocceryapp.model.Category
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val name = mutableStateOf("")

    @SuppressLint("MutableCollectionMutableState")
    val categoryList = mutableStateOf(ArrayList<Category>())

    @SuppressLint("MutableCollectionMutableState")
    val productList = mutableStateOf(ArrayList<Product>())
    val cartSheetData = mutableStateOf(Pair(0, 0))


    fun getCustomerName() {
        viewModelScope.launch {
            val list = repository.getCustomerFromLocal()
            if (list.isNotEmpty()) {
                name.value += list[0].name
            }
        }
    }

    fun getCartItems() {
        viewModelScope.launch {
            try {
                val res = repository.getAllCartItems()
                if (res != null) {
                    var total = 0;
                    res.forEach {
                        total += (it.price ?: 0) * (it.quantity)
                    }
                    cartSheetData.value = Pair(res.size, total)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            try {
                val res = repository.getCartItem(id = product.id)
                if (res != null) {
                    res.quantity++
                    repository.updateQuantity(product = res)
                } else {
                    product.quantity = 1
                    repository.addCartItem(product = product)
                }
                getCartItems()
                Log.d("ROOM", "CRUD: Success")
            } catch (e: Exception) {
                Log.d("ROOM", "Exception: ${e.message}")
            }
        }
    }

     fun getCategories() {
        viewModelScope.launch {
            val res = repository.getCategories()
            if (res is ArrayList<*>) {
                categoryList.value = res as ArrayList<Category>
            }
        }
    }

     fun getProducts() {
        viewModelScope.launch {
            val res = repository.getHotProducts()
            if (res is ArrayList<*>) {
                productList.value = res as ArrayList<Product>
            }
        }
    }

}