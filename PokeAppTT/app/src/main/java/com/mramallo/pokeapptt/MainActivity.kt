package com.mramallo.pokeapptt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mramallo.pokeapptt.presentation.navigation.DetailRoute
import com.mramallo.pokeapptt.presentation.navigation.HomeRoute
import com.mramallo.pokeapptt.presentation.ui.DetailScreen
import com.mramallo.pokeapptt.presentation.ui.HomeScreen
import com.mramallo.pokeapptt.presentation.ui.theme.PokeAppTTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PokeAppTTTheme {
                NavHost(
                    navController = navController,
                    startDestination = HomeRoute
                ) {
                    composable<HomeRoute> {
                        HomeScreen(
                            onDetailClick = { namePokemon ->
                                navController.navigate(DetailRoute)
                            }
                        )
                    }
                    composable<DetailRoute> {
                        DetailScreen(
                            onBackClick = {
                                navController.popBackStack(route = HomeRoute, inclusive = false)
                            }
                        )
                    }
                }
            }
        }
    }
}