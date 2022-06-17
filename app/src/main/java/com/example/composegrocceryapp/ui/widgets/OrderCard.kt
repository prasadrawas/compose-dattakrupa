package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.model.Order
import com.example.composegrocceryapp.ui.theme.Green500


@Composable
fun OrderCard(order: Order) {
    val icons = listOf(
        Icons.Rounded.Widgets,
        Icons.Rounded.AllInbox,
        Icons.Rounded.Outbox,
    )

    Card(modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icons.random(),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Green500
            )
            Width(width = 20)
            Column {
                Text(
                    text = order.customerInfo?.name!!, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                IconText(icon = Icons.Rounded.Phone, text = order.customerInfo?.phone!!)
                IconText(icon = Icons.Rounded.Email, text = order.customerInfo?.email!!)


                Text(
                    text = "Date : ${order.date} | ${order.time}", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}