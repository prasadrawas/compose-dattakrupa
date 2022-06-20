package com.example.composegrocceryapp.db.daos

import androidx.room.*
import com.example.composegrocceryapp.model.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer_tbl")
    suspend fun getCustomerFromLocal(): List<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(customer: Customer)

    @Query("DELETE FROM customer_tbl")
    suspend fun deleteAllCustomers()


}