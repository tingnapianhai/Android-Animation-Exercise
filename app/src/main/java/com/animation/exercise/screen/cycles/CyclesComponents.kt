@file:OptIn(ExperimentalMaterial3Api::class)

package com.animation.exercise.screen.cycles

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.PreviewColumn
import com.animation.exercise.studio.component.GradientIcon
import com.animation.exercise.studio.component.PrimaryTitle
import com.animation.exercise.studio.thenIf

@Composable
fun CyclesItemCard(
    title: String,
    description: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = CardDefaults.shape
    Card(
        shape = shape,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .thenIf(selected) { border(width = 5.dp, color = MaterialTheme.colorScheme.primary, shape = shape) }
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1F), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                PrimaryTitle(title = title)
                Text(text = description)
            }
            GradientIcon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(80.dp),
                imageVector = icon,
                contentDescription = null
            )
        }

    }
}

@Preview
@Composable
private fun PreviewCyclesItemCard() = PreviewColumn(showBackground = false) {
    CyclesItemCard(
        title = "Short and Sweet",
        description = "When there are things that needs to be done.",
        selected = false,
        icon = Icons.Default.LocalFlorist
    ) {}

    CyclesItemCard(
        title = "Short and Sweet",
        description = "When there are things that needs to be done.",
        selected = true,
        icon = Icons.Default.LocalFlorist
    ) {}
}