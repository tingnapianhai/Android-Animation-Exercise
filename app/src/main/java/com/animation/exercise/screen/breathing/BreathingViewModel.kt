package com.animation.exercise.screen.breathing

import androidx.lifecycle.SavedStateHandle
import com.animation.exercise.domain.model.BreathingCycle
import com.animation.exercise.domain.model.BreathingStep
import com.animation.exercise.domain.usecase.GetBreathingCycleUseCase
import com.animation.exercise.screen.breathing.BreathingModels.Event
import com.animation.exercise.screen.breathing.BreathingModels.State
import com.animation.exercise.screen.breathing.BreathingRoute.cycleId
import com.animation.exercise.screen.breathing.BreathingRoute.cyclesCount
import com.animation.exercise.viewmodel.StateEventViewModel
import com.animation.exercise.viewmodel.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class BreathingViewModel @Inject constructor(
    savedSavedStateHandle: SavedStateHandle,
    private val getBreathingCycle: GetBreathingCycleUseCase
) : StateEventViewModel<State, Event>(), BreathingModels.Actions {

    private val cycleId = savedSavedStateHandle.cycleId
    private val maxCycles = savedSavedStateHandle.cyclesCount
    override val stateFlow = MutableStateFlow(State())
    private lateinit var breathingCycle: BreathingCycle
    private var activeRun: Job? = null
    private var activeRunStarted: Instant? = null

    init {
        launch {
            getBreathingCycle(cycleId).fold(
                onSuccess = { cycle ->
                    breathingCycle = cycle
                    updateState {
                        copy(
                            title = "${breathingCycle.name} (${breathingCycle.cycleDuration.times(maxCycles)})",
                            instructions = breathingCycle.instructions,
                        )
                    }
                    preparedToStartState()
                },
                onFailure = { error -> Timber.e(error, "Unable to get breathing cycle") })
        }
    }

    private fun preparedToStartState() {
        updateState {
            copy(
                timeSpent = 0.seconds.toString(),
                breathingStep = null,
                stepTimeLeft = "",
                cyclesLeft = maxCycles,
                showSuccess = false
            )
        }
    }


    override fun cancel() {
        activeRun?.cancel()
        sendEvent(Event.GoBack)
    }

    override fun start() {
        if (activeRun != null) return Timber.d("Cycles already running")
        startCycles()
    }

    override fun reset() {
        if (activeRun == null) return Timber.d("Cycles already halted")
        activeRun?.cancel()
        activeRun = null
        activeRunStarted = null
        preparedToStartState()
    }

    private fun startCycles() {
        activeRun = launch {

            launch {
                while (true) {
                    updateState { copy(timeSpent = timeSpentOnActive()) }
                    delay(1.seconds)
                }
            }

            activeRunStarted = Clock.System.now()
            (maxCycles downTo 1).forEach { cycleIndex ->
                updateState { copy(cyclesLeft = cycleIndex) }
                Timber.d("Cycles: $cycleIndex / $maxCycles")
                breathingCycle.steps.forEach { step ->
                    Timber.d("step $step")
                    updateState {
                        copy(
                            breathingStep = step.mapToUiModel()
                        )
                    }
                    // Extra update to breathing step time
                    val stepCountdown = launch {
                        var secondsLeft = step.duration
                        while (secondsLeft.inWholeSeconds > 0) {
                            Timber.d("SecondsLeft $secondsLeft")
                            updateState { copy(stepTimeLeft = secondsLeft.toString()) }
                            delay(1.seconds)
                            secondsLeft -= 1.seconds
                        }
                    }
                    delay(step.duration)
                    stepCountdown.cancel()
                }
            }
            updateState { copy(cyclesLeft = 0) }
            showSuccess()

        }
    }

    private fun showSuccess() {
        activeRun?.cancel()
        updateState { copy(showSuccess = true) }
    }

    override fun confirmSuccess() {
        updateState { copy(showSuccess = false) }
        sendEvent(Event.GoBack)
    }

    private fun timeSpentOnActive(): String {
        val duration = activeRunStarted?.let { Clock.System.now() - it } ?: return ZERO.toString()
        return duration.truncate(DurationUnit.SECONDS).toString()
    }

    private fun Duration.truncate(unit: DurationUnit): Duration =
        toLong(unit).toDuration(unit)

    private fun BreathingStep.mapToUiModel(): BreathingModels.Step = when (this) {
        is BreathingStep.Exhale -> BreathingModels.Step.BreathOut(duration)
        is BreathingStep.Inhale -> BreathingModels.Step.BreathIn(duration)
        is BreathingStep.Pause -> BreathingModels.Step.Hold(duration)
    }
}

