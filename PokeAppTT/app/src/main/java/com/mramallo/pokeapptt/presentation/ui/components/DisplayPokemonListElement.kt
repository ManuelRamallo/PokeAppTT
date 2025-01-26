package com.mramallo.pokeapptt.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mramallo.pokeapptt.R
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.presentation.ui.PokemonListViewModel
import com.mramallo.pokeapptt.presentation.ui.utils.formatNumberPokemon
import com.mramallo.pokeapptt.presentation.ui.utils.getColorByTypePokemon
import com.mramallo.pokeapptt.presentation.ui.utils.getColorTextAccordingToBackground

@Composable
fun DisplayPokemonListElement(
    pokemonListViewModel: PokemonListViewModel,
    pokemon: PokemonResult?,
    numberPokemon: Int,
    onDetailClick: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        pokemonListViewModel.getPokemonElement(pokemon?.uuid ?: "", pokemon?.name ?: "")
    }
    val pokemonElement = pokemonListViewModel.statePokemonElement[pokemon?.uuid]?.pokemonDetail


    Card(
        onClick = {
            onDetailClick(pokemon?.name ?: "")
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = getColorByTypePokemon(pokemonElement?.types?.get(0)?.type?.name ?: "").copy(alpha = 0.2f))
    ) {
        if (pokemonListViewModel.statePokemonElement[pokemon?.uuid]?.loading == true) {
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
                    if (pokemonElement?.types != null) {
                        Row {
                            pokemonElement.types.forEach { type ->
                                DisplayTypePokemon(type.type?.name ?: "")
                                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                            }
                        }

                    }
                }
                DisplayImage(
                    image = pokemonElement?.sprites?.front_default ?: "",
                    type = pokemonElement?.types?.get(0)?.type?.name ?: ""
                )
            }
        }
    }
}




@Composable
fun DisplayImage(
    image: String,
    type: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            .background(getColorByTypePokemon(type))
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
        pokemonListViewModel = hiltViewModel(),
        pokemon = PokemonResult(
            name = "Pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        ),
        numberPokemon = 1,
        onDetailClick = {}
    )
}