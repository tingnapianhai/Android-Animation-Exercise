package com.animation.exercise.breathing.di

import com.animation.exercise.data.repository.CyclesRepositoryImpl
import com.animation.exercise.domain.repository.CyclesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCyclesRepo(repo: CyclesRepositoryImpl): CyclesRepository
}
