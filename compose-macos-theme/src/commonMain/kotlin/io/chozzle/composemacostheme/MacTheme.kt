package io.chozzle.composemacostheme

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object MacTheme {

    val colors: MacColors
        @Composable
        get() = AmbientMacColors.current

    val defaultColors
        @Composable
        get() = lightColors(
            primary = macLightPalette.primary,
        )

    val defaultTypography
        @Composable
        get() = Typography(
            defaultFontFamily = MacFonts.SFPro(),
            button = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
            )
        )

    val defaultShapes
        @Composable
        get() = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(11.dp),
            large = RoundedCornerShape(11.dp)
        )
}

private val AmbientMacColors = staticCompositionLocalOf<MacColors> {
    error("No MacColors provided")
}


/**
 * Wraps [MaterialTheme] with modifications to match MacOS Theme
 * */
@Composable
fun MacTheme(
    colors: Colors = MacTheme.defaultColors,
    typography: Typography = MacTheme.defaultTypography,
    shapes: Shapes = MacTheme.defaultShapes,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(AmbientMacColors provides macLightPalette) {
        MaterialTheme(colors, typography, shapes) {
            val indication = remember {
                MacIndication
            }
            CompositionLocalProvider(
                LocalIndication provides indication,
                content = content
            )
        }
    }
}

/**
 * Uses the default debug click indication (gray overlay) since this is exactly what Mac Theme does.
 * */
private object MacIndication : Indication {

    private class DefaultDebugIndicationInstance(
        private val isPressed: State<Boolean>
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
            if (isPressed.value) {
                drawRect(color = Color.Black.copy(alpha = 0.07f), size = size)
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        return remember(interactionSource) {
            DefaultDebugIndicationInstance(isPressed)
        }
    }
}