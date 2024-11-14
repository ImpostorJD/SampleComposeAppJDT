package com.jdt.routinuity.views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jdt.routinuity.components.LoginScreen
import com.jdt.routinuity.components.RegisterScreen
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun AuthScreen(mode : String, navController: NavController) {
    var isLoginForm by remember { mutableStateOf(value = mode == "Login") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        AnimatedContent(
            targetState = isLoginForm,
            transitionSpec = {
                fadeIn() togetherWith fadeOut() using SizeTransform { _, _ ->
                    tween(durationMillis = 300)
                }
            }
        ) { targetLoginForm ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp))
                ,
                contentAlignment = Alignment.TopStart
            ) {
                if (targetLoginForm) {
                    LoginScreen(onSignUpClick = { isLoginForm = false }, navController = navController)
                } else {
                    RegisterScreen(onLoginClick = { isLoginForm = true }, navController = navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview(){
    val navController = rememberNavController()
    RoutinuityTheme {

        AuthScreen("Login", navController)
    }
}

