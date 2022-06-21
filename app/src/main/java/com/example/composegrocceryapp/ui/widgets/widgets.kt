package com.example.composegrocceryapp.ui.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.navigation.AppScreens
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

@Composable
fun DisplayException(exception: Exception) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "${exception.message}")
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BottomCartSheet(data: Pair<Int, Int>, onClick: () -> Unit) {
    val btnWidth = (LocalConfiguration.current.screenWidthDp.toFloat() * 0.97f).toInt()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .width(btnWidth.dp)
                .clickable { onClick.invoke() }
                .padding(bottom = 6.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Green200,
        ) {
            Column(
                modifier = Modifier
                    .width(btnWidth.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 4.dp, top = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Items added : ${data.first}",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                )
                Row(
                    modifier = Modifier.width(btnWidth.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total amount: ₹ ${data.second}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_light)),
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "Checkout",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

