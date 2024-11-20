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
    onParentSubmit: ((Boolean) -> Unit)? = null,
    onSubmitTriggered: ((() -> Unit) -> Unit)? = null
){
    LaunchedEffect(Unit) {
        onSubmitTriggered?.invoke {
            if (hasParentSubmit) {
                onParentSubmit?.invoke(true)
            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp, 8.dp)
    ){
        Text("Profile",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "",
            onValueChange = {},
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
            label = { Text(text = "Name", color = MaterialTheme.colorScheme.primary) },
            modifier = Modifier.fillMaxWidth(),
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
        Spacer(modifier = Modifier.height(8.dp))
        if(!hasParentSubmit){
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {},
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
        ProfileView()
    }
}
