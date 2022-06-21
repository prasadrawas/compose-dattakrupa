package com.example.composegrocceryapp.db.daos

import androidx.room.*
import com.example.composegrocceryapp.model.CustomerDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDetailsDao {

    @Insert
    suspend fun insertCustomerDetails(details: CustomerDetails)

    @Delete
    suspend fun deleteCustomerDetails(details: CustomerDetails)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCustomerDetails(details: CustomerDetails)

    @Query("SELECT * FROM customer_details_tbl WHERE id=:id")
    suspend fun getCustomerDetails(id: String): CustomerDetails?

    @Query("SELECT * FROM customer_details_tbl")
    fun getAllCustomerDetailsAsFlow(): Flow<List<CustomerDetails>>




}