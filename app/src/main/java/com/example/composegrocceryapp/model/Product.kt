package com.example.composegrocceryapp.model

data class Product(
    var id: String? = null,
    val name: String? = null,
    val category: String? = null,
    val image: String? = null,
    val price: Int? = null,
    val rating: Int? = null,
    var quantity: Int = 0
)