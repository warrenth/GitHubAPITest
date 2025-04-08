package com.kth.githubapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kth.githubapi.presentation.screen.issuesRoute
import com.kth.githubapi.presentation.screen.repositoriesRoute
import com.kth.githubapi.presentation.screen.splashRoute

/**
 * NavHostController : backstack, navigate 등을 제어
 * NavHost : route 들을 실행 해주는 container
 * rememberNavController() : recomposition 중 navController 상태 유지
 *
 */
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash.route
    ) {
        splashRoute(navController)
        repositoriesRoute()
        issuesRoute()
    }
}