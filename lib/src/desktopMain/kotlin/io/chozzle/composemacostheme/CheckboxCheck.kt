package io.chozzle.composemacostheme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
actual fun MacCheckboxCheck(checkColor: Color)  {
    Text(
        "\uDBC0\uDD85",
        fontSize = 10.sp,
        color = checkColor,
        fontWeight = FontWeight.W800
    )
}