package com.animation.exercise.studio.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.isPreview


@Composable
fun StudioDialog(
    onDismissRequest: () -> Unit,
    horizontalPadding: Dp = 16.dp,
    contentPadding: PaddingValues = PaddingValues(
        vertical = 16.dp,
        horizontal = 24.dp
    ),
    shape: Shape = MaterialTheme.shapes.large,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable BoxScope.() -> Unit
) {
    when (isPreview()) {
        false -> {
            Dialog(
                onDismissRequest = onDismissRequest,
                properties = properties
            ) {
                DialogBox(horizontalPadding, contentPadding, shape, containerColor, content)
            }
        }
        true -> {
            Box(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(vertical = 24.dp)
            ) {
                DialogBox(horizontalPadding, contentPadding, shape, containerColor, content)
            }
        }
    }
}

@Composable
private fun DialogBox(
    horizontalPadding: Dp,
    contentPadding: PaddingValues,
    shape: Shape,
    containerColor: Color,
    content: @Composable (BoxScope.() -> Unit)
) = Box(
    modifier = Modifier
        .padding(horizontal = horizontalPadding)
        .defaultMinSize(minHeight = 100.dp)
        .clip(shape)
        .background(color = containerColor)
        .padding(paddingValues = contentPadding),
    content = content
)

@Composable
@Preview
private fun Preview() = PreviewBox(addPadding = false, showBackground = false) {
    StudioDialog(onDismissRequest = { }) {
        Text(text = "A Time to Kill", color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
@Preview
private fun PreviewFullWidth() = PreviewBox(addPadding = false, showBackground = false) {
    StudioDialog(onDismissRequest = { }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Petentium mediocritatem aliquet tristique " +
                    "diam delicata sumo curabitur torquent maiorum neglegentur doming prompta proin aliquid " +
                    "eum interpretaris facilisis elit ea nunc propriae postulant qualisque tamquam repudiare " +
                    "iaculis expetenda usu recteque sit graece inan.",
            color = MaterialTheme.colorScheme.primary
        )
    }
}
