package io.chozzle.composenativetheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

object WindowsFonts {

    @Composable
    fun SegoeUI() = FontFamily(
        font(
            "segoe_ui",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        font(
            "segoe_ui_italic",
            FontWeight.Normal,
            FontStyle.Italic
        ),
        font(
            "segoe_ui_bold",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        font(
            "segoe_ui_bold_italic",
            FontWeight.Bold,
            FontStyle.Italic
        )
    )

    @Composable
    fun SegoeAssets() = font(
        "segmdl2",
        FontWeight.Normal,
        FontStyle.Normal
    )
}

@Composable
fun font(path: String, weight: FontWeight, style: FontStyle): Font = TODO()