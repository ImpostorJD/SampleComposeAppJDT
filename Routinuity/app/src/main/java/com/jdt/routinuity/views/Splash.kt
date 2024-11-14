package com.jdt.routinuity.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jdt.routinuity.R
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun SplashScreen(navController: NavController){
    Column (
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                R.mipmap.ic_launcher
            ),
            contentDescription = "Logo",
            Modifier.size(
                height = 250.dp,
                width = 250.dp
            )
        )
        Spacer(modifier = Modifier.height(150.dp))
        Button(
            onClick = {
                navController.navigate("auth/Login")
            },
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(25f)
        ) {
            Text(
                "Login".uppercase(),
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(fontSize =  15.sp),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("auth/Register")
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(25f)
                ),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(25f),
            colors = ButtonDefaults.buttonColors(
                containerColor =  MaterialTheme.colorScheme.background, // Or your desired color
                contentColor = MaterialTheme.colorScheme.primary // Text color
            )
        ) {
            Text(
                text = "Sign Up".uppercase(),
                style = TextStyle(fontSize = 15.sp),
            )
        }

    }
}

@Preview
@Composable
fun SplashScreenView(){
    val navController = rememberNavController()
    RoutinuityTheme {
        SplashScreen(navController)
    }
}