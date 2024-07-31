package com.example.pokedex.presentation.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.presentation.detail_screen.elements.PokemonDetailStateWrapper
import com.example.pokedex.presentation.detail_screen.elements.PokemonDetailTopSection
import com.example.pokedex.utils.Resource
import com.example.pokedex.viewModel.PokemonDetailViewModel

@Composable
fun PokemonDetailCard(
    dominantColor :Color,
    name : String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize :Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo by produceState<Resource<Pokemon>>(initialValue = Resource.Loading()){
        value = viewModel.getPokemonInfo(name)
    }
    PokemonDetailTopSection(
        navController =navController,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
    )
    PokemonDetailStateWrapper(
        pokemonInfo = pokemonInfo,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = topPadding+ pokemonImageSize/2f,
                start = 16.dp,
                end = 16.dp
            )
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        loadingModifier = Modifier
            .size(100.dp)
            .padding(
                top = topPadding+ pokemonImageSize/2f,
                start = 16.dp,
                end = 16.dp
            )
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(dominantColor)
        .padding(bottom = 16.dp)
    ){


        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize())
        {

            if(pokemonInfo is Resource.Success){
                pokemonInfo.data?.sprites?.let{
                    AsyncImage(
                        model = it.front_default,
                        contentDescription = pokemonInfo.data!!.name,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .offset(topPadding)
                    )
                }
            }

        }
    }


}