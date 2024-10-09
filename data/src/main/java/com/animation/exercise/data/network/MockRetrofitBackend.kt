package com.animation.exercise.data.network

import com.animation.exercise.data.model.BreathingCycleDTO
import com.animation.exercise.data.model.BreathingCyclesDTO
import com.animation.exercise.data.model.BreathingStepDTO
import com.animation.exercise.data.model.BreathingStepTypeDTO.EXHALE
import com.animation.exercise.data.model.BreathingStepTypeDTO.INHALE
import com.animation.exercise.data.model.BreathingStepTypeDTO.PAUSE

class MockRetrofitBackend : CyclesAPI {
    override fun getCycles(): BreathingCyclesDTO = BreathingCyclesDTO(
        ordering = listOf("cyclic_sighing", "sudarshan_kryia", "hyper"),
        cycles = listOf(
            BreathingCycleDTO(
                id = "cyclic_sighing",
                name = "Cyclic Sighing",
                description = "Reduce anxiety breathing",
                instructions = "It's easy to get stuck on counting the seconds, try to just breath deeply and find a flow when you got the timings down.",
                steps = listOf(
                    BreathingStepDTO(INHALE, 3),
                    BreathingStepDTO(PAUSE, 1),
                    BreathingStepDTO(EXHALE, 6),
                )
            ),
            BreathingCycleDTO(
                id = "sudarshan_kryia",
                name = "Sudarshan Kryia",
                description = "Yoga breathing for relaxation",
                instructions = "During this exercise, be mindful to keep the pauses for 2 seconds. If it feels good you can on your exhale let out a long sigh: Aaahhh",
                steps = listOf(
                    BreathingStepDTO(INHALE, 4),
                    BreathingStepDTO(PAUSE, 2),
                    BreathingStepDTO(EXHALE, 6),
                    BreathingStepDTO(PAUSE, 2),
                )
            ),
            BreathingCycleDTO(
                id = "hyper",
                name = "Hyper Ventilate",
                description = "In - out - a - lot",
                instructions = "Just go for it, and then maybe go lay down!",
                steps = listOf(
                    BreathingStepDTO(INHALE, 1),
                    BreathingStepDTO(EXHALE, 1),
                )
            ),
        )
    )
}