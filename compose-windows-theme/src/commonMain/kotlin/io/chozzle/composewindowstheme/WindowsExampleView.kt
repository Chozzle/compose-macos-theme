package io.chozzle.composewindowstheme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WindowsExampleView() {
    WindowsTheme {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Windows:")

            Spacer(Modifier.height(32.dp))

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
        WindowsButton(onClick = {}) {
            Text("Primary")
        }

        WindowsButton(enabled = false, onClick = {}) {
            Text("You can't touch this")
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        WindowsButton(
            onClick = {},
            colors = WindowsButtonDefaults.accentColors
        ) {
            Text("Primary")
        }

        WindowsButton(
            enabled = false,
            onClick = {},
            colors = WindowsButtonDefaults.accentColors
        ) {
            Text("You can't touch this")
        }
    }
}
