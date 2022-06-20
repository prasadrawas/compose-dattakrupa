package com.example.composegrocceryapp.repository
import com.example.composegrocceryapp.db.daos.CustomerDao
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.network.AuthAPI
import com.example.composegrocceryapp.network.DatabaseAPI
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val auth: AuthAPI,
    private val fireDb: DatabaseAPI,
    private val customerDao: CustomerDao
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

    suspend fun getCustomerFromLocal(email: String): Customer? {
        return customerDao.getCustomerFromLocal(email = email)
    }

    suspend fun deleteAllFromLocalDb(){
        return customerDao.deleteAllCustomers()
    }


        /*suspend fun getCategories() : Any {
        return db.getCategories()
    }

    suspend fun getHotProducts() : Any {
        return db.getHotProducts()
    }

    suspend fun getProductItem(id : String) : Any {
        return db.getProductItem(id)
    }

    suspend fun getProductsByCategory(price: Int, category : String) : Any {
        return db.getProductsByCategory(price, category)
    }

    fun isLoggedIn() : Boolean {
        return auth.isLoggedIn()
    }
    }*/

        /*suspend fun makeOrder(order: OrderModel) : Any{
        return db.makeOrder(order)
    }*/

}