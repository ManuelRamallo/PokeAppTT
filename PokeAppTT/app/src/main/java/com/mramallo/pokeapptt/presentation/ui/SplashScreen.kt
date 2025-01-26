package com.mramallo.pokeapptt.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mramallo.pokeapptt.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenPersonalized(
    onFinishSplash: () -> Unit,
    splashDuration: Long = 3000L,
){
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(splashDuration)
        isVisible = false
        onFinishSplash()
    }

    if(isVisible) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFF000029)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.pokedex_icon_foreground),
                    contentDescription = "Pokedex Icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp)
                )
            }

            Text(
                text = stringResource(R.string.powered),
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 48.dp)
            )

        }
    }



}