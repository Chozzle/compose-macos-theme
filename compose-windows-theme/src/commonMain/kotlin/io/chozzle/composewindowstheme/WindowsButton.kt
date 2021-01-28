package io.chozzle.composewindowstheme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WindowsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    windowsButtonStyle: WindowsButtonStyle = WindowsButtonStyle.Button,
    interactionState: InteractionState = remember { InteractionState() },
    elevation: ButtonElevation? = ZeroButtonElevation,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        disabledBackgroundColor = WindowsDisabledBackgroundColor,
        disabledContentColor = WindowsDisabledContentColor
    ),
    contentPadding: PaddingValues = macButtonPaddingValues(windowsButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    var isPointerHovering by remember { mutableStateOf(false) }
    Button(
        onClick = onClick,
        modifier = Modifier
            .pointerMoveFilter(
                onEnter = {
                    isPointerHovering = true
                    false
                },
                onExit = {
                    isPointerHovering = false
                    false
                }
            ),
        enabled = enabled,
        interactionState = interactionState,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = if (isPointerHovering) {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = colors.backgroundColor(false),
                disabledContentColor = colors.contentColor(false),
                backgroundColor = WindowsTheme.colors.highlight,
                contentColor = MaterialTheme.colors.surface
            )
        } else {
            colors
        },
        contentPadding = contentPadding,
        content = content
    )
}


@Composable
private fun macButtonPaddingValues(windowsButtonStyle: WindowsButtonStyle) =
    if (windowsButtonStyle == WindowsButtonStyle.Hyperlink) {
        PaddingValues(10.dp, 2.dp, 10.dp, 2.dp)
    } else {
        PaddingValues(10.dp, 6.dp, 10.dp, 6.dp)
    }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WindowsHyperlinkButton( // TODO
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // TODO: Make an "indication" that changes to primary color on click
    enabled: Boolean = true,
    windowsButtonStyle: WindowsButtonStyle = WindowsButtonStyle.Hyperlink,
    interactionState: InteractionState = remember { InteractionState() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(
        defaultElevation = 2.dp, // TODO: Try to adjust shadow to be more similar to Mac
        pressedElevation = 2.dp,
        disabledElevation = 0.dp
    ),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = WindowsButtonDefaults.secondaryButtonColors(),
    contentPadding: PaddingValues = macButtonPaddingValues(windowsButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    WindowsButton(
        onClick = onClick,
        modifier = modifier.sizeIn(windowsButtonStyle.minWidth, windowsButtonStyle.minHeight),
        enabled = enabled,
        interactionState = interactionState,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

internal expect fun Modifier.pointerMoveFilter(
    onMove: (position: Offset) -> Boolean = { false },
    onExit: () -> Boolean = { false },
    onEnter: () -> Boolean = { false },
): Modifier


sealed class WindowsButtonStyle(val minWidth: Dp, val minHeight: Dp) {
    object Button : WindowsButtonStyle(minWidth = 120.dp, minHeight = 32.dp)
    object Hyperlink : WindowsButtonStyle(minWidth = Dp.Unspecified, 32.dp)
}

@OptIn(ExperimentalMaterialApi::class)
object ZeroButtonElevation : ButtonElevation {
    override fun elevation(enabled: Boolean, interactionState: InteractionState) = 0.dp
}

object WindowsButtonDefaults {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun secondaryButtonColors(
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = MaterialTheme.colors.onSurface,
    ): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = WindowsDisabledBackgroundColor,
        contentColor = contentColor,
        disabledContentColor = WindowsDisabledContentColor
    )
}