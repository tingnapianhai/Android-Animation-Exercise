package com.animation.exercise.domain.model

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Assuming localised backend strings.
 * If not localised by backend, some identifier to map to client localised strings.
 */
data class BreathingCycle(
    val id: String,
    val name: String,
    val description: String,
    val instructions: String,
    val steps: List<BreathingStep>
) {
    val cycleDuration: Duration get() = steps.fold(0.seconds) { acc, step ->  acc + step.duration }
}
