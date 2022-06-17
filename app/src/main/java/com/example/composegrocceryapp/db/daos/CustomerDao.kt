package com.example.composegrocceryapp.db.daos

import androidx.room.*
import com.example.composegrocceryapp.model.Customer

@Dao
interface CustomerDao {
//    @Query("SELECT * FROM customer_tbl WHERE email=:email")
//    suspend fun getUser(email: String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(customer: Customer)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(customer: Customer)

    @Delete
    suspend fun deleteUser(customer: Customer)
}