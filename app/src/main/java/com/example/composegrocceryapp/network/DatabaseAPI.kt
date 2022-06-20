package com.example.composegrocceryapp.network

import com.example.composegrocceryapp.constants.USERS
import com.example.composegrocceryapp.model.Customer
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseAPI @Inject constructor(){
    private val db = Firebase.firestore


    suspend fun storeUserData(customer: Customer) : Any {
        val res : Any
        res = try {
            val ref = db.collection(USERS).add(customer).await()
            ref.id
        }catch (e : Exception){
            e
        }
        return res
    }

    suspend fun getUserData(email: String) : Any? {
        val res = try {
            val ref = db.collection(USERS).whereEqualTo("email", email).get().await()
            if (ref == null || ref.isEmpty) {
                null
            } else {
                val user = ref.documents[0].toObject(Customer::class.java)
                user?.id = ref.documents[0].id
                user
            }
        }catch (e : Exception){
            e
        }
        return res
    }

    /*

    suspend fun getCategories() : Any {
        var res: Any

        try{
            val documents = db.collection(FirebaseConstants.CATEGORIES).get().await()
            res = ArrayList<CategoryModel>()
            for(doc in documents?.documents!!){
                val item = doc?.toObject(CategoryModel::class.java)
                item?.id = doc?.id
                if (item != null) {
                    AppConstants.categories.add(item.name?:"")
                    res.add(item)
                }
            }
        }catch (e : Exception){
            res = e
        }
        return res
    }

    suspend fun getHotProducts() : Any{
        var res: Any

        try {
            val documents = db.collection(FirebaseConstants.PRODUCTS).limit(10).get().await()
            res = ArrayList<ProductModel>()
            for(doc in documents?.documents!!){
                val item = doc?.toObject(ProductModel::class.java)
                item?.id = doc?.id
                if (item != null) {
                    res.add(item)
                }
            }
        }catch (e : Exception){
            res = e
        }
        return res
    }

    suspend fun getProductItem( id : String) : Any {
        val res: Any = try {
            val result = db.collection(FirebaseConstants.PRODUCTS).document(id).get().await()
            val product = result.toObject(ProductModel::class.java)!!
            product.id = result?.id
            product
        }catch (e : Exception){
            e
        }

        return res
    }

    suspend fun getProductsByCategory(price: Int, category : String) : Any {
        var res: Any

        try {
            val documents: Any
            documents = when (price) {
                0 -> db.collection(FirebaseConstants.PRODUCTS).whereEqualTo("category", category).get().await()
                1 -> db.collection(FirebaseConstants.PRODUCTS).whereEqualTo("category", category).orderBy("price", Query.Direction.ASCENDING).get().await()
                else -> db.collection(FirebaseConstants.PRODUCTS).whereEqualTo("category", category).orderBy("price", Query.Direction.DESCENDING).get().await()
            }
            res = ArrayList<ProductModel>()
            for(doc in documents?.documents!!){
                val item = doc?.toObject(ProductModel::class.java)
                item?.id = doc?.id
                if (item != null) {
                    res.add(item)
                }
            }
        }catch (e : Exception){
            res = e
        }
        return res
    }

    suspend fun makeOrder(order: OrderModel): Any{
        val res = try {
            db.collection(FirebaseConstants.ORDERS).add(order)
            true
        }catch (e: Exception){
            e
        }
        return res
    }*/

}