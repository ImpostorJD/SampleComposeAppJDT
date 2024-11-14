package com.jdt.routinuity.components

import RoundedRightTriangle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.jdt.routinuity.R
import com.jdt.routinuity.ui.theme.RoutinuityTheme



@Composable
fun LoginScreen(onSignUpClick: () -> Unit, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.TopStart)
                .offset((-20).dp, (-10).dp)
        ){
            BlurredCircle(blurColor = MaterialTheme.colorScheme.background)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .offset(0.dp, 50.dp)
        ){
            BlurredCircle(blurColor = MaterialTheme.colorScheme.background)
        }
        Column {
            Row(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
            ) {
                RoundedRightTriangle (
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = MaterialTheme.colorScheme.background,
                    cornerRadius = 5.dp,
                    direction =  TriangleDirection.Left
                ){
                    RoundedRightTriangle (
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        cornerRadius = 5.dp,
                        direction =  TriangleDirection.Left,
                        onClick = onSignUpClick,
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(start = 20.dp, bottom = 10.dp),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Start,
                        ){
                            Text("Sign Up", color = MaterialTheme.colorScheme.background)
                        }
                    }
                }

                RoundedRightTriangle(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(110.dp),
                    color = MaterialTheme.colorScheme.background,
                    cornerRadius = 5.dp,
                    direction =  TriangleDirection.Right
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
            ) {
                Row (
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopStart).padding(4.dp, 0.dp, 0.dp,0.dp)
                        .offset(0.dp, (-1).dp),
                ){
                    RoundedRightTriangle(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        color = MaterialTheme.colorScheme.primary,
                        cornerRadius = 0.dp,
                        direction =  TriangleDirection.Left,
                        orientation = TriangleOrientation.UpsideDown
                    )
                    // Align this triangle to the top left
                    RoundedRightTriangle(
                        color = MaterialTheme.colorScheme.background,
                        orientation = TriangleOrientation.UpsideDown,
                        modifier = Modifier
                            .width(110.dp)
                            .height(20.dp),
                        cornerRadius = 0.dp
                    )
                }
                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier =  Modifier.padding(16.dp)){
                    Text(
                        text = "LOGIN",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    TextField(
                        value = email,
                        onValueChange = { value ->
                            email = value
                        },
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                        label = { Text(text = "Email", color = MaterialTheme.colorScheme.primary) },
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

                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        value = password,
                        onValueChange = { value ->
                            password = value
                        },
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                        label = { Text(text = "Password", color = MaterialTheme.colorScheme.primary) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
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

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        onClick
                        = {},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(25f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Text(text = "Login")
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HorizontalDivider(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        )

                        Text(
                            text = "Or",
                            color = Color.Black,
                            modifier = Modifier.wrapContentSize(Alignment.Center)
                        )

                        HorizontalDivider(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(25f)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            VerticalDivider(Modifier.height(20.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Continue with Google")
                        }
                }







                }
            }
        }

    }
}

@Preview
@Composable
fun LoginPreview(){
    val navController = rememberNavController()
    RoutinuityTheme {
        LoginScreen({}, navController)
    }
}
