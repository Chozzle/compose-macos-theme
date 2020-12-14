import androidx.compose.ui.graphics.Color

data class MacColors(
    val primary: Color,
    val highlight: Color,
    val border: Color,
    val borderDark: Color
)

val macLightPalette = MacColors(
    primary = Color(0xFF017AFF), // blue
    highlight = Color(0xFF91BBFA), // light blue
    border = Color(0xFFE5E5E5), // light gray
    borderDark = Color(0xFF919191) // light gray
)

