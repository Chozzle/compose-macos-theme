package io.chozzle.composewindowstheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.fontFamily

object WindowsFonts {

    @Composable
    fun SegoeUI() = fontFamily(
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
}

@Composable
expect fun font(path: String, weight: FontWeight, style: FontStyle): Font