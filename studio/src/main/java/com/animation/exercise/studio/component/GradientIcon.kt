package com.animation.exercise.studio.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.animation.exercise.studio.PreviewColumn

@Composable
fun GradientIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.tertiaryContainer)
) {
    MaterialTheme.colorScheme.primaryContainer
    val brushGradient = Brush.linearGradient(colors = colors)
    Icon(modifier = modifier
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(brush = brushGradient, blendMode = BlendMode.SrcAtop)
            }
        },
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}

@Preview
@Composable
private fun PreviewGradientIcon() = PreviewColumn {
    GradientIcon(Icons.Default.ImageSearch, null)
    GradientIcon(Icons.Default.Abc, null)
    GradientIcon(Icons.Default.Percent, null)
}
