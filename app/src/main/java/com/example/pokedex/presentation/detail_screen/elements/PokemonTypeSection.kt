package com.example.pokedex.presentation.detail_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.remote.response.Type
import com.example.pokedex.utils.parseTypeToColor
import java.util.Locale

@Composable
fun PokemonTypeSection(
    types : List<Type>
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ){
        for(type in types){
          Box(contentAlignment = Alignment.Center,
              modifier = Modifier
                  .weight(1f)
                  .padding(horizontal = 8.dp)
                  .clip(CircleShape)
                  .background(parseTypeToColor(type))
                  .height(35.dp)
          ){
              Text(
                  text = type.type.name.capitalize(Locale.ROOT) ,
                  textAlign = TextAlign.Center,
                  fontSize = 18.sp,
                  color = Color.White
              )
          }
        }
    }
}