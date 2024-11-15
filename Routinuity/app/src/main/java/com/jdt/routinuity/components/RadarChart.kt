package com.jdt.routinuity.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

@Composable
fun RadarChart(
    data: List<List<Float>>,
    labels: List<Pair<String, Color>>,
    maxValue: Float,
    modifier: Modifier = Modifier,
    lines: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
    statFill: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
    labelTextColor: Color = MaterialTheme.colorScheme.primary
) {
    val labelPositions = remember { mutableListOf<Offset>() }
    val selectedLabel = remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        var foundIndex: Int? = null
                        for (position in labelPositions) {
                            val distance = (offset.x - position.x).pow(2) + (offset.y - position.y).pow(2)
                            if (distance <= 30f * 30f) {
                                foundIndex = labelPositions.indexOf(position)
                                break
                            }
                        }

                        if (foundIndex != null && foundIndex < labels.size) {
                            val label = labels[foundIndex].first
                            // Toggle label selection
                            selectedLabel.value = if (selectedLabel.value == label) null else label
                        }
                    }
                }
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = minOf(centerX, centerY) * 0.8f
            val sides = labels.size

            // Drawing concentric grid lines with composable context
            for (i in 1..sides) {
                val stepRadius = radius * i / sides
                drawPolygon(
                    center = Offset(centerX, centerY),
                    radius = stepRadius,
                    sides = sides,
                    color = lines
                )
            }

            val angleStep = 360f / sides

            // Calculate label positions and draw lines
            labels.forEachIndexed { index, labelPair ->
                val label = labelPair.first
                val labelColor = labelPair.second

                val angle = Math.toRadians((angleStep * index - 90f).toDouble())
                val labelRadius = radius + 20f
                val labelX = centerX + labelRadius * cos(angle).toFloat()
                val labelY = centerY + labelRadius * sin(angle).toFloat()

                labelPositions.add(Offset(labelX, labelY))

                drawLine(
                    color = lines,
                    start = Offset(centerX, centerY),
                    end = Offset(centerX + radius * cos(angle).toFloat(), centerY + radius * sin(angle).toFloat()),
                    strokeWidth = 5f
                )

                drawCircle(
                    color = labelColor,
                    radius = 20f,
                    center = Offset(labelX, labelY)
                )

                if (selectedLabel.value == label) {
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            label,
                            labelX,
                            labelY - 30f, // Adjust position for label
                            android.graphics.Paint().apply {
                                color = labelTextColor.toArgb()
                                textSize = 40f
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }
                }
            }

            // Drawing the radar chart data points
            data.forEach { values ->
                val points = values.mapIndexed { index, value ->
                    val angle = Math.toRadians((angleStep * index - 90f).toDouble())
                    val scaledValue = (value / maxValue) * radius
                    Offset(
                        (centerX + scaledValue * cos(angle)).toFloat(),
                        (centerY + scaledValue * sin(angle)).toFloat() // Corrected Y computation
                    )
                }

                drawPolygonPath(points, statFill)
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
                        listOf(0f, 0f, 50f, 100f, 10f, 20f),
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
                    labelTextColor = MaterialTheme.colorScheme.background
                )
            }

        }
    }
