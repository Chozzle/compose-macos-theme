package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
actual fun CheckboxCheck(checkColor: Color)  {
    Text(
        "\uDBC0\uDD85",
        Modifier.offset(x = (-0.5).dp, y = (-2.5).dp), // Strangely need this on android so text is actually centered
        fontSize = 10.sp,
        color = checkColor,
        fontWeight = FontWeight.W800
    )
}