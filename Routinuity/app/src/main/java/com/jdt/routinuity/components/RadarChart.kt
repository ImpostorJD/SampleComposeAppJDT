package com.jdt.routinuity.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RadarChart(
    data: List<List<Float>>,
    labels: List<Pair<String, Color>>,
    maxValue: Float,
    modifier: Modifier = Modifier,
    lines: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
    statFill: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
) {
    val labelPositions = remember { mutableListOf<Offset>() }

    var targetRadius by remember { mutableFloatStateOf(0f) }
    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    var alpha by remember { mutableFloatStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(Unit) {
        targetRadius = 1f
        alpha = 0.6f
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val maxRadius = minOf(centerX, centerY) * 0.8f
            val currentRadius = maxRadius * animatedRadius
            val sides = labels.size

            for (i in 1..sides) {
                val stepRadius = currentRadius * i / sides
                drawPolygon(
                    center = Offset(centerX, centerY),
                    radius = stepRadius,
                    sides = sides,
                    color = lines.copy(alpha = animatedAlpha)
                )
            }

            val angleStep = 360f / sides

            labels.forEachIndexed { index, labelPair ->
                val labelColor = labelPair.second
                val angle = Math.toRadians((angleStep * index - 90f).toDouble())
                val labelRadius = currentRadius + 20f
                val labelX = centerX + labelRadius * cos(angle).toFloat()
                val labelY = centerY + labelRadius * sin(angle).toFloat()

                labelPositions.add(Offset(labelX, labelY))

                drawLine(
                    color = lines.copy(alpha = animatedAlpha),
                    start = Offset(centerX, centerY),
                    end = Offset(
                        centerX + currentRadius * cos(angle).toFloat(),
                        centerY + currentRadius * sin(angle).toFloat()
                    ),
                    strokeWidth = 5f
                )

                drawCircle(
                    color = labelColor.copy(alpha = animatedAlpha),
                    radius = 20f,
                    center = Offset(labelX, labelY)
                )
            }

            data.forEach { values ->
                val points = values.mapIndexed { index, value ->
                    val angle = Math.toRadians((angleStep * index - 90f).toDouble())
                    val scaledValue = (value / maxValue) * currentRadius
                    Offset(
                        (centerX + scaledValue * cos(angle)).toFloat(),
                        (centerY + scaledValue * sin(angle)).toFloat()
                    )
                }
                drawPolygonPath(points, statFill.copy(alpha = animatedAlpha))
            }
        }
    }
}

fun DrawScope.drawPolygon(
    center: Offset,
    radius: Float,
    sides: Int,
    color: Color
) {
    val angleStep = 360f / sides
    val path = Path().apply {
        for (i in 0 until sides) {
            val angle = Math.toRadians((angleStep * i + 90f).toDouble())
            val x = center.x + radius * cos(angle).toFloat()
            val y = center.y - radius * sin(angle).toFloat()
            if (i == 0) moveTo(x, y) else lineTo(x, y)
        }
        close()
    }
    drawPath(path, color = color, style = Stroke(width = 5f))
}

fun DrawScope.drawPolygonPath(points: List<Offset>, color: Color) {
    val path = Path().apply {
        points.forEachIndexed { index, point ->
            if (index == 0) moveTo(point.x, point.y)
            else lineTo(point.x, point.y)
        }
        close()
    }
    drawPath(path, color, style = Fill)
}

@Preview(showBackground = true)
@Composable
fun RadarSampleView() {
    RoutinuityTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxSize()
        ) {
            RadarChart(
                data = listOf(
                    listOf(0f, 0f, 50f, 100f, 10f, 20f)
                ),
                labels = listOf(
                    "Label 1" to Color.Red,
                    "Label 2" to Color.Green,
                    "Label 3" to Color.Blue,
                    "Label 4" to Color.Yellow,
                    "Label 5" to Color.Magenta,
                    "Label 6" to Color.Cyan
                ),
                maxValue = 100f,
                modifier = Modifier.fillMaxSize(),
                lines = Color.Red.copy(alpha = 0.7f),
                statFill = Color.Cyan.copy(alpha = 0.4f),
            )
        }
    }
}

