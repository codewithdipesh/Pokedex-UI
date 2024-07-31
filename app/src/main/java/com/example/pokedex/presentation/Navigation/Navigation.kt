package com.example.pokedex.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedex.presentation.detail_screen.PokemonDetailCard
import com.example.pokedex.presentation.homeView.HomeView
import java.util.Locale


@Composable
fun Navigation(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination =Screen.HomeScreen.route ){
        composable(Screen.HomeScreen.route){
           HomeView(navController = navController)
        }
        composable(Screen.DetailScreen.route + "/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor"){
                    type = NavType.IntType
                } ,
                navArgument("pokemonName"){
                    type = NavType.StringType
                }
            )
        ){
            val dominantColor = remember {
                if(it.arguments != null ) {
                    val color = it.arguments!!.getInt("dominantColor")
                    Color(color)
                }else{
                    Color.White
                }
            }
            val pokemonName = remember {
                if(it.arguments != null ) {
                    it.arguments!!.getString("pokemonName")
                }else{
                    ""
                }
            }

            PokemonDetailCard(
                dominantColor = dominantColor ,
                name =  pokemonName!!.toLowerCase(Locale.ROOT),
                navController = navController
            )



        }
    }
}