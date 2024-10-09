package com.animation.exercise.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.animation.exercise.screen.breathing.BreathingRoute
import com.animation.exercise.screen.cycles.CyclesRoute
import com.animation.exercise.screen.home.HomeRoute

@Composable
fun AppNavigation(navController: NavHostController) {
    // Navigation can be made very complex/simple scalable etc. based on needs.
    // Since the needs here are very basic, and complexity for the sake of being complex is confusing:
    // - This navigation pattern be very basic
    NavHost(navController = navController, startDestination = HomeRoute.PATH) {
        with(HomeRoute) { composable(PATH) { Route(navController = navController, navBackStackEntry = it) } }
        with(CyclesRoute) { composable(PATH) { Route(navController = navController, navBackStackEntry = it) } }
        with(BreathingRoute) { composable(PATH) { Route(navController = navController, navBackStackEntry = it) } }

    }
}