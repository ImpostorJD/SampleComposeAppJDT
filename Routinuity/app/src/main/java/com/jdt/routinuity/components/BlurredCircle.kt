package com.jdt.routinuity.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun BlurredCircle(blurColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier
                .graphicsLayer {
                    renderEffect = BlurEffect(radiusX = 50.dp.toPx(), radiusY = 50.dp.toPx())
                }
                .fillMaxSize()
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = 100f
            drawCircle(
                color = blurColor,
                center = androidx.compose.ui.geometry.Offset(centerX, centerY),
                radius = radius
            )
        }
    }
}

@Preview
@Composable
fun BlurCircleView(){
    RoutinuityTheme {
        BlurredCircle(MaterialTheme.colorScheme.background)
    }
}