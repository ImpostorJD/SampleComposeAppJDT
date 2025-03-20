package com.jdt.routinuity.components.context

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jdt.routinuity.utils.DataStoreManager
import com.jdt.routinuity.views.AuthScreen
import com.jdt.routinuity.views.DashboardScreen
import com.jdt.routinuity.views.SplashScreen
import com.jdt.routinuity.views.WalkthroughScreen

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext
    val dataStoreManager = remember { DataStoreManager(context) }

    val firstVisit by dataStoreManager.getFinishedWalkthrough().collectAsState(initial = false)

    AnimatedContent(
        targetState = navController.currentBackStackEntry?.destination?.route,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        }, label = "Animation Content"
    ){
        NavHost(navController, startDestination = if (firstVisit) "splash" else "dashboard") {
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