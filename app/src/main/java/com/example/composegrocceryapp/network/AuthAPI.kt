package com.example.composegrocceryapp.network
import com.google.firebase.FirebaseException
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthAPI @Inject constructor() {

    private val auth : FirebaseAuth = Firebase.auth

    suspend fun signUpWithEmailPassword(email : String, password : String) : Any? {
        val res: Any? = try {
            val authResult = auth.createUserWithEmailAndPassword(email,password).await()
            authResult?.user
        }catch (e : Exception){
            e
        }
        return res
    }


    suspend fun signInWithEmailPassword(email : String, password: String) : Any? {
        val res = try {
            if(isLoggedIn())
                auth.currentUser
            val authResult = auth
                .signInWithEmailAndPassword(email, password)
                .await()
            authResult?.user
        } catch (e: Exception) {
            e
        }
        return res
    }


    suspend fun sendPasswordResetLink(email : String) : Any {
        val result = try {
            auth.sendPasswordResetEmail(email).await()
            true
        }catch (e : FirebaseException){
            e
        }
        return result
    }


    fun logout() : Any{
        val res = try {
            auth.signOut()
            true
        }catch (e: Exception){
            e
        }
        return res
    }


     fun isLoggedIn() : Boolean{
        try {
            if(auth.currentUser!=null)
                return true
            return false
        }catch (e: Exception){
            return false
        }
    }

    suspend fun changeEmail(email: String, newEmail: String, password: String) : Any{
        val res = try {
            val credentials = EmailAuthProvider.getCredential(email, password)
            auth.currentUser?.reauthenticate(credentials)?.await()
            auth.currentUser?.updateEmail(newEmail)
            true
        }catch (e: Exception){
            e
        }
        return res
    }

    suspend fun changePassword(email: String, password: String, newPassword: String) : Any{
        val res = try {
            val credentials = EmailAuthProvider.getCredential(email, password)
            auth.currentUser?.reauthenticate(credentials)?.await()
            auth.currentUser?.updatePassword(newPassword)
            true
        }catch (e: Exception){
            e
        }
        return res
    }

}