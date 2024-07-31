package com.example.pokedex.presentation.detail_screen.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.utils.Resource

@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo : Resource<Pokemon>,
    loadingModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    when (pokemonInfo) {
        is Resource.Loading -> {
            CircularProgressIndicator(
               color = MaterialTheme.colorScheme.primary,
              modifier = loadingModifier
            )
        }
        is Resource.Success -> {

        }
        is Resource.Error -> {
            Text(
                text = pokemonInfo.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
    }

}