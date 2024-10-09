package com.animation.exercise

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AnimationExerciseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val debugTree = object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String =
                "(${element.fileName}:${element.lineNumber})"
        }
        Timber.plant(debugTree)
    }
}