package jp.matsuura.pokemon.androidapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.matsuura.pokemon.androidapp.ui.detail.DetailScreen
import jp.matsuura.pokemon.androidapp.ui.home.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        composable(
            route = Destination.Home.route,
        ) {
            HomeScreen(
                onCardItemClicked = { pokemonId ->
                    navController.navigate(
                        Destination.Detail.route + "/${pokemonId}"
                    )
                }
            )
        }
        composable(
            route = Destination.Detail.route + "/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                },
            ),
        ) {
            DetailScreen(
                onButtonClicked = {
                    navController.popBackStack()
                },
                onPokemonClicked = { pokemonId ->
                    navController.navigate(
                        Destination.Detail.route + "/${pokemonId}"
                    )
                }
            )
        }
    }

}