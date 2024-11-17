package com.jdt.routinuity.components.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileInformationStrip(
    label: String,
    value: String,
){
    var size by remember { mutableStateOf(0.dp) }
    val animatedSize by animateDpAsState(
        targetValue = size,
        animationSpec = tween(durationMillis = 200), label = ""
    )

    LaunchedEffect(Unit) {
        size = 60.dp
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(animatedSize)
            .padding(8.dp, 0.dp)
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(8.dp, 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(label,
            style = TextStyle(
                color = MaterialTheme.colorScheme.background,
                fontSize = 15.sp
            )
        )
        Text(value,
            style = TextStyle(
                color = MaterialTheme.colorScheme.background,
                fontSize = 15.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(2.dp))
}
