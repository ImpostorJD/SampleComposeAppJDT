package com.jdt.routinuity.components.habitform

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FieldButton(
    modifier: Modifier = Modifier,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    inactiveContentColor: Color = MaterialTheme.colorScheme.inversePrimary,
    inactiveContainerColor: Color = Color.Transparent,
    activeContentColor: Color = MaterialTheme.colorScheme.background,
    activeContainerColor: Color = MaterialTheme.colorScheme.primary,
) {

    val containerColor by animateColorAsState(
        targetValue = if (isActive) activeContainerColor.copy(alpha = 0.5f) else inactiveContainerColor,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val contentColor by animateColorAsState(
        targetValue = if (isActive) activeContentColor else inactiveContentColor,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    val borderColor by animateColorAsState(
        targetValue = if (isActive) activeContainerColor else inactiveContentColor,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50f)
            )
            .height(35.dp)
            .background(
                color = containerColor,
                shape = RoundedCornerShape(50f)
            ).clip(RoundedCornerShape(50f)),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Text(label)
    }
}