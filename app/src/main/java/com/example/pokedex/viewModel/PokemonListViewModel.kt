package com.example.pokedex.viewModel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokedex.data.models.PokemonListEntry
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) :ViewModel()
{
    private var curPage = 0
    var pokemonList = mutableListOf<List<PokemonListEntry>>(emptyList())
    var loadError by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var endReached by mutableStateOf(false)

    private val PAGE_SIZE = 20

   fun loadPokemonPaginated(){
       viewModelScope.launch {
           isLoading = true //laoding getting the details
           val result = repository.getPokemonList(PAGE_SIZE,curPage * PAGE_SIZE) //20 pokemon per page and for next page we get from 21( offset 20 = 20*1) pokemon
           when(result){
               is Resource.Success ->{
                   endReached = curPage * PAGE_SIZE >= result.data!!.count //check from api result count
                   // "results": [
                   //    {
                   //      "name": "metapod",
                   //      "url": "https://pokeapi.co/api/v2/pokemon/11/"
                   //    },
                   //    we are getting the last digit which is 11 here
                   val pokemonEntries = result.data.results.mapIndexed{index,entry->
                       val number =
                           if(entry.url.endsWith("/")){
                                entry.url.dropLast(1).takeLastWhile { it.isDigit() } //drop the / and get until it is number
                           }
                           else{
                               entry.url.takeLastWhile { it.isDigit() } //there is no / so just simply get the number
                           }
                       val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                       PokemonListEntry(entry.name.capitalize(Locale.ROOT),url,number.toInt())
                   }

                   curPage++
                   isLoading = false
                   pokemonList += pokemonEntries
               }
               is Resource.Error->{
                  isLoading = false
                   loadError = result.message!!
               }

           }
       }
   }


  fun calcDominantColor(drawable : Drawable, onFinished :(Color) -> Unit){
      val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888,true)

      Palette.from(bmp).generate{palette->
      palette?.dominantSwatch?.rgb?.let { colorValue ->
          onFinished(Color(colorValue))

      }
      }
  }

}