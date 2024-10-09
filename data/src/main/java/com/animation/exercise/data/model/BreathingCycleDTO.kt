package com.animation.exercise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreathingCycleDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("instructions")
    val instructions: String,
    @SerialName("breathing_steps")
    val steps: List<BreathingStepDTO>
)