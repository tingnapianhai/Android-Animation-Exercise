package com.animation.exercise.data.mapper

import com.animation.exercise.data.model.BreathingCycleDTO
import com.animation.exercise.data.model.BreathingCyclesDTO
import com.animation.exercise.data.model.BreathingStepDTO
import com.animation.exercise.data.model.BreathingStepTypeDTO
import com.animation.exercise.domain.model.BreathingCycle
import com.animation.exercise.domain.model.BreathingStep
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Singleton
class CyclesMapper @Inject constructor() {

    fun mapToModel(dto: BreathingCyclesDTO): List<BreathingCycle> {
        val cycles = dto.cycles.associateBy { it.id }
        val dtoList = dto.ordering.map { id -> cycles[id]!! }
        return dtoList.map(::mapToModel)
    }

    private fun mapToModel(dto: BreathingCycleDTO): BreathingCycle = with(dto) {
        BreathingCycle(
            id = id,
            name = name,
            description = description,
            instructions = instructions,
            steps = steps.map(::mapToModel)
        )
    }

    private fun mapToModel(dto: BreathingStepDTO): BreathingStep = with(dto) {
        when (type) {
            BreathingStepTypeDTO.INHALE -> BreathingStep.Inhale(time.seconds)
            BreathingStepTypeDTO.PAUSE -> BreathingStep.Pause(time.seconds)
            BreathingStepTypeDTO.EXHALE -> BreathingStep.Exhale(time.seconds)
        }
    }

}