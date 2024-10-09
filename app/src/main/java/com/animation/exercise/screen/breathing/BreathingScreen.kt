package com.animation.exercise.screen.breathing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.FullPreview
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.component.LargeTopBar
import com.animation.exercise.studio.component.MainBackground
import com.animation.exercise.studio.component.StudioDialog

@Composable
fun BreathingScreen(
    state: BreathingModels.State,
    actions: BreathingModels.Actions
) {
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(state.breathingStep) {
        if (state.breathingStep != null) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            // Note:
            //  - some custom vibration based on the step type.
            //    BUT, that's not an out of the box thing on the LocalHapticFeedback
            //    So, let's skip setting up loops and delays for making good, good, good, good vibrations.
        }
    }
    Scaffold(
        topBar = {
            LargeTopBar(
                title = state.title,
                navAction = LargeTopBar.NavAction.Close(actions::cancel)
            )
        }
    ) { pv ->
        MainBackground {
            Column(
                modifier = Modifier
                    .padding(pv)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    textAlign = TextAlign.Center,
                    text = when (state.breathingStep == null) {
                        true -> "Tap anywhere to start"
                        false -> "Swipe down to pause and reset"
                    },
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.size(20.dp))

                AnimatedVisibility(visible = state.breathingStep != null) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.sizeIn(minWidth = 80.dp),
                                    text = state.timeSpent,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                CyclesCounter(
                                    modifier = Modifier.fillMaxWidth(),
                                    cyclesLeft = state.cyclesLeft
                                )
                            }
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = state.breathingStep?.getLabel() ?: "",
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
                                textAlign = TextAlign.Center
                            )
                            Text(text = state.instructions)
                        }
                    }
                }

                BreathingVisualiser(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 24.dp),
                    breathingStep = state.breathingStep,
                    stepTimeLeft = state.stepTimeLeft ?: ""
                )
            }
            TapAndSweepOverlay(
                onTap = actions::start,
                onSweep = actions::reset
            )
        }
        if (state.showSuccess) {
            StudioDialog(onDismissRequest = actions::confirmSuccess) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(text = "Success", style = MaterialTheme.typography.titleMedium)
                    Icon(
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.CenterHorizontally),
                        imageVector = Icons.Default.CheckCircleOutline,
                        contentDescription = null,
                        tint = Color(0xFF49B346)
                    )
                    Text(
                        text = "Good Job! Don't forget to take a really deep breath once in a while",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@FullPreview
@Composable
private fun PreviewBreathingScreen() = PreviewBox {
    BreathingScreen(state = BreathingModels.Preview.state, actions = BreathingModels.Preview.actions)
}