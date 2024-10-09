package com.animation.exercise.screen.cycles

object CyclesModels {

    data class State(
        val cycles: List<CyclesItem> = emptyList(),
        val showStartSection: Boolean = false,
        val startCyclesCount: Int = 1,
        val cyclesDuration: String = ""
    )

    data class CyclesItem(
        val id: String,
        val title: String,
        val description: String,
        val icon: String,
        val selected: Boolean,
        val onClick: () -> Unit
    )

    sealed interface Event {
        data object GoBack : Event
        data class GoToBreathingExercise(val cycleId: String, val cycles: Int) : Event
    }

    interface Actions {
        fun goBack()
        fun startBreathingExercise()
        fun updateSlider(count: Int)
    }

    object Preview {
        val state: State
            get() = State(
                cycles = listOf(
                    CyclesItem(
                        id = "1",
                        title = "Amaryllis",
                        description = "Magnolia and wine",
                        icon = "cyclic",
                        selected = false
                    ) {},
                    CyclesItem(
                        id = "2",
                        title = "Batman",
                        description = "Robin and Alfred",
                        icon = "flower",
                        selected = true
                    ) {}
                )
            )
        val actions: Actions
            get() = object : Actions {
                override fun goBack() = Unit
                override fun startBreathingExercise() = Unit
                override fun updateSlider(count: Int) = Unit
            }
    }
}
