package com.mramallo.pokeapptt.data.remote

data class PokemonDetailDto(
    val id: Int?,
    val height: Int?,
    val weight: Int?,
    val name: String?,
    val order: Int?,
    val abilities: List<AbilitiesDto>?,
    val base_experience: Int?,
    val sprites: SpritesDto?,
    val stats: List<StatsDto>?,
    val types: List<TypesDto>?
)
