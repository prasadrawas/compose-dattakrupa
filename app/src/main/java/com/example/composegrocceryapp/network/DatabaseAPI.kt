package com.example.composegrocceryapp.network

import com.example.composegrocceryapp.constants.CATEGORIES
import com.example.composegrocceryapp.constants.CUSTOMERS
import com.example.composegrocceryapp.constants.PRODUCTS
import com.example.composegrocceryapp.model.Category
import com.example.composegrocceryapp.model.Customer
import com.example.composegrocceryapp.model.Product
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseAPI  @Inject constructor() {
    private val db = Firebase.firestore


    suspend fun storeUserData(customer: Customer): Any {
        val res: Any
        res = try {
            val ref = db.collection(CUSTOMERS).add(customer).await()
            ref.id
        } catch (e: Exception) {
            e
        }
        return res
    }

    suspend fun getUserData(email: String): Any? {
        val res = try {
            val ref = db.collection(CUSTOMERS).whereEqualTo("email", email).get().await()
            if (ref == null || ref.isEmpty) {
                null
            } else {
                val user = ref.documents[0].toObject(Customer::class.java)
                user?.id = ref.documents[0].id
                user
            }
        } catch (e: Exception) {
            e
        }
        return res
    }

    suspend fun getCategories(): Any {
        var res: Any

        try {
            val documents = db.collection(CATEGORIES).get().await()
            res = ArrayList<Category>()
            for (doc in documents?.documents!!) {
                val item = doc?.toObject(Category::class.java)
                item?.id = doc?.id
                if (item != null) {
                    res.add(item)
                }
            }
        } catch (e: Exception) {
            res = e
        }
        return res
    }

    suspend fun getHotProducts(): Any {
        var res: Any

        try {
            val documents = db.collection(PRODUCTS).limit(10).get().await()
            res = ArrayList<Product>()
            for (doc in documents?.documents!!) {
                val item = doc?.toObject(Product::class.java)
                item?.id = doc?.id.toString()
                if (item != null) {
                    res.add(item)
                }
            }
        } catch (e: Exception) {
            res = e
        }
        return res
    }

    suspend fun getProductItem(id: String): Any {
        val res: Any = try {
            val result = db.collection(PRODUCTS).document(id).get().await()
            val product = result.toObject(Product::class.java)!!
            product.id = result?.id.toString()
            product
        } catch (e: Exception) {
            e
        }

        return res
    }

    suspend fun getProductsByCategory(category: String): Any {
        var res: Any

        try {
            val documents = db.collection(PRODUCTS).whereEqualTo("category", category).get().await()
            res = ArrayList<Product>()
            for (doc in documents?.documents!!) {
                val item = doc?.toObject(Product::class.java)
                item?.id = doc?.id.toString()
                if (item != null) {
                    res.add(item)
                }
            }
        } catch (e: Exception) {
            res = e
        }
        return res
    }

}