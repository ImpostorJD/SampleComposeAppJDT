package com.jdt.routinuity.views

import android.widget.Space
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jdt.routinuity.R
import com.jdt.routinuity.components.BiDirectionalScrollableWeekCalendar
import com.jdt.routinuity.components.BlurredCircle
import com.jdt.routinuity.components.CategorySelector
import com.jdt.routinuity.components.HabitCard
import com.jdt.routinuity.components.ProgressCard
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DashboardScreen(navController: NavController){

    val today = LocalDate.now()
    var selectedDay by remember { mutableStateOf(value = today) }
    var activeCategory by remember { mutableStateOf("Daily") }
    val formatter = DateTimeFormatter.ofPattern("dd, MMM yyyy")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ){
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary),
                ){
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .align(Alignment.TopStart)
                            .offset((-100).dp, (-60).dp)
                    ){
                        BlurredCircle(blurColor = MaterialTheme.colorScheme.background)
                    }
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .align(Alignment.CenterEnd)
                            .offset(90.dp, 15.dp)
                    ){
                        BlurredCircle(blurColor = MaterialTheme.colorScheme.background)
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
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 15.sp,
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Your Name!",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = today.format(formatter),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 15.sp,
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row (modifier = Modifier.fillMaxWidth()){
                            BiDirectionalScrollableWeekCalendar(initialSelectedDay = selectedDay,
                                onClick = { value ->
                                    selectedDay = value
                                }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(10.dp).fillMaxWidth())

                CategorySelector { value ->
                    activeCategory = value
                }

                Spacer(Modifier.height(10.dp).fillMaxWidth())
                ProgressCard(
                    progress = 0.4f,
                    background = MaterialTheme.colorScheme.primary,
                    progressColor = MaterialTheme.colorScheme.background
                )
                Spacer(Modifier.height(10.dp).fillMaxWidth())

                HabitCard(
                    progressColor = Color.Red.copy(alpha = 0.4f),
                    progress = 3,
                    icon = R.drawable.ic_quantity,
                    label = "Escape the Matrix",
                    requirement = 3,
                    delimiter = "Pages"
                )
                Spacer(Modifier.height(10.dp).fillMaxWidth())

                HabitCard(
                    progressColor = Color.Red.copy(alpha = 0.4f),
                    progress = 0,
                    icon = R.drawable.ic_quantity,
                    label = "Escape the Matrix",
                    requirement = 3,
                    delimiter = "Pages"
                )
                Spacer(Modifier.height(10.dp).fillMaxWidth())

                HabitCard(
                    progressColor = Color.Red.copy(alpha = 0.4f),
                    progress = 2,
                    icon = R.drawable.ic_quantity,
                    label = "Escape the Matrix",
                    requirement = 3,
                    delimiter = "Pages"
                )
                Spacer(Modifier.height(10.dp).fillMaxWidth())

                HabitCard(
                    progressColor = Color.Red.copy(alpha = 0.4f),
                    progress = 1,
                    icon = R.drawable.ic_quantity,
                    label = "Escape the Matrix",
                    requirement = 3,
                    delimiter = "Pages"
                )
                Spacer(Modifier.height(10.dp).fillMaxWidth())

                HabitCard(
                    progressColor = Color.Red.copy(alpha = 0.4f),
                    progress = 1,
                    icon = R.drawable.ic_quantity,
                    label = "Escape the Matrix",
                    requirement = 3,
                    delimiter = "Pages"
                )
                Spacer(Modifier.height(10.dp).fillMaxWidth())
            }

        }

        Row (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(25.dp, 0.dp)
                .offset(0.dp, -(30).dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50.dp)
                )
                .fillMaxWidth()
                .height(50.dp)
                .padding(6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(2f)
                    .clip(RoundedCornerShape(75f)),
                shape = RoundedCornerShape(75f),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
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
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Habit", style = TextStyle(fontSize = 14.sp))
                }
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50f)
                    )
                    .padding(0.dp),
                shape = RoundedCornerShape(50f),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.background
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
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50f)
                    ),
                shape = RoundedCornerShape(50f),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.background
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
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview(){
    val navController = rememberNavController()
    RoutinuityTheme {
        DashboardScreen(navController)
    }
}

