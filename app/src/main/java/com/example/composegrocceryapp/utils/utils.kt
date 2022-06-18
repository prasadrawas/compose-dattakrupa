package com.example.composegrocceryapp.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import com.example.composegrocceryapp.constants.BLANK_VALUE
import com.example.composegrocceryapp.constants.REQUIRED
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun toDateFormat(date: Date): String{
    return SimpleDateFormat("EEE, MMM, d").format(date)
}

@SuppressLint("SimpleDateFormat")
fun toTimeFormat(date: Date): String{
    return SimpleDateFormat("hh:mm:aa").format(date)
}

fun validateInput(
    inputValue: String,
    error: MutableState<String>,
    errorMsg: String,
    validator: (String) -> Boolean
): Boolean{
    return when {
        inputValue.isEmpty() -> {
            error.value = REQUIRED
            false
        }
        !validator(inputValue) -> {
            error.value = errorMsg
            false
        }
        else -> {
            error.value = BLANK_VALUE
            true
        }
    }
}
