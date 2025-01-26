package com.mramallo.pokeapptt.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mramallo.pokeapptt.R
import com.mramallo.pokeapptt.domain.entity.PokemonDetail
import com.mramallo.pokeapptt.presentation.ui.components.DisplayTypePokemon
import com.mramallo.pokeapptt.presentation.ui.components.SemicircleBgDetail
import com.mramallo.pokeapptt.presentation.ui.utils.formatNumberPokemon
import com.mramallo.pokeapptt.presentation.ui.utils.getColorByTypePokemon
import java.util.Locale

@Composable
fun DetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel(),
    namePokemon: String,
    onBackClick: () -> Unit
) {

    LaunchedEffect(Unit) {
        pokemonDetailViewModel.getPokemonDetail(namePokemon)
    }
    val pokemonDetail = pokemonDetailViewModel.statePokemonDetail.pokemonDetail


    Scaffold(
        modifier = Modifier
            .padding(top = 48.dp)
            .fillMaxSize(),
    ) { innerPadding ->
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .padding(horizontal = 16.dp)

        ) {
            DisplayHeaderDetail(
                modifier = Modifier.height(250.dp),
                image = pokemonDetail?.sprites?.front_default ?: "",
                type = pokemonDetail?.types?.get(0)?.type?.name ?: "",
                onBackClick = onBackClick
            )
            DisplayBasicInfo(pokemonDetail = pokemonDetail)
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))
            DisplayAdditionalInfo()
        }
    }

}


@Composable
fun DisplayHeaderDetail(
    modifier: Modifier = Modifier,
    image: String,
    type: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        SemicircleBgDetail(getColorByTypePokemon(type))
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .size(36.dp)
                .clickable { onBackClick() },
            tint = Color.White,
        )
        AsyncImage(
            model = image,
            placeholder = painterResource(R.mipmap.error_magikarp_foreground),
            error = painterResource(R.mipmap.error_no_content_foreground),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun DisplayBasicInfo(
    pokemonDetail: PokemonDetail?,
){
    Text(
        text = pokemonDetail?.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        } ?: "",
        fontSize = 42.sp,
        fontWeight = FontWeight.W500,
    )
    Text(
        text = "Nº ${pokemonDetail?.order?.formatNumberPokemon()}",
        fontSize = 18.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(24.dp))
    Row {
        pokemonDetail?.types?.forEach { type ->
            DisplayTypePokemon(
                type = type.type?.name ?: "",
                fontSize = 16,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

@Composable
fun DisplayAdditionalInfo(){
    DisplayAttributes()
    Spacer(modifier = Modifier.height(24.dp))
    DisplayAttributes()
}

@Composable
fun DisplayAttributes(){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DisplayAttributeElement(
            icon = R.drawable.weight,
            titleAttribute = "PESO",
            attribute = "6,5",
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        DisplayAttributeElement(
            icon = R.drawable.weight,
            titleAttribute = "ALTURA",
            attribute = "6,5",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DisplayAttributeElement(
    icon: Int,
    titleAttribute: String,
    attribute: String,
    modifier: Modifier
){
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier
                    .size(14.dp),
                tint = Color.DarkGray,
            )
            Text(
                text = titleAttribute,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            Text(
                text = attribute,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}
