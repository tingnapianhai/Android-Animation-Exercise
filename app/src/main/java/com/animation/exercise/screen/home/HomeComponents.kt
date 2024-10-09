@file:OptIn(ExperimentalMaterial3Api::class)

package com.animation.exercise.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.component.GradientIcon
import com.animation.exercise.studio.component.PrimaryTitle

@Composable
fun HomeItemCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector?,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.aspectRatio(1F),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PrimaryTitle(modifier = Modifier.fillMaxWidth(), title = title)
            icon?.let {
                GradientIcon(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.End),
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeItemCard() = PreviewBox(showBackground = false) {
    HomeItemCard(title = "Hello", icon = Icons.Default.Image) {}
}
