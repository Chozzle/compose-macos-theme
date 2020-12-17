package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
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
    colors: ButtonColors = ButtonConstants.defaultButtonColors(),
    contentPadding: PaddingValues = PaddingValues(10.dp, 6.dp, 10.dp, 6.dp),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick,
        modifier.sizeIn(macButtonStyle.minWidth, macButtonStyle.minHeight),
        enabled,
        interactionState,
        elevation,
        shape,
        border,
        colors,
        contentPadding,
        content
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

