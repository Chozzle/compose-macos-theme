package io.chozzle.composenativetheme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class NativeTheme {

    val theme: NativeTheme
        @Composable
        get() = LocalTheme.current

    val colors: NativeColors
        @Composable
        get() = LocalNativeColors.current
}

object Mac: NativeTheme()
object Windows: NativeTheme()

private val LocalNativeColors = staticCompositionLocalOf<NativeColors> {
    error("No NativeColors provided")
}

val LocalTheme = staticCompositionLocalOf<NativeTheme> {
    error("No Theme provided")
}

@Composable
fun NativeTheme(
    colors: Colors = lightColors(
        primary = nativeLightPalette.baseLow,
    ),
    typography: Typography = Typography(
        //defaultFontFamily = nativeFonts.SegoeUI(),
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
    CompositionLocalProvider(
        LocalNativeColors provides nativeLightPalette,
    ) {
        MaterialTheme(colors, typography, shapes, content)
    }
}