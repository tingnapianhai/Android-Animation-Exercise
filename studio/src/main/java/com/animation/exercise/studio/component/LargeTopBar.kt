package com.animation.exercise.studio.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animation.exercise.studio.ComponentPreview
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.PreviewColumn
import com.animation.exercise.studio.thenIfPreview

object LargeTopBar {

    @Composable
    operator fun invoke(
        title: String?,
        modifier: Modifier = Modifier,
        navAction: NavAction = NavAction.None,
        actions: @Composable (RowScope.() -> Unit)? = null
    ) {
        Surface(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge.copy(
                topStart = ZeroCornerSize,
                topEnd = ZeroCornerSize
            ),
        ) {
            Column {
                Box(modifier = Modifier
                    .statusBarsPadding()
                    .thenIfPreview {
                        border(1.dp, Color.Cyan)
                            .background(Color.LightGray)
                            .height(28.dp)
                            .fillMaxWidth()
                    }
                )
                val hasActionBar = navAction != NavAction.None || actions != null
                if (hasActionBar) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = navAction.action) {
                            when (navAction) {
                                is NavAction.Close -> Icons.Default.Close
                                is NavAction.GoBack -> Icons.Default.ArrowBack
                                NavAction.None -> null
                            }?.let { imageVector ->
                                // Skipping accessibility, would need localised handling passed down from :app
                                Icon(imageVector = imageVector, contentDescription = null)
                            }
                        }
                        if (actions != null) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                                content = actions
                            )
                        }
                    }
                }
                if (title != null) {
                    Spacer(modifier = Modifier.size(if (!hasActionBar) 24.dp else 8.dp))
                    PrimaryHeadline(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .padding(bottom = 20.dp), headline = title
                    )
                } else Spacer(modifier = Modifier.size(8.dp))
            }

        }
    }

    sealed interface NavAction {
        val action: () -> Unit

        data object None : NavAction {
            override val action: () -> Unit get() = { }
        }

        data class GoBack(override val action: () -> Unit) : NavAction
        data class Close(override val action: () -> Unit) : NavAction
    }
}

@ComponentPreview
@Composable
private fun PreviewLargeTopBar() = PreviewColumn(addPadding = false, showBackground = false) {
    LargeTopBar(title = "Hamburgers")
    LargeTopBar(title = null, navAction = LargeTopBar.NavAction.Close {})
    LargeTopBar(title = "And some mustard", navAction = LargeTopBar.NavAction.GoBack {})
    LargeTopBar(title = "And some mustard", navAction = LargeTopBar.NavAction.GoBack {}) {
        IconButton(onClick = {}) { Icon(imageVector = Icons.Default.Phone, contentDescription = null) }
        IconButton(onClick = {}) { Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null) }
    }
}

@Preview
@Composable
private fun PreviewWithBackground() = PreviewBox(addPadding = false) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box { LargeTopBar(title = "The Dance Place") }
    }
}
