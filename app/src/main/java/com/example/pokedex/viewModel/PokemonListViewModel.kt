package com.example.pokedex.viewModel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) :ViewModel()
{
    private var curPage = 0

    private val _pokemonList = MutableStateFlow<List<PokemonListEntry>>(emptyList())
    val pokemonList: StateFlow<List<PokemonListEntry>> = _pokemonList.asStateFlow()

    private val _endReached = MutableStateFlow(false)
    val endReached: StateFlow<Boolean> = _endReached.asStateFlow()

    private val _loadError = MutableStateFlow<String?>(null)
    val loadError: StateFlow<String?> = _loadError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val PAGE_SIZE = 20

    private var cachedPokemonList = listOf<PokemonListEntry>()

    var isSearchStarting = true

    private val _isSearching = MutableStateFlow(false)
    val isSearching:StateFlow<Boolean> = _isSearching.asStateFlow()

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query :String){
        val listToSearch = if(isSearchStarting) {
            _pokemonList.value
        }else{
            cachedPokemonList
        }

        viewModelScope.launch (Dispatchers.Default){
            if(query.isEmpty()){
                _pokemonList.value = cachedPokemonList
                _isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.lowercase().contains(query.lowercase().trim(),ignoreCase = false) ||
                        it.number.toString() == query
            }
            if(isSearchStarting){
                cachedPokemonList = _pokemonList.value
                isSearchStarting =false

            }
            _pokemonList.value = results
            _isSearching.value =true
        }
    }



   fun loadPokemonPaginated(){
       viewModelScope.launch {
           _isLoading.value= true //laoding getting the details
           val result = repository.getPokemonList(PAGE_SIZE,curPage * PAGE_SIZE) //20 pokemon per page and for next page we get from 21( offset 20 = 20*1) pokemon
           when(result){
               is Resource.Success ->{
                   _endReached.value = curPage * PAGE_SIZE >= result.data!!.count //check from api result count
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

                   _pokemonList.value += pokemonEntries
                   curPage++
                   _isLoading.value = false
               }
               is Resource.Error->{
                  _isLoading.value = false
                   _loadError.value = result.message!!
               }
               is Resource.Loading ->{
                   _isLoading.value = true
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