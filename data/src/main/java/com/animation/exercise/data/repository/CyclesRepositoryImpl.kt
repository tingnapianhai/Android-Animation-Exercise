package com.animation.exercise.data.repository

import com.animation.exercise.data.mapper.CyclesMapper
import com.animation.exercise.data.network.CyclesAPI
import com.animation.exercise.domain.model.BreathingCycle
import com.animation.exercise.domain.repository.CyclesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CyclesRepositoryImpl @Inject constructor(
    private val cyclesAPI: CyclesAPI,
    private val mapper: CyclesMapper
) : CyclesRepository {

    // Initial fetch of cycles could be handled in different ways:
    // 1. No initial list, wait for "manual" fetch
    // 2. use an init {} function here and perform fetch using a background thread (CoroutineScopes.app)
    // 3. Create a Bootstrap Service / Use Case that tells all repositories to fetch data, and report when done.
    //  - In this instance I've gone for 1.

    private val cyclesCache: MutableStateFlow<List<BreathingCycle>> = MutableStateFlow(emptyList())
    override val cycles: Flow<List<BreathingCycle>> get() = cyclesCache

    override suspend fun getCycle(id: String): BreathingCycle =
        cycles.first().first { cycle -> cycle.id == id }

    override suspend fun fetchBreathingCycles(): List<BreathingCycle> {
        val dto = cyclesAPI.getCycles()
        return mapper.mapToModel(dto)
            .also { newCycles -> cyclesCache.update { newCycles } }
    }
}