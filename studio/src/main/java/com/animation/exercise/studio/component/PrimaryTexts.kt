package com.animation.exercise.studio.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.animation.exercise.studio.PreviewColumn

@Composable
fun PrimaryHeadline(headline: String, modifier: Modifier = Modifier) {
    PrimaryText(
        modifier = modifier,
        text = headline,
        style = MaterialTheme.typography.headlineLarge,
    )
}

@Composable
fun PrimaryTitle(title: String, modifier: Modifier = Modifier) {
    PrimaryText(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun PrimaryText(text: String, style: TextStyle, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = text,
        style = style.copy(fontWeight = FontWeight.ExtraBold),
        color = MaterialTheme.colorScheme.primary,
    )
}

@Preview
@Composable
private fun PreviewPrimaryTexts() = PreviewColumn {
    PrimaryHeadline("The Roman Empire")
    PrimaryTitle("Iulius Caesar")
}