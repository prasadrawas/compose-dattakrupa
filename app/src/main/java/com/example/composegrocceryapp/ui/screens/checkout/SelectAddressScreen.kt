package com.example.composegrocceryapp.ui.screens.checkout

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.composegrocceryapp.R
import com.example.composegrocceryapp.components.ReactiveAppbar
import com.example.composegrocceryapp.components.ReactiveButton
import com.example.composegrocceryapp.components.Width
import com.example.composegrocceryapp.model.CustomerDetails
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.ui.widgets.BoldText
import com.example.composegrocceryapp.ui.widgets.IconText
import com.example.composegrocceryapp.utils.LifecycleHandler
import com.example.composegrocceryapp.utils.showToast

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectAddressScreen(
    navigator: NavHostController,
    viewModel: SelectAddressViewModel
) {
    LifecycleHandler { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.getAllCustomerDetails()
        }
    }

    val selected = mutableStateOf(-1)
    val context = LocalContext.current
    Scaffold(topBar = { ReactiveAppbar(navigator = navigator) }) {
        val list = viewModel.addressList.collectAsState().value
        MainContent(viewModel = viewModel, navigator = navigator, selected = selected, list = list)
        CheckoutButton(navigator = navigator, selected = selected, list = list, context = context)
    }
}

@Composable
fun MainContent(
    viewModel: SelectAddressViewModel,
    navigator: NavController,
    selected: MutableState<Int>,
    list: List<CustomerDetails>
) {
    LazyColumn {
        item { AddNewAddressButton(navigator) }
        if (list.isEmpty()) {
            item { if (list.isEmpty()) NoDataSection() }
        } else {
            itemsIndexed(list) { i, item ->
                SelectAddressCard(
                    viewModel = viewModel,
                    address = item,
                    selected = selected,
                    index = i
                )
            }
        }
    }
}

@Composable
private fun AddNewAddressButton(navigator: NavController) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .clickable { navigator.navigate(AppScreens.Address.name) }) {
        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = null)
            Width(width = 10)
            Text(
                text = "Add new address", style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
private fun NoDataSection() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "No address to display here", style = TextStyle(
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                fontSize = 15.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SelectAddressCard(
    address: CustomerDetails,
    selected: MutableState<Int>,
    index: Int,
    viewModel: SelectAddressViewModel
) {
    val rowWidth = LocalConfiguration.current.screenWidthDp.toFloat();
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .clickable { selected.value = index }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RadioButton(
                modifier = Modifier.width((rowWidth * 0.1f).toInt().dp),
                selected = selected.value == index, onClick = { selected.value = index })

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width((rowWidth * 0.7f).toInt().dp)
            ) {
                BoldText(text = address.name)
                IconText(icon = Icons.Rounded.Email, text = address.email)
                IconText(icon = Icons.Rounded.Phone, text = address.phone)
                IconText(
                    icon = Icons.Rounded.LocationCity,
                    text = "${address.landmark}, ${address.city} ${address.pinCode}"
                )
            }

            IconButton(
                modifier = Modifier.width((rowWidth * 0.2f).toInt().dp),
                onClick = { viewModel.deleteAddress(details = address) }) {
                Icon(
                    imageVector = Icons.Rounded.DeleteOutline,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
private fun CheckoutButton(
    navigator: NavController,
    selected: MutableState<Int>,
    list: List<CustomerDetails>,
    context: Context
) {
    if (list.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            ReactiveButton(title = "Checkout", width = 0.95f) {
                if(selected.value>=0){
                    navigator.navigate(AppScreens.Checkout.name+"/${list[selected.value].id}")
                }else{
                    context.showToast("Please select address")
                }
            }
        }
    }
}
