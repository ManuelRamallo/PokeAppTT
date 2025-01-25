package com.mramallo.pokeapptt.domain.entity

data class Abilities(
    val ability: AbilityElement,
    val is_hidden: Boolean,
    val slot: Int
)
