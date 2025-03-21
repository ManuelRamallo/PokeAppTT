package com.mramallo.pokeapptt.domain.entity

data class Sprites(
    val front_default: String,
    val back_default: String,
    val front_shiny: String,
    val back_shiny: String,
) {
    companion object {
        fun getMock() = Sprites(
            front_default = "",
            back_default = "",
            front_shiny = "",
            back_shiny = ""
        )
    }
}
