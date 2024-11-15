package com.jdt.routinuity.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.R
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun HabitCard(
    modifier: Modifier = Modifier,
    progressColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
    progress: Int,
    textColor: Color = MaterialTheme.colorScheme.primary,
    hintColor: Color = MaterialTheme.colorScheme.inversePrimary,
    background: Color = MaterialTheme.colorScheme.background,
    icon: Int,
    label: String,
    requirement: Int,
    delimiter: String,
) {
    val progressPercent = (progress.toFloat() / requirement.toFloat()).coerceIn(0f, 1f)

    val progressPercentInt = (progressPercent * 100).toInt()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 0.dp)
            .background(color = background)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(25f)
            )
            .height(120.dp)
            .clip(RoundedCornerShape(25f))
    ) {
        val maxSizePx = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

        val maxCornerRadius = maxSizePx / 2
        val cornerRadiusPx = maxCornerRadius * (1 - progressPercent)
        val cornerRadiusDp = with(LocalDensity.current) { cornerRadiusPx.toDp() }

        Box(
            modifier = Modifier
                .background(color = progressColor, shape = RoundedCornerShape(cornerRadiusDp))
                .align(Alignment.TopStart)
                .height(with(LocalDensity.current) { (maxSizePx * (0.1f + (progressPercent * 0.9f))).toDp() })
                .width(with(LocalDensity.current) { (maxSizePx * (0.1f + (progressPercent * 0.9f))).toDp() })
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f)
            ) {
                // Show the progress percentage as an integer
                Text("$progressPercentInt%", color = textColor)
                Spacer(modifier = Modifier.height(12.dp))
                Text(label, color = textColor)
            }

            VerticalDivider(Modifier.width(2.dp).fillMaxHeight().padding(0.dp, 2.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    if (progress == requirement) "Finished" else "In Progress",
                    color = hintColor,
                    style = TextStyle(fontSize = 10.sp)
                )
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
                Column(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text(
                        "$progress / $requirement",
                        color = textColor,
                        style = TextStyle(fontSize = 12.sp)
                    )
                    Text(
                        delimiter,
                        color = textColor,
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HabitCardPreview() {
    RoutinuityTheme {
        HabitCard(
            progressColor = Color.Red.copy(alpha = 0.4f),
            progress = 3,
            icon = R.drawable.ic_quantity,
            label = "Escape the Matrix",
            requirement = 3,
            delimiter = "Pages"
        )
    }
}
