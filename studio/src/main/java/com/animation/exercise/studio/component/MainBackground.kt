package com.animation.exercise.studio.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.mainGradient

@Composable
fun MainBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val brush = Brush.mainGradient()
    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.onPrimary,
    ) {
        Canvas(
            modifier = modifier.fillMaxSize(),
            onDraw = { drawRect(brush) }
        )
        content()
    }
}

@Preview
@Composable
private fun PreviewMainBackground() = PreviewBox {
    MainBackground {}
}
