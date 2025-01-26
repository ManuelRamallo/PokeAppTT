package com.mramallo.pokeapptt.data.remote.api

import com.mramallo.pokeapptt.data.remote.PokemonDetailDto
import com.mramallo.pokeapptt.data.remote.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListDto


    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonDetailDto

}