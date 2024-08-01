package com.example.pokedex.presentation.detail_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import kotlin.math.round

@Composable
fun PokemonDetailDataScreen(
    weight : Int,
    height : Int,
    sectionHeight : Dp = 80.dp
) {
    val pokemonWeightInKg by remember {
        mutableStateOf(weight  / 10f)
    }
    val pokemonHeightInMeter by remember {
        mutableStateOf(height  / 10f)
    }
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){

        PokemonDetailDataItem(
            dataIcon = painterResource(id =R.drawable.ic_weight ) ,
            dataValue = pokemonWeightInKg ,
            datUnit = "Kg",
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier
            .size(1.dp,sectionHeight)
            .background(Color.Gray)
        )
        PokemonDetailDataItem(
            dataIcon = painterResource(id =R.drawable.ic_height ) ,
            dataValue = pokemonHeightInMeter ,
            datUnit = "M",
            modifier = Modifier.weight(1f)
        )
    }


}


