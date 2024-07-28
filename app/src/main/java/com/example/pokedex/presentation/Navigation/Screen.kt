package com.example.pokedex.presentation.Navigation

sealed class Screen (val route : String){
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("pokemon_detail_screen")
}