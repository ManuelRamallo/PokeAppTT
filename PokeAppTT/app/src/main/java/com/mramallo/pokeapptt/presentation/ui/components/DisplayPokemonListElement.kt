package com.mramallo.pokeapptt.presentation.ui.components

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mramallo.pokeapptt.R
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.presentation.ui.PokemonDetailViewModel
import com.mramallo.pokeapptt.presentation.ui.utils.formatNumberPokemon

@Composable
fun DisplayPokemonListElement(
    pokemonDetailViewModel: PokemonDetailViewModel,
    pokemon: PokemonResult?,
    numberPokemon: Int,
    onDetailClick: () -> Unit
) {

    LaunchedEffect(Unit) {
        pokemonDetailViewModel.getPokemonDetail(pokemon?.name ?: "")
    }


    Card(
        onClick = {
            onDetailClick()
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        if (pokemonDetailViewModel.state.loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Nº ${numberPokemon.formatNumberPokemon()}",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = pokemon?.name ?: "",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Row {
                        DisplayType()
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        DisplayType()
                    }
                }
                // TODO YA CONSIGO CARGAR LA IMAGEN PERO NO CAMBIA SEGÚN EL POKEMON
                DisplayImage(
                    image = pokemonDetailViewModel.state.pokemonDetail?.sprites?.front_default ?: ""
                )
            }
        }
    }

}


@Composable
fun DisplayType() {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Green)
    ) {
        Text(
            text = "Planta",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun DisplayImage(
    image: String
) {

    Log.d("IMAGE", "Display image -> $image")

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            .background(Color.Green)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.2f),
                        Color.Black.copy(alpha = 0.6f)
                    ),
                    startY = 100f
                )
            )

    ) {
        AsyncImage(
            model = image,
            placeholder = painterResource(R.mipmap.error_magikarp_foreground),
            error = painterResource(R.mipmap.error_no_content_foreground),
            contentDescription = "",
            modifier = Modifier.size(120.dp)
        )
    }
}


@Preview
@Composable
fun DisplayPokemonListElementPreview() {
    DisplayPokemonListElement(
        pokemonDetailViewModel = hiltViewModel(),
        pokemon = PokemonResult(
            name = "Pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        ),
        numberPokemon = 1,
        onDetailClick = {}
    )
}