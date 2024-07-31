package com.example.pokedex.viewModel

import androidx.lifecycle.ViewModel
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) :ViewModel()
{
  suspend fun getPokemonInfo(name:String):Resource<Pokemon>{
      return repository.getPokemonInfo(name)
  }

}