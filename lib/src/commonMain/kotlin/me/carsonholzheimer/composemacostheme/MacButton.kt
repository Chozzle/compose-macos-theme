package me.carsonholzheimer.composemacostheme

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
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MacButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    macButtonStyle: MacButtonStyle = MacButtonStyle.Small,
    interactionState: InteractionState = remember { InteractionState() },
    elevation: ButtonElevation? = ZeroButtonElevation,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        disabledBackgroundColor = MacButtonDisabledColor,
        disabledContentColor = MacButtonDisabledContentColor
    ),
    contentPadding: PaddingValues = PaddingValues(10.dp, 6.dp, 10.dp, 6.dp),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.sizeIn(macButtonStyle.minWidth, macButtonStyle.minHeight),
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MacSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    macButtonStyle: MacButtonStyle = MacButtonStyle.Small,
    interactionState: InteractionState = remember { InteractionState() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(
        defaultElevation = 2.dp,
        pressedElevation = 2.dp,
        disabledElevation = 0.dp
    ),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = MacButtonDefaults.secondaryButtonColors(),
    contentPadding: PaddingValues = PaddingValues(10.dp, 6.dp, 10.dp, 6.dp),
    content: @Composable RowScope.() -> Unit
) {
    MacButton(
        onClick = onClick,
        modifier = modifier.sizeIn(macButtonStyle.minWidth, macButtonStyle.minHeight),
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

sealed class MacButtonStyle(val minWidth: Dp, val minHeight: Dp) {
    object Large : MacButtonStyle(minWidth = 157.dp, minHeight = 28.dp)
    object Small : MacButtonStyle(minWidth = Dp.Unspecified, 20.dp)
}

@OptIn(ExperimentalMaterialApi::class)
object ZeroButtonElevation : ButtonElevation {
    override fun elevation(enabled: Boolean, interactionState: InteractionState) = 0.dp
}

object MacButtonDefaults {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun secondaryButtonColors(
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = MaterialTheme.colors.onSurface,
    ): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = MacButtonDisabledColor,
        contentColor = contentColor,
        disabledContentColor = MacButtonDisabledContentColor
    )
}

// TODO handle dark theme here
private val MacButtonDisabledColor = Color(0x0D000000)
private val MacButtonDisabledContentColor = macLightPalette.primary50
