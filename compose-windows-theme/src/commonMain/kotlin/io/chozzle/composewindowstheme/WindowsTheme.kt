package io.chozzle.composewindowstheme

import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object WindowsTheme {

    @Composable
    val colors: WindowsColors
        get() = AmbientMacColors.current
}

private val AmbientMacColors = staticAmbientOf<WindowsColors> {
    error("No MacColors provided")
}
/**
 * Wraps [MaterialTheme] with modifications to match MacOS Theme
 * */
@Composable
fun WindowsTheme(
    colors: Colors = lightColors(
        primary = windowsLightPalette.primary,
    ),
    typography: Typography = Typography(
        defaultFontFamily = WindowsFonts.SegoeUI(),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        )
    ),
    shapes: Shapes = Shapes(
        small = RoundedCornerShape(2.dp),
        medium = RoundedCornerShape(11.dp),
        large = RoundedCornerShape(11.dp)
    ),
    content: @Composable () -> Unit
) {
    Providers(AmbientMacColors provides windowsLightPalette) {
        MaterialTheme(colors, typography, shapes) {
            val indicationFactory: @Composable () -> Indication = {
                MacIndication
            }
            Providers(
                AmbientIndication provides indicationFactory,
                content = content
            )
        }
    }
}

/**
 * Uses the default debug click indication (gray overlay) since this is exactly what Mac Theme does.
 * */
private object MacIndication : Indication {

    private object DefaultDebugIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication(interactionState: InteractionState) {
            drawContent()
            if (interactionState.contains(Interaction.Pressed)) {
                drawRect(color = Color.Black.copy(alpha = 0.07f), size = size)
            }
        }
    }

    override fun createInstance(): IndicationInstance {
        return DefaultDebugIndicationInstance
    }
}