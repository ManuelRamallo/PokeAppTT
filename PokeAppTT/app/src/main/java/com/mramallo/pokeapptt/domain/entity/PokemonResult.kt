package com.mramallo.pokeapptt.domain.entity

import java.util.UUID

data class PokemonResult(
    val name: String,
    val url: String,
    val uuid: String = UUID.randomUUID().toString()
)
