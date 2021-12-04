package io.chozzle.composewindowstheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun WindowsExampleView() {
    WindowsTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ButtonsView()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ButtonsView() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        WindowsButton(onClick = {}) {
            Text("Primary")
        }

        WindowsButton(enabled = false, onClick = {}) {
            Text("Disabled")
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        WindowsButton(
            onClick = {},
            colors = WindowsButtonDefaults.accentColors
        ) {
            Text("Accent")
        }

        WindowsButton(
            enabled = false,
            onClick = {},
            colors = WindowsButtonDefaults.accentColors
        ) {
            Text("Disabled")
        }
    }
}
