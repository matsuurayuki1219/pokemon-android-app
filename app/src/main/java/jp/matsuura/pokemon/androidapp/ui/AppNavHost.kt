package jp.matsuura.pokemon.androidapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jp.matsuura.pokemon.androidapp.ui.detail.DetailScreen
import jp.matsuura.pokemon.androidapp.ui.home.HomeScreen

@Composable
fun AppNavHost (navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        composable(Destination.Home.route) {
            HomeScreen()
        }
        composable(Destination.Detail.route) {
            DetailScreen()
        }
    }

}