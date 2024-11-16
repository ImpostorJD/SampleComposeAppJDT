package com.jdt.routinuity.components.context

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun CategoryButton(
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

    Button(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(25f)
            )
            .height(50.dp)
            .background(
                color = containerColor,
                shape = RoundedCornerShape(25f)
            ).clip(RoundedCornerShape(25f)),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Text(label)
    }
}

@Composable
fun CategorySelector(
    onCategoryChange: ((String) -> Unit)? = null
) {
    var activeCategory by remember { mutableStateOf("Daily") }

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(5.dp, 0.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        listOf("Daily", "Weekly", "Monthly", "Custom").forEach { value ->
            CategoryButton(
                modifier = Modifier.width(120.dp),
                label = value,
                isActive = activeCategory == value,
                onClick = {
                    activeCategory = value
                    onCategoryChange?.invoke(activeCategory)
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

    }
}

@Preview
@Composable
fun CategorySelectorPreview() {
    RoutinuityTheme {
        CategorySelector()
    }
}
