package com.kth.githubapi.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kth.githubapi.presentation.navigation.Route
import kotlinx.coroutines.delay

fun NavGraphBuilder.splashRoute(navController: NavController) {
    composable(route = Route.Splash.route) {
        SplashScreen(
            onFinish = {
                navController.navigate(Route.Repositories.create("warrenth")) {
                    popUpTo(Route.Splash.route) { inclusive = true }
                }
            }
        )
    }
}

@Composable
fun SplashScreen(onFinish: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onFinish()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "GitHub API Viewer Loading..",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}