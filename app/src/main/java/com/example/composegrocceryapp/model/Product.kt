package com.example.composegrocceryapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_tbl")
data class Product(
    @PrimaryKey
    @NonNull
    var id: String = "",
    val name: String? = null,
    val category: String? = null,
    val image: String? = null,
    val price: Int? = null,
    val rating: Int? = null,
    var quantity: Int = 0
)