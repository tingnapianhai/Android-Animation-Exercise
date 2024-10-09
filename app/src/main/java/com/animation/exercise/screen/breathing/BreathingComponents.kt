@file:OptIn(ExperimentalLayoutApi::class)

package com.animation.exercise.screen.breathing

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.PreviewColumn
import kotlin.time.Duration.Companion.seconds

private const val MAX_STATE = 250
private const val MIN_STATE = 80

@Composable
fun BreathingVisualiser(
    breathingStep: BreathingModels.Step?,
    stepTimeLeft: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    var lastState: Int by remember { mutableIntStateOf(MIN_STATE) }

    val targetState = when (breathingStep) {
        is BreathingModels.Step.BreathIn -> MAX_STATE
        is BreathingModels.Step.Hold -> lastState
        is BreathingModels.Step.BreathOut,
        null -> MIN_STATE
    }

    lastState = targetState
    val intSize by animateIntAsState(
        targetValue = targetState,
        label = "animation",
        animationSpec = tween(breathingStep?.duration?.inWholeMilliseconds?.toInt() ?: 400)
    )

    Box(
        modifier = modifier
            .size(MAX_STATE.dp)
            .border(3.dp, color = color, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(intSize.dp)
                .background(
                    color.copy(alpha = 0.4F),
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(MIN_STATE.dp)
                .background(color = color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepTimeLeft,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.contentColorFor(color)
            )
        }
    }
}

@Composable
fun CyclesCounter(cyclesLeft: Int, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(cyclesLeft) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(color = LocalContentColor.current, shape = CircleShape)
            )
        }
    }
}

@Composable
fun TapAndSweepOverlay(
    onTap: () -> Unit,
    onSweep: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(onClick = onTap, indication = null, interactionSource = MutableInteractionSource())
        .pointerInput(Unit) {
            detectVerticalDragGestures { _, dragAmount -> if (dragAmount > 20F) onSweep() }
        }
    )
}

@Preview
@Composable
private fun PreviewBreathingVisualiser() = PreviewColumn(showBackground = false) {
    BreathingVisualiser(breathingStep = BreathingModels.Step.BreathIn(3.seconds), "1s")
    BreathingVisualiser(breathingStep = BreathingModels.Step.BreathOut(4.seconds), "4s")
}
