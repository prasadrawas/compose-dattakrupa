package com.example.composegrocceryapp.navigation.user

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composegrocceryapp.navigation.AppScreens
import com.example.composegrocceryapp.ui.screens.admin.inventory.category.InventoryCategoryDetailsScreen
import com.example.composegrocceryapp.ui.screens.admin.inventory.category.InventoryCategoryDetailsViewModel
import com.example.composegrocceryapp.ui.screens.admin.inventory.category.InventoryCategoryScreen
import com.example.composegrocceryapp.ui.screens.admin.inventory.category.InventoryCategoryViewModel
import com.example.composegrocceryapp.ui.screens.admin.inventory.product.InventoryProductDetailsScreen
import com.example.composegrocceryapp.ui.screens.admin.inventory.product.InventoryProductDetailsViewModel
import com.example.composegrocceryapp.ui.screens.admin.inventory.product.InventoryProductsScreen
import com.example.composegrocceryapp.ui.screens.admin.inventory.product.InventoryProductsViewModel
import com.example.composegrocceryapp.ui.screens.admin.panel.ActiveUsersScreen
import com.example.composegrocceryapp.ui.screens.admin.panel.ActiveUsersViewModel
import com.example.composegrocceryapp.ui.screens.admin.panel.AdminPanelScreen
import com.example.composegrocceryapp.ui.screens.admin.panel.AdminPanelViewModel
import com.example.composegrocceryapp.ui.screens.product.ProductDetailsScreen
import com.example.composegrocceryapp.ui.screens.product.ProductDetailsViewModel
import com.example.composegrocceryapp.ui.screens.auth.*
import com.example.composegrocceryapp.ui.screens.cart.CartScreen
import com.example.composegrocceryapp.ui.screens.cart.CartViewModel
import com.example.composegrocceryapp.ui.screens.checkout.*
import com.example.composegrocceryapp.ui.screens.home.HomeScreen
import com.example.composegrocceryapp.ui.screens.home.HomeViewModel
import com.example.composegrocceryapp.ui.screens.order.*
import com.example.composegrocceryapp.ui.screens.product.ProductListerScreen
import com.example.composegrocceryapp.ui.screens.product.ProductsListerViewModel
import com.example.composegrocceryapp.ui.screens.profile.*
import com.example.composegrocceryapp.ui.screens.splash.SplashScreen

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun UserAppNavigation(){
    val navigator = rememberNavController()
    NavHost(navController = navigator, startDestination = AppScreens.ProductsLister.name){

        composable(AppScreens.Splash.name){
            SplashScreen(navigator = navigator)
        }

        composable(AppScreens.SignIn.name){
            val viewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.SignUp.name){
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ResetPassword.name){
            val viewModel = hiltViewModel<ResetPasswordViewModel>()
            ResetPasswordScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ResetSuccess.name){
            ResetSuccessScreen(navigator = navigator)
        }

        composable(AppScreens.Home.name){
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ProductDetails.name){
            val viewModel = hiltViewModel<ProductDetailsViewModel>()
            ProductDetailsScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.Cart.name){
            val viewModel = hiltViewModel<CartViewModel>()
            CartScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.Address.name){
            val viewModel = hiltViewModel<AddressViewModel>()
            AddressScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.Checkout.name){
            val viewModel = hiltViewModel<CheckoutViewModel>()
            CheckoutScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.OrderSuccess.name){
            OrderSuccessScreen(navigator = navigator)
        }

        composable(AppScreens.Profile.name){
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ChangeEmail.name){
            val viewModel = hiltViewModel<ChangeEmailViewModel>()
            ChangeEmailScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ChangePassword.name){
            val viewModel = hiltViewModel<ChangePasswordViewModel>()
            ChangePasswordScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.Orders.name){
            val viewModel = hiltViewModel<OrdersViewModel>()
            OrdersScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.OrderDetails.name){
            val viewModel = hiltViewModel<OrderDetailsViewModel>()
            OrderDetailsScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.AdminPanel.name){
            val viewModel = hiltViewModel<AdminPanelViewModel>()
            AdminPanelScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ActiveUsers.name){
            val viewModel = hiltViewModel<ActiveUsersViewModel>()
            ActiveUsersScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.InventoryProducts.name){
            val viewModel = hiltViewModel<InventoryProductsViewModel>()
            InventoryProductsScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.InventoryProductDetails.name){
            val viewModel = hiltViewModel<InventoryProductDetailsViewModel>()
            InventoryProductDetailsScreen(navigator = navigator, viewModel = viewModel)
        }


        composable(AppScreens.InventoryCategory.name){
            val viewModel = hiltViewModel<InventoryCategoryViewModel>()
            InventoryCategoryScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.InventoryCategoryDetails.name){
            val viewModel = hiltViewModel<InventoryCategoryDetailsViewModel>()
            InventoryCategoryDetailsScreen(navigator = navigator, viewModel = viewModel)
        }

        composable(AppScreens.ProductsLister.name){
            val viewModel = hiltViewModel<ProductsListerViewModel>()
            ProductListerScreen(navigator = navigator, viewModel = viewModel)
        }
    }
}