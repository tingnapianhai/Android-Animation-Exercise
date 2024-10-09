package com.animation.exercise.screen.breathing

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.animation.exercise.viewmodel.collectEvents
import com.animation.exercise.viewmodel.collectState
import javax.annotation.CheckReturnValue

object BreathingRoute {
    private const val ARG_CYCLE_ID = "cycle_id"
    private const val ARG_CYCLES_COUNT = "cycles_count"
    const val PATH = "breathing/{${ARG_CYCLE_ID}}/{$ARG_CYCLES_COUNT}"

    @CheckReturnValue
    fun path(cycleId: String, cyclesCount: String) = "breathing/${cycleId}/$cyclesCount"

    context (BreathingViewModel)
    val SavedStateHandle.cycleId
        get(): String = this.get<String>(ARG_CYCLE_ID) ?: error("Missing Argument $ARG_CYCLE_ID")

    context (BreathingViewModel)
    val SavedStateHandle.cyclesCount
        get(): Int = this.get<String>(ARG_CYCLES_COUNT)?.toInt() ?: error("Missing Argument $ARG_CYCLES_COUNT")

    @Composable
    fun Route(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        val context = LocalContext.current
        val viewModel: BreathingViewModel = hiltViewModel()

        val state by viewModel.collectState()
        BreathingScreen(state = state, actions = viewModel)
        viewModel.collectEvents { event ->
            when (event) {
                is BreathingModels.Event.Toast -> Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
                BreathingModels.Event.GoBack -> navController.navigateUp()
            }
        }
    }

}
