package com.animation.exercise.domain.usecase

import com.animation.exercise.domain.model.BreathingCycle
import com.animation.exercise.domain.repository.CyclesRepository
import javax.inject.Inject

class GetBreathingCycleUseCase @Inject constructor(
    private val repo: CyclesRepository
) : UseCase() {
    suspend operator fun invoke(breathingCycleId: String): Result<BreathingCycle> = handleSuspendResult {
        repo.getCycle(breathingCycleId)
    }
}
