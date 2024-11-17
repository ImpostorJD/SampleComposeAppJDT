package com.jdt.routinuity.components.habitform

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CustomDaysSelection(
    customDays: Map<String, Boolean>,
    onDayToggle: (String) -> Unit,
    category: String
) {
    val height by animateDpAsState(
        targetValue = if (category == "Custom") 30.dp else 0.dp,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
            .height(height),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        customDays.forEach { (day, isActive) ->
            DaysCustomSelectionButton(
                modifier = Modifier.weight(1f),
                label = day,
                isActive = isActive,
                onClick = { onDayToggle(day) }
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}
