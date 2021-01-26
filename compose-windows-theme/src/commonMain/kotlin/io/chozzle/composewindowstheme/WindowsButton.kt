package io.chozzle.composewindowstheme

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
        disabledBackgroundColor = MacDisabledBackgroundColor,
        disabledContentColor = MacDisabledContentColor
    ),
    contentPadding: PaddingValues = macButtonPaddingValues(windowsButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    Button(
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

@Composable
private fun macButtonPaddingValues(windowsButtonStyle: WindowsButtonStyle) =
    if (windowsButtonStyle == WindowsButtonStyle.Hyperlink) {
        PaddingValues(10.dp, 2.dp, 10.dp, 2.dp)
    } else {
        PaddingValues(10.dp, 6.dp, 10.dp, 6.dp)
    }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WindowsSecondaryButton(
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
    colors: ButtonColors = MacButtonDefaults.secondaryButtonColors(),
    contentPadding: PaddingValues = macButtonPaddingValues(windowsButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    MacButton(
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

sealed class ButtonStyle(val minWidth: Dp, val minHeight: Dp) {
    object Large : ButtonStyle(minWidth = 157.dp, minHeight = 28.dp)
    object Small : ButtonStyle(minWidth = Dp.Unspecified, 20.dp)
}

object WindowsButtonDefaults {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun secondaryButtonColors(
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = MaterialTheme.colors.onSurface,
    ): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = MacDisabledBackgroundColor,
        contentColor = contentColor,
        disabledContentColor = MacDisabledContentColor
    )
}