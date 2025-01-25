package com.mramallo.pokeapptt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mramallo.pokeapptt.presentation.ui.HomeScreen
import com.mramallo.pokeapptt.presentation.ui.theme.PokeAppTTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var query by rememberSaveable { mutableStateOf("") }

            PokeAppTTTheme {
                Scaffold(
                    modifier = Modifier.safeContentPadding().fillMaxSize().padding(top = 24.dp),
                    topBar = {
                       TextField(
                           value = query,
                           onValueChange = {
                               query = it
                           },
                           singleLine = true,
                           modifier = Modifier.fillMaxWidth(),
                       )
                    }
                ) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}