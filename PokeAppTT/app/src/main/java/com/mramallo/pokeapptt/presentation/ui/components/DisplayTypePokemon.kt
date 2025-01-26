package com.mramallo.pokeapptt.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mramallo.pokeapptt.presentation.ui.utils.getColorByTypePokemon
import com.mramallo.pokeapptt.presentation.ui.utils.getColorTextAccordingToBackground

@Composable
fun DisplayTypePokemon(
    type: String,
    fontSize: Int = 14,
    modifier: Modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = getColorByTypePokemon(type))
    ) {
        Text(
            text = type,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier,
            color = getColorTextAccordingToBackground(getColorByTypePokemon(type))
        )
    }
}