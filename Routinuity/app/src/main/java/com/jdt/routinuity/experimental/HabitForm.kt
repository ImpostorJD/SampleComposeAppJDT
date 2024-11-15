package com.jdt.routinuity.experimental

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun HabitForm(){
    Text("Hello World")
}

@Preview
@Composable
fun HabitFormPreview(){
    RoutinuityTheme {
        HabitForm()
    }
}