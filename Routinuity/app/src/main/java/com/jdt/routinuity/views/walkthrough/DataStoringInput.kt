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
import com.jdt.routinuity.utils.AppwriteService

@Composable
fun DataStoringInput(
    onValidationChanged: (Boolean) -> Unit,
    provideDataGetter: ((() -> String)) -> Unit
) {
    var projectIdInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val appwriteService = remember { AppwriteService.getInstance() }

    suspend fun validate(): Boolean {
        if(projectIdInput.isBlank()){
            errorMessage = "ID cannot be empty"
        }else{
            try {
                appwriteService.setProjectId(projectIdInput)
                appwriteService.getClient()
                errorMessage = null
            } catch (e: IllegalStateException) {
                errorMessage = "Invalid Project ID. Please check your Appwrite credentials."
                Log.d("appwrite", errorMessage ?: "Unknown error")
            }
        }

        return errorMessage == null
    }

    LaunchedEffect(Unit) {
        provideDataGetter { projectIdInput }
    }

    LaunchedEffect(projectIdInput) {
        onValidationChanged?.invoke(validate())

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
            "Please create an Appwrite account and create a project. You will host your own server to ensure you are in control of your data.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Input Row
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
                label = { Text("Project ID", color = MaterialTheme.colorScheme.primary) },
                modifier = Modifier.weight(1f),
                isError = errorMessage != null,
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

        // Show error message if there's an issue
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun DataStoringPreview() {
    RoutinuityTheme {
        DataStoringInput(
            onValidationChanged = {},
            provideDataGetter = {}
        )
    }
}

