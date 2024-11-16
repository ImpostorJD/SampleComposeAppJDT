package com.jdt.routinuity.components.habitform

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequirementsButton(
    modifier: Modifier = Modifier,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    icon: Int,
    inactiveContentColor: Color = MaterialTheme.colorScheme.inversePrimary,
    inactiveContainerColor: Color = Color.Transparent,
    activeContentColor: Color = MaterialTheme.colorScheme.background,
    activeContainerColor: Color = MaterialTheme.colorScheme.primary,
) {

    val containerColor by animateColorAsState(
        targetValue = if (isActive) activeContainerColor else inactiveContainerColor,
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
    val interactionSource = remember(isActive) { MutableInteractionSource() }

    Button(
        onClick = onClick,
        modifier = modifier.then(
            Modifier .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50f)
            ).fillMaxHeight()
                .background(
                    color = containerColor,
                    shape = RoundedCornerShape(50f)
                ).clip(RoundedCornerShape(50f))
        ),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = TextStyle(fontSize = 15.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                Modifier.size(40.dp),
            )
        }
    }
}
