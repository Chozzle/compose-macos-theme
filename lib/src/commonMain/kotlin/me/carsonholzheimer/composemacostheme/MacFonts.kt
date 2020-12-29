package me.carsonholzheimer.composemacostheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.fontFamily

object MacFonts {
    val fontDirectory = "font"

    @Composable
    fun SFPro() = fontFamily(
        font(
            "SF Pro Regular",
            "$fontDirectory/sf_pro_display_regular.otf",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        font(
            "SF Pro Regular Italic",
            "$fontDirectory/sf_pro_display_regularitalic.otf",
            FontWeight.Normal,
            FontStyle.Italic
        ),

        font(
            "SF Pro Bold",
            "$fontDirectory/sf_pro_display_bold.otf",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        font(
            "SF Pro Bold Italic",
            "$fontDirectory/sf_pro_display_bolditalic.otf",
            FontWeight.Bold,
            FontStyle.Italic
        ),

        font(
            "SF Pro Extra Bold ",
            "$fontDirectory/sf_pro_display_heavy.otf",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        font(
            "SF Pro Extra Bold Italic",
            "$fontDirectory/sf_pro_display_heavyitalic.otf",
            FontWeight.ExtraBold,
            FontStyle.Italic
        ),

        font(
            "SF Pro Medium",
            "$fontDirectory/sf_pro_display_medium.otf",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        font(
            "SF Pro Medium Italic",
            "$fontDirectory/sf_pro_display_mediumitalic.otf",
            FontWeight.Medium,
            FontStyle.Italic
        )
    )
}

expect fun font(alias: String, path: String, weight: FontWeight, style: FontStyle): Font