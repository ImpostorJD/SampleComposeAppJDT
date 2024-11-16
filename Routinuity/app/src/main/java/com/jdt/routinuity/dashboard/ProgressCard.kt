package com.jdt.routinuity.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.R
import com.jdt.routinuity.components.shapes.BlurredCircle
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun ProgressCard(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.primary,
    progressColor: Color = MaterialTheme.colorScheme.background,
    progress: Float
){
    var progressWidth by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier.padding(10.dp, 0.dp)
            .height(120.dp)
            .fillMaxWidth()
            .background(color = background, shape = RoundedCornerShape(10.dp)
            ).clip(RoundedCornerShape(10)),

        ){
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.TopStart)
                .offset((-100).dp, (-60).dp)
        ){
            BlurredCircle(blurColor = progressColor)
        }
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterEnd)
                .offset((80).dp, (-40).dp)
        ){
            BlurredCircle(blurColor = progressColor)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 5.dp)
        )
        {
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text("Daily Goals",
                    style = TextStyle(
                        color = progressColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Icon(
                    painterResource(id = R.drawable.ic_task),
                    contentDescription = "",
                    tint = progressColor,
                    modifier = Modifier.weight(0.5f)
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.6f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "2/10 Completed",
                    style = TextStyle(
                        color = progressColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box (
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(
                            color = progressColor.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(25f)
                        ).clip(RoundedCornerShape(25f))
                        .onGloballyPositioned { w ->
                            progressWidth = w.size.width.toFloat()
                        }
                ){

                    Box (
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(with(LocalDensity.current) {
                                (progressWidth * progress).toDp()
                            })
                            .background(
                                color = progressColor,
                                shape = RoundedCornerShape(25f)
                            )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "${(progress * 100).toInt()}%",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        color = progressColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgressCardPreview(){
    RoutinuityTheme {
        ProgressCard(
            progress = 0.4f
        )
    }
}

