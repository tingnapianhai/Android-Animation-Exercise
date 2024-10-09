package com.animation.exercise.screen.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.animation.exercise.screen.cycles.CyclesRoute
import com.animation.exercise.viewmodel.collectEvents

object HomeRoute {
    const val PATH = "home"

    @Composable
    fun Route(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        val context = LocalContext.current
        val viewModel: HomeViewModel = hiltViewModel()

        HomeScreen(actions = viewModel)
        viewModel.collectEvents { event ->
            when (event) {
                HomeModels.Event.GoToBreathingExercises -> navController.navigate(CyclesRoute.PATH)
                is HomeModels.Event.ShowToast -> {
                    Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}