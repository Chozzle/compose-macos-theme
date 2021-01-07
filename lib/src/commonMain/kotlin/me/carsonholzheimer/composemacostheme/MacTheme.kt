package me.carsonholzheimer.composemacostheme

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

object MacTheme {

    @Composable
    val colors: MacColors
        get() = AmbientMacColors.current
}

private val AmbientMacColors = staticAmbientOf<MacColors> {
    error("No me.carsonholzheimer.composemacostheme.MacColors provided")
}

@Composable
fun MacTheme(
    colors: Colors = lightColors(
        primary = macLightPalette.primary,
    ),
    typography: Typography = Typography(
        defaultFontFamily = MacFonts.SFPro(),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
        )
    ),
    shapes: Shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(11.dp),
        large = RoundedCornerShape(11.dp)
    ),
    content: @Composable () -> Unit
) {
    Providers(AmbientMacColors provides macLightPalette) {
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
 * Use the default debug click indication (gray overlay) since this is what Mac UI does.
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