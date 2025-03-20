package com.jdt.routinuity.views.walkthrough

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun DataStoringInput (onValidationChanged: (Boolean) -> Unit, provideDataGetter: ((() -> String)) -> Unit){
    var projectIdInput by remember { mutableStateOf("") }

    LaunchedEffect(projectIdInput) {
        onValidationChanged(projectIdInput.isNotBlank())

    }

    LaunchedEffect(Unit) {
        provideDataGetter { projectIdInput }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            "Where to Store your Progress?",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Please create an appwrite account and create a project. You will host your own server to ensure you are in control of your data.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = projectIdInput,
                onValueChange = { input ->
                    projectIdInput = input
                    onValidationChanged(input.isNotBlank())
                },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                label = {
                    Text(
                        text = "Project Id",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                )
            )

        }

    }
}

@Preview
@Composable
fun DataStoringPreview(){
    RoutinuityTheme {
        DataStoringInput(
            {},
            provideDataGetter = {}
        )
    }
}
