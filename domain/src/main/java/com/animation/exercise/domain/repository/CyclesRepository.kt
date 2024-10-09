package com.animation.exercise.domain.repository

import com.animation.exercise.domain.model.BreathingCycle
import kotlinx.coroutines.flow.Flow

interface CyclesRepository {
    val cycles: Flow<List<BreathingCycle>>
    suspend fun getCycle(id: String): BreathingCycle
    suspend fun fetchBreathingCycles(): List<BreathingCycle>
}
