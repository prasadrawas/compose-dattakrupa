package com.example.composegrocceryapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_details_tbl")
data class CustomerDetails(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val city: String,
    val pinCode: String,
    val landmark: String
)