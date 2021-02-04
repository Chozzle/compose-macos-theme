package io.chozzle.composewindowstheme

import androidx.compose.animation.animateAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.indication
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
    colors: WindowsButtonColors = WindowsButtonDefaults.primaryColors,
    contentPadding: PaddingValues = windowsButtonPaddingValues(windowsButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    var isPointerHovering by remember { mutableStateOf(false) }
    val backgroundColor by animateAsState(
        targetValue = if (isPointerHovering) {
            colors.hoverColor
        } else {
            colors.backgroundColor
        },
        animationSpec = TweenSpec(
            durationMillis = 250,
            easing = LinearEasing
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .pointerMoveFilter(
                onEnter = {
                    isPointerHovering = true
                    false
                },
                onExit = {
                    isPointerHovering = false
                    false
                }
            ).sizeIn(windowsButtonStyle.minWidth, windowsButtonStyle.minHeight)

            // Material indication interferes with animated hover color change
            .indication(interactionState, indication = null),
        enabled = enabled,
        interactionState = interactionState,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = colors.disabledBackgroundColor,
            disabledContentColor = colors.disabledContentColor,
            backgroundColor = if (interactionState.contains(Interaction.Pressed)) {
                colors.pressedColor
            } else {
                backgroundColor
            },
            contentColor = colors.contentColor
        ),
        contentPadding = contentPadding,
        content = content
    )
}

object WindowsButtonDefaults {
    val primaryColors: WindowsButtonColors
        @Composable
        get() = WindowsButtonColors(
            disabledBackgroundColor = WindowsDisabledBackgroundColor,
            disabledContentColor = WindowsDisabledContentColor,
            backgroundColor = WindowsTheme.colors.baseLow,
            contentColor = MaterialTheme.colors.onBackground,
            hoverColor = WindowsTheme.colors.listLow,
            pressedColor = WindowsTheme.colors.baseMediumLow
        )
    val accentColors: WindowsButtonColors
        @Composable
        get() = WindowsButtonColors(
            disabledBackgroundColor = WindowsDisabledBackgroundColor,
            disabledContentColor = WindowsDisabledContentColor,
            backgroundColor = WindowsTheme.colors.accent,
            contentColor = MaterialTheme.colors.onPrimary,
            hoverColor = WindowsTheme.colors.accentLight,
            pressedColor = WindowsTheme.colors.accentDark
        )
}

data class WindowsButtonColors(
    val backgroundColor: Color,
    val disabledBackgroundColor: Color,
    val contentColor: Color,
    val disabledContentColor: Color,
    val hoverColor: Color,
    val pressedColor: Color
)

@Composable
private fun windowsButtonPaddingValues(windowsButtonStyle: WindowsButtonStyle) =
    if (windowsButtonStyle == WindowsButtonStyle.Hyperlink) {
        PaddingValues(10.dp, 2.dp, 10.dp, 2.dp)
    } else {
        PaddingValues(10.dp, 6.dp, 10.dp, 6.dp)
    }


/*@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WindowsHyperlinkButton( // TODO
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    windowsButtonStyle: WindowsButtonStyle = WindowsButtonStyle.Hyperlink,
    interactionState: InteractionState = remember { InteractionState() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(
        defaultElevation = 2.dp,
        pressedElevation = 2.dp,
        disabledElevation = 0.dp
    ),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = WindowsButtonDefaults.secondaryButtonColors(),
    contentPadding: PaddingValues = windowsButtonPaddingValues(windowsButtonStyle),
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
}*/

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