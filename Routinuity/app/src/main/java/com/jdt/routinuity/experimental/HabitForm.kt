package com.jdt.routinuity.experimental

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.R
import com.jdt.routinuity.components.context.CategoryButton
import com.jdt.routinuity.components.context.CategorySelector
import com.jdt.routinuity.components.habitform.FieldButton
import com.jdt.routinuity.components.habitform.RequirementsButton
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun HabitForm(
    onCollapse:() -> Unit,
    icon: Int = R.drawable.ic_chevron_down,
    targetId: Int? = null,
    labels: List<Pair<String, Color>>,
) {
    var selectedCategory by remember { mutableStateOf("Daily") }
    var selectedArea by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Quantity") }

    Column (modifier = Modifier
        .fillMaxSize()
        .background(
            color = MaterialTheme.colorScheme.background
        ),
    ){
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
       ){
           Button(
               onClick = {
                   onCollapse.invoke()
               },
               Modifier.size(50.dp),
               contentPadding = PaddingValues(10.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor =  Color.Transparent,
                   contentColor = MaterialTheme.colorScheme.primary,
               ),
           ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                )
           }
           Text(
               if(targetId == null) "Add habit" else "Edit Habit",
               modifier = Modifier
                   .fillMaxWidth(),
               style = TextStyle(
                   color = MaterialTheme.colorScheme.primary,
                   fontSize = 25.sp,
                   fontWeight = FontWeight.Bold
               )
           )
           if(targetId != null){
               Button(
                   onClick = {
                       onCollapse.invoke()
                   },
                   Modifier.size(50.dp),
                   contentPadding = PaddingValues(10.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor =  Color.Transparent,
                       contentColor = MaterialTheme.colorScheme.primary,
                   ),
               ) {
                   Icon(
                       painter = painterResource(id = R.drawable.ic_trash),
                       contentDescription = "",
                   )
               }
           }
       }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ){
            item {
                TextField(
                    value = "",
                    onValueChange = { },
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                    label = { Text(text = "Habit Title", color = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 4.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor =  MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.padding(20.dp, 0.dp),
                    text = "Select Area",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(20.dp, 0.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                    labels.map { pair ->
                        FieldButton(
                            label = pair.first,
                            isActive = (pair.first == selectedArea),
                            onClick = {
                                selectedArea = pair.first
                            },
                            inactiveContentColor = MaterialTheme.colorScheme.inversePrimary,
                            inactiveContainerColor = Color.Transparent,
                            activeContentColor = MaterialTheme.colorScheme.primary,
                            activeContainerColor = pair.second.copy(alpha = 0.6f),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.padding(20.dp, 0.dp),
                    text = "Repeat Cycle",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
                CategorySelector { value ->
                    selectedCategory = value
                }

                Spacer(modifier = Modifier.height(10.dp))

                if(selectedCategory == "Custom"){

                    Spacer(modifier = Modifier.height(10.dp))
                }

                Text(
                    modifier = Modifier.padding(20.dp, 0.dp),
                    text = "Requirements",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        listOf("Quantity", "Timer", "Repetition").forEach { type ->
                            RequirementsButton(
                                modifier = Modifier.weight(1f),
                                label = type,
                                isActive = (type == selectedType),
                                onClick = {
                                    selectedType = type
                                },
                                inactiveContentColor = MaterialTheme.colorScheme.inversePrimary,
                                inactiveContainerColor = Color.Transparent,
                                activeContentColor = MaterialTheme.colorScheme.background,
                                activeContainerColor = MaterialTheme.colorScheme.primary,
                                icon = when (type) {
                                    "Quantity" -> R.drawable.ic_quantity
                                    "Timer" -> R.drawable.ic_timer
                                    "Repetition" -> R.drawable.ic_repetition
                                    else -> R.drawable.ic_quantity
                                }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                    Row{

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(
                            modifier = Modifier.padding(20.dp, 0.dp),
                            text = "Submit",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun HabitFormPreview(){
    RoutinuityTheme {
        HabitForm(
            onCollapse = {},
            labels = listOf(
                "Label 1" to Color.Red,
                "Label 2" to Color.Green,
                "Label 3" to Color.Blue,
                "Label 4" to Color.Yellow,
                "Label 5" to Color.Magenta,
                "Label 6" to Color.Cyan
            )
        )
    }
}