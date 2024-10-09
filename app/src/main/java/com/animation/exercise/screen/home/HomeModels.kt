package com.animation.exercise.screen.home

object HomeModels {
    sealed interface Event {
        data object GoToBreathingExercises : Event
        data class ShowToast(val text: String) : Event
    }

    interface Actions {
        fun goToBreathingExercises()
        fun showToast(text: String)
    }

    object Preview {
        val actions: Actions
            get() = object : Actions {
                override fun goToBreathingExercises() = Unit
                override fun showToast(text: String) = Unit
            }
    }
}