package com.sparkly.productslist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sparkly.productslist.ui.screens.ProductDetails
import com.sparkly.productslist.ui.screens.ProductList

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            ProductList(onItemClicked = {json ->
                navController.navigate("${Screen.Details.route}/$json")
            })
        }
        composable("${Screen.Details.route}/{${Arg.JSON}}",
            arguments = listOf(navArgument(Arg.JSON) {
                type = NavType.StringType
                nullable = true
            })) {
            ProductDetails()
        }
    }
}

object Arg {
    const val JSON = "json"
}