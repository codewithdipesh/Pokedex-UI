package com.example.pokedex.presentation.detail_screen.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.utils.parseStatToAbbr
import com.example.pokedex.utils.parseStatToColor

@Composable
fun PokemonBaseStat(
    pokemonInfo :Pokemon,
    animDelayedItem :Int = 100
) {
    val maxBaseStat by remember {
        mutableStateOf(pokemonInfo.stats.maxOf { it.base_stat })
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp))
    {
        Text(
            text = "Base Stat",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))

        for(i in pokemonInfo.stats.indices){
            val stat = pokemonInfo.stats[i]
            PokemonStat(
                StatName = parseStatToAbbr(stat) ,
                statValue = stat.base_stat ,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animDelayedItem
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

    }

}