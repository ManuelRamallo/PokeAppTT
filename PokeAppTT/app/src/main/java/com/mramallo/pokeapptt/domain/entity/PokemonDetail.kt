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
) {
    companion object {
        fun getMock() = PokemonDetail(
            id = 1,
            height = 80,
            weight = 100,
            name = "bulbasaur",
            order = 0,
            abilities = listOf(),
            base_experience = 100,
            sprites = Sprites.getMock(),
            stats = listOf(),
            types = listOf()
        )
    }
}
