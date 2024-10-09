package com.animation.exercise.screen.breathing

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.animation.exercise.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object BreathingModels {

    data class State(
        val title: String = "",
        val timeSpent: String = "",
        val instructions: String = "",
        val breathingStep: Step? = null,
        val stepTimeLeft: String = "",
        val cyclesLeft: Int = 0,
        val showSuccess: Boolean = false
    )

    sealed interface Step {
        val duration: Duration

        data class BreathIn(override val duration: Duration) : Step
        data class BreathOut(override val duration: Duration) : Step
        data class Hold(override val duration: Duration) : Step

        @Composable
        fun getLabel() = when (this) {
            is BreathIn -> R.string.breath_action_inhale
            is BreathOut -> R.string.breath_action_exhale
            is Hold -> R.string.breath_action_pause
        }.let { stringResource(id = it) }
    }

    sealed interface Event {
        data object GoBack : Event
        data class Toast(val text: String) : Event
    }

    interface Actions {
        fun cancel()
        fun start()
        fun reset()
        fun confirmSuccess()
    }

    object Preview {
        val state: State
            get() = State(
                title = "Breath-ups (3m)",
                timeSpent = "1m 20s",
                instructions = "Follow along!",
                breathingStep = Step.BreathIn(3.seconds),
                cyclesLeft = 12,
                showSuccess = false
            )
        val actions: Actions
            get() = object : Actions {
                override fun cancel() = Unit
                override fun start() = Unit
                override fun reset() = Unit
                override fun confirmSuccess() = Unit
            }
    }
}