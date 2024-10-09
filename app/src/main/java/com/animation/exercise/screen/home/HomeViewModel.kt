package com.animation.exercise.screen.home

import com.animation.exercise.domain.usecase.GetAllBreathingCyclesUseCase
import com.animation.exercise.screen.home.HomeModels.Event
import com.animation.exercise.viewmodel.EventViewModel
import com.animation.exercise.viewmodel.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCycles: GetAllBreathingCyclesUseCase
) : EventViewModel<Event>(), HomeModels.Actions {

    init {
        launch {
            fetchCycles().onFailure { error ->
                Timber.e(error, "Unable to get cycles")
            }
        }
    }

    override fun goToBreathingExercises() = sendEvent(Event.GoToBreathingExercises)

    override fun showToast(text: String) = sendEvent(Event.ShowToast(text))
}
