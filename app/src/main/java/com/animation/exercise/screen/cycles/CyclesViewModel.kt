package com.animation.exercise.screen.cycles

import com.animation.exercise.domain.model.BreathingCycle
import com.animation.exercise.domain.usecase.FlowAllBreathingCyclesUseCase
import com.animation.exercise.screen.cycles.CyclesModels.Event
import com.animation.exercise.screen.cycles.CyclesModels.State
import com.animation.exercise.viewmodel.StateEventViewModel
import com.animation.exercise.viewmodel.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CyclesViewModel @Inject constructor(
    private val getCycles: FlowAllBreathingCyclesUseCase
) : StateEventViewModel<State, Event>(), CyclesModels.Actions {

    override val stateFlow: MutableStateFlow<State> = MutableStateFlow(State())
    private var cyclesCount: Int = 1
    private var selected: BreathingCycle? = null

    init {
        launch {
            getCycles().collect { breathingCycles ->
                updateState {
                    val items = breathingCycles.map { cycle ->
                        CyclesModels.CyclesItem(
                            id = cycle.id,
                            title = cycle.name,
                            description = cycle.description,
                            icon = iconForCycle(cycle.id),
                            selected = false,
                            onClick = { selectCycle(cycle) }
                        )
                    }
                    copy(cycles = items)
                }
            }
        }
    }

    private fun selectCycle(cycle: BreathingCycle?) {
        selected = if (selected != cycle) cycle else null
        cyclesCount = 1
        updateSlider(cyclesCount)
        updateState {
            copy(
                showStartSection = selected != null,
                cycles = cycles.map {
                    val selected1 = it.id == selected?.id
                    it.copy(selected = selected1)
                })
        }
    }

    override fun updateSlider(count: Int) {
        cyclesCount = count
        updateState {
            copy(
                startCyclesCount = count,
                cyclesDuration = selected?.run { cycleDuration.times(count).toString() } ?: "",
            )
        }
    }

    private fun iconForCycle(cycleId: String): String = when (cycleId) {
        "cyclic_sighing" -> "cyclic"
        "sudarshan_kryia" -> "flower"
        "hyper" -> "hyper"
        else -> "missing"
    }

    override fun startBreathingExercise() {
        sendEvent(Event.GoToBreathingExercise(selected?.id ?: error("No selected item!"), cyclesCount))
        selectCycle(null)
    }

    override fun goBack() = sendEvent(Event.GoBack)
}
