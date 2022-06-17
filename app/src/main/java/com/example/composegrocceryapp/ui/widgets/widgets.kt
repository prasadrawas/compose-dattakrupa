package com.example.composegrocceryapp.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.ui.theme.Green200

@Composable
fun ScreenTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Green200
        ),
        modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
    )
}


@Composable
fun BoldText(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = color
        ),
        modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
    )
}


@Composable
fun CustomDetailsSection() {
    Column(modifier = Modifier.padding(start = 12.dp, top = 15.dp, bottom = 15.dp, end = 12.dp)) {
        BoldText(text = "Prasad Rawas")
        IconText(icon = Icons.Rounded.Email, text = "prasadrawas.om@gmail.com")
        IconText(icon = Icons.Rounded.Phone, text = "7276974942")
        IconText(
            icon = Icons.Rounded.LocationCity,
            text = "At. Pategoan, Tq. Paithan, Paithan 431107"
        )
    }
}

@Composable
fun IconText(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(14.dp)
        )
        Width(width = 10)
        Text(
            text = text, style = TextStyle(
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.poppins_light))
            )
        )
    }
}


@Composable
fun RatingBar(count: Int) {
    LazyRow {
        items(5) {
            Icon(
                imageVector = Icons.Rounded.Star, contentDescription = null,
                tint = if (it < count) Green200 else Color.LightGray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun ProductImage(url: String) {
    Image(
        painter = rememberAsyncImagePainter(url), contentDescription = null,
        modifier = Modifier
            .height(140.dp)
            .padding(10.dp)
    )
}

@Composable
fun AnnotatedBoldText(boldText: String, simpleText: String, size: Int = 16) {
    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = size.sp
            )
        ) {
            append(boldText)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = size.sp
            )
        ) {
            append(simpleText)
        }

    })
}