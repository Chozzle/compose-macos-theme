package io.chozzle.composewindowstheme

import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AmbientTextStyle
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
}
