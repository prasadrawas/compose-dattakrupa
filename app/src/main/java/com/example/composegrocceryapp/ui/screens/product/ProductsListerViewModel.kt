package com.example.composegrocceryapp.ui.screens.product

import android.annotation.SuppressLint
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
class ProductsListerViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {
    @SuppressLint("MutableCollectionMutableState")
    val dataOrException = mutableStateOf(Any())
    val cartSheetData = mutableStateOf(Pair(0, 0))

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

    fun getProductsByCategory(category: String){
        viewModelScope.launch {
            dataOrException.value = true
            val res = repository.getProductsByCategory(category = category)
            if(res is ArrayList<*>){
                dataOrException.value = res as ArrayList<Product>
            }else{
                dataOrException.value = res as Exception
            }
        }
    }
}