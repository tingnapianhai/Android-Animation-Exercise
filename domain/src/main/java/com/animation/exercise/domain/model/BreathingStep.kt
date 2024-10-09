package com.animation.exercise.domain.model

import kotlin.time.Duration

sealed interface BreathingStep {
    val duration: Duration

    data class Inhale(override val duration: Duration) : BreathingStep
    data class Pause(override val duration: Duration) : BreathingStep
    data class Exhale(override val duration: Duration) : BreathingStep
}
