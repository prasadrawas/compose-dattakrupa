package com.example.composegrocceryapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_tbl")
data class Customer (
    @PrimaryKey
    @NonNull
    var email: String = "",
    var id: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var city: String? = null,
    var pinCode: String? = null,
    var landmark: String? = null
)