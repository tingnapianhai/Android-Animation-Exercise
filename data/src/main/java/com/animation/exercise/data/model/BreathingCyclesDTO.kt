package com.animation.exercise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreathingCyclesDTO(
    @SerialName("order")
    val ordering: List<String>,
    @SerialName("cycles_list")
    val cycles: List<BreathingCycleDTO>
)
