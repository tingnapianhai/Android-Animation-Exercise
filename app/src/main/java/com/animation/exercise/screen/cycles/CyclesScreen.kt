@file:OptIn(ExperimentalMaterial3Api::class)

package com.animation.exercise.screen.cycles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllOut
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.SyncLock
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.FullPreview
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.component.LargeTopBar
import com.animation.exercise.studio.component.MainBackground

@Composable
fun CyclesScreen(
    state: CyclesModels.State,
    actions: CyclesModels.Actions
) {
    Scaffold(
        topBar = { LargeTopBar("Breathing Cycles", navAction = LargeTopBar.NavAction.GoBack(actions::goBack)) }
    ) { pv ->
        MainBackground {
            Column(
                modifier = Modifier
                    .padding(pv)
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                AnimatedVisibility(visible = state.showStartSection) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        val maxSteps: Int = 50
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${state.startCyclesCount} SETS",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                ) // Note - this would use quantity strings
                                Text(text = state.cyclesDuration)
                            }

                            Slider(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                        shape = MaterialTheme.shapes.extraLarge
                                    )
                                    .padding(horizontal = 16.dp),
                                value = state.startCyclesCount.toFloat(),
                                onValueChange = { float -> actions.updateSlider(float.toInt()) },
                                valueRange = 1f..maxSteps.toFloat(),
                                steps = maxSteps
                            )

                            ElevatedButton(
                                modifier = Modifier.align(Alignment.End),
                                onClick = actions::startBreathingExercise
                            ) { Text(text = "Start") }
                        }
                    }
                }

                state.cycles.forEach { cycle ->
                    CyclesItemCard(
                        title = cycle.title,
                        description = cycle.description,
                        icon = getIcon(cycle.icon),
                        selected = cycle.selected,
                        onClick = cycle.onClick
                    )
                }
            }
        }
    }
}

@Composable
private fun getIcon(identifier: String) = when (identifier) {
    "flower" -> Icons.Default.LocalFlorist
    "cyclic" -> Icons.Default.SyncLock
    "hyper" -> Icons.Default.AutoGraph
    else -> Icons.Default.ImageNotSupported
}

@FullPreview
@Composable
private fun PreviewCyclesScreen() = PreviewBox(addPadding = false) {
    CyclesScreen(state = CyclesModels.Preview.state, actions = CyclesModels.Preview.actions)
}