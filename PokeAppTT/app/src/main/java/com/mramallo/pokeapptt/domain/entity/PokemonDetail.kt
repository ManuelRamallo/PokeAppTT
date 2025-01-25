package com.mramallo.pokeapptt.domain.entity

data class PokemonDetail(
    val id: Int,
    val height: Int,
    val weight: Int,
    val name: String,
    val order: Int,
    val abilities: List<Abilities>,
    val base_experience: Int,
    val sprites: Sprites,
    val stats: List<Stats>,
    val types: List<Types>
)
