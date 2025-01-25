package com.mramallo.pokeapptt.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.mramallo.pokeapptt.R
import com.mramallo.pokeapptt.domain.entity.PokemonResult

@Composable
fun DisplayError(pokemonList: LazyPagingItems<PokemonResult>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.error_magikarp_foreground),
            contentDescription = "Error magikarp",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = stringResource(R.string.error_title),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.size(36.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { pokemonList.retry() },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.error_button)
            )
        }
    }
}