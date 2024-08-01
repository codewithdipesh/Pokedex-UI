package com.example.pokedex.presentation.detail_screen.elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PokemonStat(
    StatName : String,
    statValue:Int,
    statMaxValue:Int,
    statColor:Color,
    animDuration:Int = 1000,
    animDelay:Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
  val curPercant = animateFloatAsState(
      targetValue = if(animationPlayed) {
          statValue / statMaxValue.toFloat()
      }else 0f,
      animationSpec = tween(
          animDuration,
          animDelay
      )
  )

   LaunchedEffect(true){
       animationPlayed = true
   }
   Box(
       modifier = Modifier
           .fillMaxWidth()
           .height(28.dp)
           .clip(CircleShape)
           .background(
               if (isSystemInDarkTheme()) {
                   Color(0xFF505050)
               } else {
                   Color.LightGray
               }
           )
   ){
       Row (
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .fillMaxHeight()
               .fillMaxWidth(curPercant.value)
               .clip(CircleShape)
               .background(statColor)
               .padding(horizontal = 8.dp)
       ){
            Text(
                text = StatName,
                fontWeight = FontWeight.Bold
                )
           Text(
               text = (curPercant.value * statMaxValue).toInt().toString(),
               fontWeight = FontWeight.Bold
           )
       }
   }
}
