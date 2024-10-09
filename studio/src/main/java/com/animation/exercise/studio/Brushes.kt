package com.animation.exercise.studio

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

@Composable
fun Brush.Companion.mainGradient() = verticalGradient(
    listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.inversePrimary)
)