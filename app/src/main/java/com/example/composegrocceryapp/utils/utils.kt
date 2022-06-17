package com.example.composegrocceryapp.utils

import android.annotation.SuppressLint
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

