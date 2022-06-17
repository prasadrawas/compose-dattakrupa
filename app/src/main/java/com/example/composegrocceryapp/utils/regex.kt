package com.example.composegrocceryapp.utils

fun isValidEmail(email: String): Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
}

fun isValidPhone(phone: String): Boolean{
    return android.util.Patterns.PHONE.matcher(phone).matches();
}

fun isValidText(text: String): Boolean{
    return text.trim().length>2;
}

fun isStrongPassword(password: String): Boolean{
    return password.trim().length>=6;
}