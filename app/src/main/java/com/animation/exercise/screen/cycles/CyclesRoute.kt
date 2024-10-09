package com.animation.exercise.screen.cycles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.animation.exercise.screen.breathing.BreathingRoute
import com.animation.exercise.viewmodel.collectEvents
import com.animation.exercise.viewmodel.collectState

object CyclesRoute {
    const val PATH = "cycles"

    @Composable
    fun Route(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        val viewModel: CyclesViewModel = hiltViewModel()

        val state by viewModel.collectState()
        CyclesScreen(state = state, actions = viewModel)
        viewModel.collectEvents { event ->
            when (event) {
                CyclesModels.Event.GoBack -> navController.navigateUp()
                is CyclesModels.Event.GoToBreathingExercise -> BreathingRoute
                    .path(event.cycleId, event.cycles.toString())
                    .let(navController::navigate)
            }
        }
    }
}