package com.mramallo.pokeapptt.domain.entity

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonResult>
)
