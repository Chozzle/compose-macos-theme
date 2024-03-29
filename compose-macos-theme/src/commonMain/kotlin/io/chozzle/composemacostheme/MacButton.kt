package io.chozzle.composemacostheme

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun MacButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    macButtonStyle: MacButtonStyle = MacButtonStyle.Small,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ZeroButtonElevation,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        disabledBackgroundColor = MacDisabledBackgroundColor,
        disabledContentColor = MacDisabledContentColor
    ),
    contentPadding: PaddingValues = macButtonPaddingValues(macButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    io.chozzle.composemacostheme.modifiedofficial.Button(
        onClick = onClick,
        modifier = modifier.sizeIn(macButtonStyle.minWidth, macButtonStyle.minHeight),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun macButtonPaddingValues(macButtonStyle: MacButtonStyle) =
    if (macButtonStyle == MacButtonStyle.Small) {
        PaddingValues(10.dp, 2.dp, 10.dp, 2.dp)
    } else {
        PaddingValues(10.dp, 6.dp, 10.dp, 6.dp)
    }


@ExperimentalMaterialApi
@Composable
fun MacSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // TODO: Make an "indication" that changes to primary color on click
    enabled: Boolean = true,
    macButtonStyle: MacButtonStyle = MacButtonStyle.Small,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = MacButtonDefaults.secondaryButtonElevation() ,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = MacButtonDefaults.secondaryButtonColors(),
    contentPadding: PaddingValues = macButtonPaddingValues(macButtonStyle),
    content: @Composable RowScope.() -> Unit
) {
    MacButton(
        onClick = onClick,
        modifier = modifier.sizeIn(macButtonStyle.minWidth, macButtonStyle.minHeight),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

sealed class MacButtonStyle(val minWidth: Dp, val minHeight: Dp) {
    object Large : MacButtonStyle(minWidth = 157.dp, minHeight = 28.dp)
    object Small : MacButtonStyle(minWidth = Dp.Unspecified, 20.dp)
}

@ExperimentalMaterialApi
object ZeroButtonElevation : ButtonElevation {

    @Composable
    override fun elevation(enabled: Boolean, interactionSource: InteractionSource): State<Dp> {
        return mutableStateOf(0.dp)
    }
}

object MacButtonDefaults {

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

    @Composable
    fun secondaryButtonElevation() = ButtonDefaults.elevation(
        defaultElevation = 2.dp, // TODO: Try to adjust shadow to be more similar to Mac
        pressedElevation = 2.dp,
        disabledElevation = 0.dp
    )
}