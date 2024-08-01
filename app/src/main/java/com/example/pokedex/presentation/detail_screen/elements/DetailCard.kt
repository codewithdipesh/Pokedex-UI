package com.example.pokedex.presentation.detail_screen.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.remote.response.Pokemon
import java.util.Locale

@Composable
fun DetailSection(
    pokemonInfo :Pokemon,
    modifier:Modifier = Modifier
) {
    val scrollState = rememberScrollState()
   Column(
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = modifier
           .fillMaxSize()
           .verticalScroll(scrollState)
   ) {
       Text(
           text = "#${pokemonInfo.id} ${pokemonInfo.name.capitalize(Locale.ROOT)}",
           fontWeight = FontWeight.Bold,
           fontSize = 20.sp,
           textAlign = TextAlign.Center,
           color = MaterialTheme.colorScheme.onSurface
           )

       PokemonTypeSection(types = pokemonInfo.types)
       PokemonDetailDataScreen(
           weight = pokemonInfo.weight,
           height = pokemonInfo.height
       )
       PokemonBaseStat(pokemonInfo = pokemonInfo)
   }

}