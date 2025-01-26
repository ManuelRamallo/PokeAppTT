package com.mramallo.pokeapptt.presentation.ui.utils

fun Int.formatNumberPokemon(): String {
    return when {
        this < 10 -> "00$this"
        this < 100 -> "0$this"
        else -> this.toString()
    }
}