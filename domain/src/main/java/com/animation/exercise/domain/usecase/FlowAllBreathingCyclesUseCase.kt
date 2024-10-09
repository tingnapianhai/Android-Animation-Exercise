package com.animation.exercise.domain.usecase

import com.animation.exercise.domain.model.BreathingCycle
import com.animation.exercise.domain.repository.CyclesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowAllBreathingCyclesUseCase @Inject constructor(
    private val repo: CyclesRepository
) : UseCase() {
    operator fun invoke(): Flow<List<BreathingCycle>> = handleFlow { repo.cycles }
}
