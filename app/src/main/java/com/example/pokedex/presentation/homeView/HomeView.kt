package com.example.pokedex.presentation.homeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.R
import com.example.pokedex.presentation.homeView.elements.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController = rememberNavController()
){
    Scaffold(
        topBar = {
          TopAppBar(title = {
              Row(modifier = Modifier
                  .fillMaxWidth()
                  .wrapContentHeight(),
                  horizontalArrangement = Arrangement.Center) {
                  Image(
                      painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                      contentDescription = "pokemon logo",
                      modifier = Modifier.padding(top = 16.dp,bottom = 16.dp)
                  )
              }
          },
              modifier = Modifier
                  .background(MaterialTheme.colorScheme.background)
                  .height(100.dp)
          )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
       Column(modifier = Modifier
           .fillMaxSize()
           .padding(it)) {

           SearchBar(
               hint = "Search...",
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp)
           )


       }
    }
}





@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    HomeView()
}