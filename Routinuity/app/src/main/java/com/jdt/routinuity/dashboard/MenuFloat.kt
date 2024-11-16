package com.jdt.routinuity.dashboard

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.R

@Composable
fun MenuFloat(
    onMenuClick: (String) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    foregroundColor: Color = MaterialTheme.colorScheme.background,
) {
    var showMenu by remember { mutableStateOf(false) }

    val bottomMenuAlpha by animateFloatAsState(
        targetValue = if (showMenu) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    val buttonWidth by animateDpAsState(
        targetValue = if (showMenu) 250.dp else 50.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    val buttonOffset by animateDpAsState(
        targetValue = if (!showMenu) (-10).dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    val iconAlpha by animateFloatAsState(
        targetValue = if (showMenu) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "Icon Fade"
    )
    val iconAlphaCollapsed by animateFloatAsState(
        targetValue = if (!showMenu) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "Icon Fade"
    )

    Box(modifier =  Modifier.fillMaxSize()){
        if (showMenu){

            Row (
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(15.dp, 0.dp)
                    .offset(0.dp, -(30).dp)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(6.dp)
                    .graphicsLayer { alpha = bottomMenuAlpha },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onMenuClick.invoke("habit-form")
                    },
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(0.dp)
                        .clip(RoundedCornerShape(75f))
                        .graphicsLayer { alpha = iconAlpha },
                    shape = RoundedCornerShape(75f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  foregroundColor,
                        contentColor = backgroundColor,
                    ),
                ){
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Text("Habit", style = TextStyle(fontSize = 10.sp))
                    }
                }
                Button(
                    onClick = {
                        onMenuClick.invoke("profile")
                    },
                    modifier = Modifier
                        .weight(0.2f)
                        .border(
                            width = 2.dp,
                            color = backgroundColor,
                            shape = RoundedCornerShape(50f)
                        )
                        .padding(0.dp)
                        .graphicsLayer { alpha = iconAlpha },
                    shape = RoundedCornerShape(50f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color.Transparent,
                        contentColor = foregroundColor
                    )
                ){
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_stats),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Button(
                    onClick = {
                        onMenuClick.invoke("settings")
                    },
                    modifier = Modifier
                        .weight(0.2f)
                        .border(
                            width = 2.dp,
                            color = backgroundColor,
                            shape = RoundedCornerShape(50f)
                        ).graphicsLayer { alpha = iconAlpha },
                    shape = RoundedCornerShape(50f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color.Transparent,
                        contentColor = foregroundColor,
                    )
                ){
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_settings),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
                Button(
                    onClick = {
                        showMenu = false
                    },
                    modifier = Modifier
                        .weight(0.2f)
                        .border(
                            width = 2.dp,
                            color = backgroundColor,
                            shape = RoundedCornerShape(50f)
                        ).graphicsLayer { alpha = iconAlpha },
                    shape = RoundedCornerShape(50f),

                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color.Transparent,
                        contentColor = foregroundColor
                    )
                ){
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_collapse),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }else{
            Button(
                    onClick = {
                        showMenu = true
                    },
            modifier = Modifier
                .height(50.dp)
                .width(buttonWidth)
                .align(Alignment.BottomEnd)
                .offset(buttonOffset, -(30).dp)
                .border(
                    width = 2.dp,
                    color = backgroundColor,
                    shape = CircleShape
                ).padding(0.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =  backgroundColor,
                contentColor = foregroundColor
            )
            ){
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                        .graphicsLayer { alpha = iconAlphaCollapsed },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Icon(
                        painter = painterResource(R.drawable.ic_menu),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}