package io.chozzle.composenativetheme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.chozzle.composemacostheme.MacButton
import io.chozzle.composemacostheme.MacButtonStyle
import io.chozzle.composemacostheme.MacDisabledBackgroundColor
import io.chozzle.composemacostheme.MacDisabledContentColor
import io.chozzle.composewindowstheme.WindowsButton
import io.chozzle.composewindowstheme.WindowsButtonColors
import io.chozzle.composewindowstheme.WindowsButtonDefaults
import io.chozzle.composewindowstheme.WindowsButtonStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    windowsButtonStyle: NativeButtonStyle = NativeButtonStyle.Primary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ZeroButtonElevation,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: NativeButtonColors = NativeButtonDefaults.primaryColors,
    contentPadding: PaddingValues = windowsButtonPaddingValues(windowsButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    when (LocalTheme.current) {
        Mac -> {
            MacButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                macButtonStyle = when (windowsButtonStyle) {
                    NativeButtonStyle.Primary -> MacButtonStyle.Large
                    NativeButtonStyle.Secondary -> MacButtonStyle.Small
                },
                interactionSource = interactionSource,
                elevation = elevation,
                shape = shape,
                border = border,
                colors = colors.toMacColors(),
                contentPadding = contentPadding,
                content = content
            )
        }
        Windows -> WindowsButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            windowsButtonStyle = when (windowsButtonStyle) {
                NativeButtonStyle.Primary -> WindowsButtonStyle.Button
                NativeButtonStyle.Secondary -> TODO()
            },
            interactionSource = interactionSource,
            elevation = elevation,
            shape = shape,
            border = border,
            colors = colors.toWindowsColors(),
            contentPadding = contentPadding,
            content = content
        )
    }
}

object NativeButtonDefaults {
    val primaryColors: NativeButtonColors
        @Composable
        get() = when (LocalTheme.current) {
            Mac -> TODO()
            Windows -> WindowsButtonDefaults.primaryColors
        }
    val accentColors: NativeButtonColors
        @Composable
        get() = NativeButtonColors(
            disabledBackgroundColor = WindowsDisabledBackgroundColor,
            disabledContentColor = WindowsDisabledContentColor,
            backgroundColor = WindowsTheme.colors.accent,
            contentColor = MaterialTheme.colors.onPrimary,
            hoverColor = WindowsTheme.colors.accentLight,
            pressedColor = WindowsTheme.colors.accentDark
        )
}

data class NativeButtonColors(
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

private fun NativeButtonColors.toMacColors() =
    ButtonDefaults.buttonColors(
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )

private fun NativeButtonColors.toWindowsColors() =
    WindowsButtonColors(
        backgroundColor,
        disabledBackgroundColor,
        contentColor,
        disabledContentColor,
        hoverColor,
        pressedColor
    )
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


sealed class NativeButtonStyle {
    object Primary : NativeButtonStyle()
    object Secondary : NativeButtonStyle()
}


@OptIn(ExperimentalMaterialApi::class)
object ZeroButtonElevation : ButtonElevation {

    @Composable
    override fun elevation(enabled: Boolean, interactionSource: InteractionSource): State<Dp> {
        return mutableStateOf(0.dp)
    }
}