package com.animation.exercise.studio

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.theme.StudioTheme

@Preview(group = "Default", name = "Default")
@Preview(group = "Default", name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(group = "Locale", name = "Finnish", locale = "fi-rFI")
@Preview(group = "Locale", name = "Swedish", locale = "sv-rSE")
@Preview(group = "Wonky", name = "Crap Device", device = "id:Nexus One")
@Preview(group = "Wonky", name = "Very Large Fonts", fontScale = 2F)
@Preview(group = "Wonky", name = "Large Fonts", fontScale = 1.5F)
@Preview(group = "Wonky", name = "Small Font", fontScale = 0.5F)
annotation class FullPreview

@Preview(group = "Default", name = "Default")
@Preview(group = "Default", name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(group = "Wonky", name = "Very Large Fonts", fontScale = 2F)
@Preview(group = "Wonky", name = "Small Font", fontScale = 0.5F)
annotation class ComponentPreview

@Preview(group = "Default", name = "Default")
@Preview(group = "Default", name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class BasicPreview

@Composable
fun isPreview(): Boolean = LocalInspectionMode.current

private val contentPadding = Modifier.padding(16.dp)
private val contentSpacing = Arrangement.spacedBy(8.dp)
private val Modifier.appBackground: Modifier
    get() = composed { then(background(MaterialTheme.colorScheme.background)) }

@Composable
fun PreviewBox(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true,
    addPadding: Boolean = true,
    previewContent: @Composable BoxScope.() -> Unit
) {
    CheckPreview()
    StudioTheme {
        Box(
            modifier = modifier
                .thenIf(showBackground) { appBackground }
                .thenIf(addPadding) { contentPadding },
            content = previewContent
        )
    }
}

@Composable
fun PreviewRow(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true,
    addPadding: Boolean = true,
    addSpacing: Boolean = true,
    previewContent: @Composable RowScope.() -> Unit
) {
    CheckPreview()
    StudioTheme {
        Row(
            modifier = modifier
                .thenIf(showBackground) { appBackground }
                .thenIf(addPadding) { contentPadding },
            horizontalArrangement = if (addSpacing) contentSpacing else Arrangement.Start,
            content = previewContent
        )
    }
}

@Composable
fun PreviewColumn(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true,
    addPadding: Boolean = true,
    addSpacing: Boolean = true,
    previewContent: @Composable ColumnScope.() -> Unit
) {
    CheckPreview()
    StudioTheme {
        Column(
            modifier = modifier
                .thenIf(showBackground) { appBackground }
                .thenIf(addPadding) { contentPadding },
            verticalArrangement = if (addSpacing) contentSpacing else Arrangement.Top,
            content = previewContent
        )
    }
}

@Composable
private fun CheckPreview() = if (!isPreview()) error("Illegal component - only use in Preview") else Unit
