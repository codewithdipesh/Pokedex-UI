package com.example.pokedex.presentation.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.presentation.detail_screen.elements.GradientTopAppBar
import com.example.pokedex.presentation.detail_screen.elements.PokemonDetailStateWrapper
import com.example.pokedex.utils.Resource
import com.example.pokedex.viewModel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    dominantColor :Color,
    name : String,
    navController: NavController ,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo by produceState<Resource<Pokemon>>(initialValue = Resource.Loading()){
        value = viewModel.getPokemonInfo(name)
    }

    Scaffold (
        topBar = {
            GradientTopAppBar(
                onNavigationClick = {
                                    navController.navigateUp()
                },
                color = dominantColor
            )
        },
        containerColor = dominantColor
    ){
       Box(
           modifier = Modifier
               .fillMaxSize()
               .padding(it)
               .padding(bottom = 30.dp, start = 16.dp, end = 16.dp)
               .clip(RoundedCornerShape(10.dp))
               .shadow(5.dp, RoundedCornerShape(16.dp))
               .background(MaterialTheme.colorScheme.surface)
       ){
           PokemonDetailStateWrapper(
               pokemonInfo = pokemonInfo,
               loadingModifier = Modifier
                   .size(40.dp)
                   .align(Alignment.Center)
           )
       }


    }


}



