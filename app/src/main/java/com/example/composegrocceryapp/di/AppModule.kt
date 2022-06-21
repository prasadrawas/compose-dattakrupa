package com.example.composegrocceryapp.di

import android.content.Context
import androidx.room.Room
import com.example.composegrocceryapp.db.AppDatabase
import com.example.composegrocceryapp.db.daos.CartDao
import com.example.composegrocceryapp.db.daos.CustomerDao
import com.example.composegrocceryapp.db.daos.CustomerDetailsDao
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideCustomerDao(database: AppDatabase): CustomerDao = database.CustomerDao()


    @Singleton
    @Provides
    fun provideCartDao(database: AppDatabase): CartDao = database.CartDao()

    @Singleton
    @Provides
    fun provideCustomerDetailsDao(database: AppDatabase): CustomerDetailsDao = database.CustomerDetailsDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "dattakrupa_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}