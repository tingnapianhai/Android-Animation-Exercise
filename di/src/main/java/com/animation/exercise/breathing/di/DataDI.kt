package com.animation.exercise.breathing.di

import android.content.Context
import android.content.SharedPreferences
import com.animation.exercise.data.network.CyclesAPI
import com.animation.exercise.data.network.MockRetrofitBackend
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataDI {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("mock_backend", Context.MODE_PRIVATE)

    @Provides
    fun provideCyclesAPI(): CyclesAPI = MockRetrofitBackend()
}