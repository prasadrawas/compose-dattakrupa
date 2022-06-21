package com.example.composegrocceryapp.db.daos

import androidx.room.*
import com.example.composegrocceryapp.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_tbl WHERE id=:id")
    suspend fun getCartItem(id: String): Product?

    @Query("SELECT * FROM cart_tbl")
    suspend fun getAllCartItems(): List<Product>?

    @Query("SELECT * FROM cart_tbl")
    fun getAllCartItemsAsFlow(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartItem(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateQuantity(product: Product)

    @Delete
    suspend fun removeCartItem(product: Product)

}