package com.example.composegrocceryapp.model

import com.example.composegrocceryapp.utils.toDateFormat
import com.example.composegrocceryapp.utils.toTimeFormat
import java.util.*

data class Order (
    var id: String? = null,
    var date: String? = toDateFormat(Date()),
    var time: String? = toTimeFormat(Date()),
    var customerInfo: Customer? = null,
    var items: List<OrderItem>? = null
)