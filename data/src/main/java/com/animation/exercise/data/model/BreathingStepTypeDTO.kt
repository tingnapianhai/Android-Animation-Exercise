package com.animation.exercise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BreathingStepTypeDTO {
    @SerialName("step_type_inhale")
    INHALE,

    @SerialName("step_type_pause")
    PAUSE,

    @SerialName("step_type_exhale")
    EXHALE
}
