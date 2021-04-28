package io.chozzle.composewindowstheme

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object WindowsTheme {

    val colors: WindowsColors
        @Composable

        get() = LocalWindowsColors.current


    val defaultColors
        @Composable
        get() = lightColors(
            primary = windowsLightPalette.baseLow,
        )

    val defaultTypography
        @Composable
        get() = Typography(
            defaultFontFamily = WindowsFonts.SegoeUI(),
            button = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        )

    val defaultShapes
        @Composable
        get() = Shapes(
            small = RoundedCornerShape(2.dp),
            medium = RoundedCornerShape(11.dp),
            large = RoundedCornerShape(11.dp)
        )
}

private val LocalWindowsColors = staticCompositionLocalOf<WindowsColors> {
    error("No WindowsColors provided")
}

private val LocalIconFont = staticCompositionLocalOf<Font> {
    error("No Font provided")
}

/**
 * Wraps [MaterialTheme] with modifications to match MacOS Theme
 * */
@Composable
fun WindowsTheme(
    colors: Colors = WindowsTheme.defaultColors,
    typography: Typography = WindowsTheme.defaultTypography,
    shapes: Shapes = WindowsTheme.defaultShapes,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalWindowsColors provides windowsLightPalette,
        LocalIconFont provides WindowsFonts.SegoeAssets(),
    ) {
        MaterialTheme(colors, typography, shapes) {
            CompositionLocalProvider(
                LocalIndication provides NoIndication,
                content = content
            )
        }
    }
}

/**
 * Copied from compose source
 * */
private object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}