package com.mramallo.pokeapptt.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

fun Int.formatNumberPokemon(): String {
    return when {
        this < 10 -> "00$this"
        this < 100 -> "0$this"
        else -> this.toString()
    }
}

fun getColorTextAccordingToBackground(bgColor: Color): Color {
    val luminance = ColorUtils.calculateLuminance(bgColor.toArgb())
    val isColorLight = luminance > 0.45
    val preferredReturnColor = if (isColorLight) Color.Black else Color.White
    return preferredReturnColor
}