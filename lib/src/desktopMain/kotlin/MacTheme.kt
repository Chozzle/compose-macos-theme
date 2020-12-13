import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ContentDrawScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.*

object MacTheme {

    @Composable
    val colors: MacColors
        get() = AmbientMacColors.current
}

private val AmbientMacColors = staticAmbientOf<MacColors> {
    error("No MacColors provided")
}

@Composable
fun MacTheme(
    colors: Colors = lightColors(
        primary = macLightPalette.primary,
    ),
    typography: Typography = Typography(defaultFontFamily = MacFonts.SFPro()),
    shapes: Shapes = Shapes(
        small = RoundedCornerShape(6.dp),
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


private object MacIndication : Indication {

    private object DefaultDebugIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication(interactionState: InteractionState) {
            drawContent()
            if (interactionState.contains(Interaction.Pressed)) {
                drawRect(color = Color.Black.copy(alpha = 0.1f), size = size)
            }
        }
    }

    override fun createInstance(): IndicationInstance {
        return DefaultDebugIndicationInstance
    }
}