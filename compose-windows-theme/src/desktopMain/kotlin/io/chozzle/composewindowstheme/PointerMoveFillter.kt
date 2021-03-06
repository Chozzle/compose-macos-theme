package io.chozzle.composewindowstheme

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerMoveFilter

actual fun Modifier.pointerMoveFilter(
    onMove: (position: Offset) -> Boolean,
    onExit: () -> Boolean,
    onEnter: () -> Boolean,
): Modifier = pointerMoveFilter(onMove, onExit, onEnter)
