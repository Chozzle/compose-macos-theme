import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.platform.font

object MacFonts {
    val fontDirectory = "font"

    @Composable
    fun SFPro() = fontFamily(
        font(
            "SF Pro Regular",
            "${fontDirectory}/SF-Pro-Display-Regular.otf",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        font(
            "SF Pro Regular Italic",
            "${fontDirectory}/SF-Pro-Display-RegularItalic.otf",
            FontWeight.Normal,
            FontStyle.Italic
        ),

        font(
            "SF Pro Bold",
            "${fontDirectory}/SF-Pro-Display-Bold.otf",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        font(
            "SF Pro Bold Italic",
            "${fontDirectory}/SF-Pro-Display-BoldItalic.otf",
            FontWeight.Bold,
            FontStyle.Italic
        ),

        font(
            "SF Pro Extra Bold ",
            "${fontDirectory}/SF-Pro-Display-Heavy.otf",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        font(
            "SF Pro Extra Bold Italic",
            "${fontDirectory}/SF-Pro-Display-HeavyItalic.otf",
            FontWeight.ExtraBold,
            FontStyle.Italic
        ),

        font(
            "SF Pro Medium",
            "${fontDirectory}/SF-Pro-Display-Medium.otf",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        font(
            "SF Pro Medium Italic",
            "${fontDirectory}/SF-Pro-Display-MediumItalic.otf",
            FontWeight.Medium,
            FontStyle.Italic
        )
    )
}