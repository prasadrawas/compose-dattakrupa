package com.example.composegrocceryapp.repository
import com.example.composegrocceryapp.db.daos.CartDao
import com.example.composegrocceryapp.db.daos.CustomerDao
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.model.Product
import com.example.composegrocceryapp.network.AuthAPI
import com.example.composegrocceryapp.network.DatabaseAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val auth: AuthAPI,
    private val fireDb: DatabaseAPI,
    private val customerDao: CustomerDao,
    private val cartDao: CartDao
    ) {

    suspend fun signInWithEmailPassword(email: String, password: String): Any? {
        return auth.signInWithEmailPassword(email, password)
    }

    suspend fun signUpWithEmailPassword(email: String, password: String): Any? {
        return auth.signUpWithEmailPassword(email, password)
    }

    suspend fun sendPasswordResetLink(email: String): Any {
        return auth.sendPasswordResetLink(email)
    }

    fun logout(): Any {
        return auth.logout()
    }

    fun isLoggedIn() : Boolean {
        return auth.isLoggedIn()
    }

    suspend fun changeEmail(email: String, newEmail: String, password: String): Any {
        return auth.changeEmail(email, newEmail, password)
    }

    suspend fun changePassword(email: String, password: String, newPassword: String): Any {
        return auth.changeEmail(email, password, newPassword)
    }


    suspend fun storeCustomerToRemoteDb(customer: Customer): Any {
        return fireDb.storeUserData(customer)
    }

    suspend fun storeCustomerToLocalDb(customer: Customer): Any {
        return customerDao.insertCustomer(customer)
    }

    suspend fun getCustomerFromRemote(email: String): Any? {
        return fireDb.getUserData(email)
    }

    suspend fun getCustomerFromLocal(): List<Customer> {
        return customerDao.getCustomerFromLocal()
    }

    suspend fun deleteAllFromLocalDb(){
        return customerDao.deleteAllCustomers()
    }

    suspend fun getCategories() : Any {
        return fireDb.getCategories()
    }

    suspend fun getHotProducts() : Any {
        return fireDb.getHotProducts()
    }


    suspend fun getProductItem(id : String) : Any {
        return fireDb.getProductItem(id)
    }

    suspend fun getProductsByCategory(category: String) : Any {
        return fireDb.getProductsByCategory(category = category)
    }

    suspend fun getCartItem(id: String): Product?{
        return cartDao.getCartItem(id = id)
    }

    suspend fun addCartItem(product: Product) {
        return cartDao.addCartItem(product = product)
    }

    suspend fun updateQuantity(product: Product) {
        return cartDao.updateQuantity(product = product)
    }

    suspend fun removeCartItem(product: Product) {
        return cartDao.removeCartItem(product = product)
    }

    suspend fun getAllCartItems(): List<Product>? {
        return cartDao.getAllCartItems()
    }

    fun getAllCartItemsAsFlow(): Flow<List<Product>> {
        return cartDao.getAllCartItemsAsFlow().flowOn(Dispatchers.IO).conflate()
    }

}