package com.mramallo.pokeapptt.data.remote

data class PokemonListDto(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResultDto>?
)