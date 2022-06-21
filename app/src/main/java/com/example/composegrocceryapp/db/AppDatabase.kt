package com.example.composegrocceryapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composegrocceryapp.db.daos.CartDao
import com.example.composegrocceryapp.db.daos.CustomerDao
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.model.Product

@Database(entities = [Customer::class, Product::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun CustomerDao(): CustomerDao
    abstract fun CartDao(): CartDao
}