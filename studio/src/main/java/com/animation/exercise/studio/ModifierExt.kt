package com.animation.exercise.studio

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


/**
 * Applies the modifier provided if the predicate is true, uses Modifier.then()
 * Example: Modifier.thenIf(enabled) { clickable(onClick = parentOnClick) }
 */
inline fun Modifier.thenIf(predicate: Boolean, block: Modifier.() -> Modifier) = when (predicate) {
    true -> then(block(this))
    false -> this
}

inline fun Modifier.thenIfPreview(crossinline block: Modifier.() -> Modifier) = composed { thenIf(isPreview(), block) }

/**
 * Applies the modifier provided if the nullable value is not null, uses Modifier.then()
 * Example: Modifier.thenCheckNull("NotNull") { clickable(onClick = handleString(it)) }
 */
fun <T : Any?> Modifier.thenCheckNull(
    nullable: T?,
    block: Modifier.(value: T) -> Modifier
) = nullable?.let { this then block.invoke(Modifier, nullable) } ?: this
