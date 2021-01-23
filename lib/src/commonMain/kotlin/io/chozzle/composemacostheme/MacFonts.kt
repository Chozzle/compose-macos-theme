package io.chozzle.composemacostheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.fontFamily

object MacFonts {

    @Composable
    fun SFPro() = fontFamily(
        font(
            "SF Pro Regular",
            "sf_pro_display_regular",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        font(
            "SF Pro Regular Italic",
            "sf_pro_display_regularitalic",
            FontWeight.Normal,
            FontStyle.Italic
        ),
        font(
            "SF Pro Medium",
            "sf_pro_display_medium",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        font(
            "SF Pro Medium Italic",
            "sf_pro_display_mediumitalic",
            FontWeight.Medium,
            FontStyle.Italic
        ),
        font(
            "SF Pro SemiBold",
            "sf_pro_display_semibold",
            FontWeight.SemiBold,
            FontStyle.Normal
        ),
        font(
            "SF Pro SemiBold",
            "sf_pro_display_semibolditalic",
            FontWeight.SemiBold,
            FontStyle.Italic
        ),
        font(
            "SF Pro Bold",
            "sf_pro_display_bold",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        font(
            "SF Pro Bold Italic",
            "sf_pro_display_bolditalic",
            FontWeight.Bold,
            FontStyle.Italic
        ),

        font(
            "SF Pro Extra Bold ",
            "sf_pro_display_heavy",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        font(
            "SF Pro Extra Bold Italic",
            "sf_pro_display_heavyitalic",
            FontWeight.ExtraBold,
            FontStyle.Italic
        )
    )
}

@Composable
expect fun font(alias: String, path: String, weight: FontWeight, style: FontStyle): Font