package com.jdt.routinuity.components.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.components.RadarChart
import com.jdt.routinuity.components.shapes.BlurredCircle

@Composable
fun ProfileStat(
    data: List<List<Float>>,
    labels: List<Pair<String, Color>>,
    maxValue: Float,
){
    var alpha by remember { mutableFloatStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 2500), label = ""
    )

    LaunchedEffect(Unit) {
        alpha = 1f
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .padding(8.dp, 0.dp)
        .background(color = MaterialTheme.colorScheme.primary)
    ){
        Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .align(Alignment.TopStart)
            .offset((-60).dp, (-20).dp)
        ){
            BlurredCircle(blurColor = MaterialTheme.colorScheme.background.copy(alpha = 0.4f))
        }

        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.TopEnd)
                .offset((20).dp, (50).dp)
        ){
            BlurredCircle(blurColor = MaterialTheme.colorScheme.background.copy(alpha = 0.4f))
        }
            Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Habit Distribution Heatmap",
                modifier = Modifier.alpha(animatedAlpha),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            RadarChart(
                data = data,
                maxValue = maxValue,
                labels = labels,
                modifier = Modifier.fillMaxWidth().weight(1f),
                lines =  MaterialTheme.colorScheme.background.copy(0.6f),
                statFill = MaterialTheme.colorScheme.background.copy(alpha = 0.4f),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(10.dp, 0.dp, 0.dp)
                    .horizontalScroll(rememberScrollState())
                    .alpha(alpha = animatedAlpha)
            ){
                labels.map { pair ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(50f)
                            )
                            .fillMaxHeight()
                            .width(100.dp)
                            .background(
                                color = pair.second.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(50f)
                            ).clip(RoundedCornerShape(50f))
                            .padding(10.dp, 5.dp)
                    ){
                        Text(
                            pair.first,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(color = MaterialTheme.colorScheme.background)
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
    }
}