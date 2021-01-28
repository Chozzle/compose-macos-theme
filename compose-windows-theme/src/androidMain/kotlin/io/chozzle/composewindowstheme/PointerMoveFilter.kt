package io.chozzle.composewindowstheme

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

/**
 * No-op function that could be implemented once pointer filter is available on androidx compose UI
 * */
actual fun Modifier.pointerMoveFilter(
    onMove: (position: Offset) -> Boolean,
    onExit: () -> Boolean,
    onEnter: () -> Boolean,
): Modifier = this