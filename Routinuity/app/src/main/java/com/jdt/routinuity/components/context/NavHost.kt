package com.jdt.routinuity.components.context

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jdt.routinuity.views.AuthScreen
import com.jdt.routinuity.views.DashboardScreen
import com.jdt.routinuity.views.SplashScreen
import com.jdt.routinuity.views.WalkthroughScreen

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()

    AnimatedContent(
        targetState = navController.currentBackStackEntry?.destination?.route,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        }, label = "Animation Content"
    ){

        NavHost(navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController)
            }
//            composable("auth/{mode}") { backStackEntry ->
//                val mode = backStackEntry.arguments?.getString("mode") ?: "login"
//                AuthScreen(mode, navController)
//            }

            composable("dashboard") {
                DashboardScreen(navController)
            }

            composable("walkthrough") {
                WalkthroughScreen(navController)
            }

        }
        BackHandler (enabled = true){
        }
    }
}