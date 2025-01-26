package com.mramallo.pokeapptt.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun SemicircleBgDetail(bgColor: Color){
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
        ) {
            drawSemicircle(bgColor, this)
        }
    }
}


private fun drawSemicircle(bgColor: Color, scope: DrawScope) {
    val canvasWidth = scope.size.width
    val canvasHeight = scope.size.height

    scope.drawCircle(
        color = Color(bgColor.toArgb()),
        radius = canvasHeight * 1.75f, // Radius adjusted to create a semicircle
        center = Offset(canvasWidth / 2, canvasHeight / 20f) // Offset centre for the semicircle
    )
}