package io.chozzle.composenativetheme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.chozzle.composemacostheme.MacButton
import io.chozzle.composemacostheme.MacButtonDefaults
import io.chozzle.composemacostheme.MacButtonStyle
import io.chozzle.composemacostheme.macButtonPaddingValues
import io.chozzle.composewindowstheme.WindowsButton
import io.chozzle.composewindowstheme.WindowsButtonColors
import io.chozzle.composewindowstheme.WindowsButtonStyle
import io.chozzle.composewindowstheme.windowsButtonPaddingValues

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    nativeButtonStyle: NativeButtonStyle = NativeButtonStyle.Primary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = null,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: NativeButtonColors = NativeButtonDefaults.primaryColors,
    contentPadding: NativePaddingValues = NativePaddingValues.ThemeDefault,
    content: @Composable RowScope.() -> Unit
) {
    when (LocalTheme.current) {
        Mac -> {
            MacButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                macButtonStyle = when (nativeButtonStyle) {
                    NativeButtonStyle.Primary -> MacButtonStyle.Large
                    NativeButtonStyle.Secondary -> MacButtonStyle.Small
                },
                interactionSource = interactionSource,
                elevation = elevation ?: when (nativeButtonStyle) {
                    NativeButtonStyle.Primary -> MacButtonDefaults.secondaryButtonElevation()
                    NativeButtonStyle.Secondary -> MacButtonDefaults.secondaryButtonElevation()
                },
                shape = shape,
                border = border,
                colors = colors.toMacColors(nativeButtonStyle),
                contentPadding = when (contentPadding) {
                    NativePaddingValues.ThemeDefault -> {
                        when (nativeButtonStyle) {
                            NativeButtonStyle.Primary -> macButtonPaddingValues(macButtonStyle = MacButtonStyle.Large)
                            NativeButtonStyle.Secondary -> macButtonPaddingValues(macButtonStyle = MacButtonStyle.Small)
                        }
                    }
                    is NativePaddingValues.Values -> contentPadding.values
                    else -> error("Not supported")
                },
                content = content
            )
        }
        Windows -> WindowsButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            windowsButtonStyle = when (nativeButtonStyle) {
                NativeButtonStyle.Primary -> WindowsButtonStyle.Button
                NativeButtonStyle.Secondary -> WindowsButtonStyle.Button // TODO decide how to merge button design
            },
            interactionSource = interactionSource,
            elevation = elevation,
            shape = shape,
            border = border,
            colors = colors.toWindowsColors(),
            contentPadding = when (contentPadding) {
                NativePaddingValues.ThemeDefault -> {
                    when (nativeButtonStyle) {
                        NativeButtonStyle.Primary -> {
                            windowsButtonPaddingValues(windowsButtonStyle = WindowsButtonStyle.Button)
                        }
                        NativeButtonStyle.Secondary -> {
                            windowsButtonPaddingValues(windowsButtonStyle = WindowsButtonStyle.Hyperlink)
                        }
                    }
                }
                is NativePaddingValues.Values -> contentPadding.values
                else -> error("Not supported")
            },
            content = content
        )
    }
}

interface NativePaddingValues {
    object ThemeDefault : NativePaddingValues
    data class Values(val values: PaddingValues) : NativePaddingValues, PaddingValues by values
}

object NativeButtonDefaults {
    val primaryColors: NativeButtonColors
        @Composable
        get() = NativeButtonColors(
            disabledBackgroundColor = NativeDisabledBackgroundColor,
            disabledContentColor = NativeDisabledContentColor,
            backgroundColor = NativeTheme.colors.baseLow,
            contentColor = MaterialTheme.colors.onBackground,
            hoverColor = NativeTheme.colors.listLow,
            pressedColor = NativeTheme.colors.baseMediumLow
        )
    val accentColors: NativeButtonColors
        @Composable
        get() = NativeButtonColors(
            disabledBackgroundColor = NativeDisabledBackgroundColor,
            disabledContentColor = NativeDisabledContentColor,
            backgroundColor = NativeTheme.colors.accent,
            contentColor = MaterialTheme.colors.onPrimary,
            hoverColor = NativeTheme.colors.accentLight,
            pressedColor = NativeTheme.colors.accentDark
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
private fun NativeButtonColors.toMacColors(nativeButtonStyle: NativeButtonStyle) =
    when (nativeButtonStyle) {
        NativeButtonStyle.Primary -> ButtonDefaults.buttonColors(
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor
        )
        NativeButtonStyle.Secondary -> MacButtonDefaults.secondaryButtonColors()
    }

@Composable
private fun NativeButtonColors.toWindowsColors() =
    WindowsButtonColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        hoverColor = hoverColor,
        pressedColor = pressedColor
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

//internal expect fun Modifier.pointerMoveFilter(
//    onMove: (position: Offset) -> Boolean = { false },
//    onExit: () -> Boolean = { false },
//    onEnter: () -> Boolean = { false },
//): Modifier


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