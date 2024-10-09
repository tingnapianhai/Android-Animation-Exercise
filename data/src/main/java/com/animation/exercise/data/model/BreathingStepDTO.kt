package com.animation.exercise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * kotlinx-serializers can actually handle sealed classes like this so the extra type enum would be superfluous.
 * But this is more standard and gives me more to show... So here we go!
 */
data class BreathingStepDTO(
    @SerialName("step_type")
    val type: BreathingStepTypeDTO,
    @SerialName("seconds")
    val time: Int
)
