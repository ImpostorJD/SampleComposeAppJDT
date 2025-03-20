package com.jdt.routinuity.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun ProfileView(
    hasParentSubmit: Boolean = false,
    onParentSubmit: (((() -> String)) -> Unit)? = null,
    onValidationChanged: ((Boolean) -> Unit)? = null,
) {
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }


    fun validate(): Boolean {
        nameError = when {
            name.isBlank() -> "Name cannot be empty"
            !name.matches(Regex("^[A-Z][a-zA-Z ]*\$")) -> "Enter a valid name (start with uppercase, only letters and spaces)"
            else -> null
        }
        return nameError == null
    }

    LaunchedEffect(name) {

        onValidationChanged?.invoke(validate())

    }

    if (hasParentSubmit){
        LaunchedEffect(Unit) {
            onParentSubmit?.let { it { name } }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp, 8.dp)
    ) {
        if (hasParentSubmit) {
            Text(
                "Profile",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = {
                name = it
                validate() // Validate on input change
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
            label = { Text(text = "Name", color = MaterialTheme.colorScheme.primary) },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError != null, // Show error highlight
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            )
        )

        // Show error message if there's an issue
        if (nameError != null) {
            Text(
                text = nameError!!,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (!hasParentSubmit) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (validate()) {
                        // Process submission only if valid
                        onParentSubmit?.let { it { name } }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@Preview
@Composable
fun ProfileViewPreview(){
    RoutinuityTheme {
        ProfileView(
            hasParentSubmit = true,
            onParentSubmit = {}
        )
    }
}
