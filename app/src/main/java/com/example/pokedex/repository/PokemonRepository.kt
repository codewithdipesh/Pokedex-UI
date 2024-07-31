package com.example.pokedex.repository

import com.example.pokedex.data.remote.api.PokeApi
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.data.remote.response.PokemonList
import com.example.pokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject


class PokemonRepository @Inject constructor(
    private val api:PokeApi
){

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return try {
            val response = api.getAllPokemon(limit, offset)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("An Unknown error occurred: ${e.message}")
        }
    }
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try{
            api.getPokemonDetails(pokemonName)
        }catch (e:Exception){
            return Resource.Error("An Unknown error occured")
        }
        return Resource.Success(response)
    }
}