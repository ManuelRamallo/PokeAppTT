package com.mramallo.pokeapptt.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisplayAttributes(
    iconAttributeLeft: Int,
    iconAttributeRight: Int,
    titleAttributeLeft: String,
    titleAttributeRight: String,
    dataAttributeLeft: String,
    dataAttributeRight: String,
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DisplayAttributeElement(
            icon = iconAttributeLeft,
            titleAttribute = titleAttributeLeft,
            attribute = dataAttributeLeft,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        DisplayAttributeElement(
            icon = iconAttributeRight,
            titleAttribute = titleAttributeRight,
            attribute = dataAttributeRight,
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
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}