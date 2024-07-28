package com.example.pokedex.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun Navigation(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination =Screen.HomeScreen.route ){
        composable(Screen.HomeScreen.route){

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
                    it.arguments!!.getString("dominantColor")
                }else{
                    ""
                }
            }




        }
    }
}