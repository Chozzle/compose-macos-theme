package io.chozzle.composenativetheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun NativeExampleView() {

    CompositionLocalProvider(
        LocalTheme provides Mac,
    ) {
        NativeTheme {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ButtonsView()
            }
        }
    }
}

@Composable
private fun ButtonsView() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(onClick = {}) {
            Text("Primary")
        }

        Button(enabled = false, onClick = {}) {
            Text("You can't touch this")
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(
            onClick = {},
            colors = NativeButtonDefaults.accentColors
        ) {
            Text("Primary")
        }

        Button(
            enabled = false,
            onClick = {},
            colors = NativeButtonDefaults.accentColors
        ) {
            Text("You can't touch this")
        }
    }
}