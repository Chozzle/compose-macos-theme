import androidx.compose.ui.graphics.Color

data class MacColors(
    val primary: Color, // blue
    val highlight: Color, // light blue
    val border: Color // light gray
)

val macLightPalette = MacColors(
    primary = Color(0xFF017AFF), // blue
    highlight = Color(0xFF91BBFA), // light blue
    border = Color(0xFFE5E5E5) // light gray
)

