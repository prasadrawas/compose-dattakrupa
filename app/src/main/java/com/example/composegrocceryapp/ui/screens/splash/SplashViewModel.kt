package com.example.composegrocceryapp.ui.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composegrocceryapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(repository: AppRepository): ViewModel() {
    val isLoggedIn = mutableStateOf(false);
    init {
        isLoggedIn.value = repository.isLoggedIn()
    }
}