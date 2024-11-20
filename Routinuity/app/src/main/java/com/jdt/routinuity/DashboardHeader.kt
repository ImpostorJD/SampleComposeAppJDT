package com.jdt.routinuity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.dashboard.BiDirectionalScrollableWeekCalendar
import com.jdt.routinuity.components.shapes.BlurredCircle
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import com.jdt.routinuity.utils.formatter
import java.time.LocalDate

@Composable
fun DashboardHeader(
    today: LocalDate,
    selectedDay: LocalDate,
    textColor: Color = MaterialTheme.colorScheme.background,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    name: String,
    changeSelectedDay: (LocalDate) -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
    ){
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.TopStart)
                .offset((-100).dp, (-60).dp)
        ){
            BlurredCircle(blurColor = textColor)
        }
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterEnd)
                .offset(90.dp, 15.dp)
        ){
            BlurredCircle(blurColor = textColor)
        }
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ){
            Text(
                text = "Welcome,",
                style = TextStyle(
                    color = textColor,
                    fontSize = 15.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$name!",
                style = TextStyle(
                    color = textColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = today.format(formatter),
                style = TextStyle(
                    color = textColor,
                    fontSize = 15.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row (modifier = Modifier.fillMaxWidth()){
                BiDirectionalScrollableWeekCalendar(initialSelectedDay = selectedDay,
                    textColor = textColor,
                    activeColor = backgroundColor,
                    onClick = { value ->
                       changeSelectedDay(value)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DashboardHeaderPreview(){
    val today = LocalDate.now()

    var selectedDay by remember { mutableStateOf(value = today) }

    RoutinuityTheme {
        DashboardHeader(
            today = today,
            selectedDay = selectedDay,
            textColor = MaterialTheme.colorScheme.background,
            backgroundColor = MaterialTheme.colorScheme.primary,
            name = "John Doe",
            changeSelectedDay = { value ->
                selectedDay = value
            }
        )
    }
}
