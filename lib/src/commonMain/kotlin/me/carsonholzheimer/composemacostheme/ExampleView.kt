package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.carsonholzheimer.composemacostheme.modifiedofficial.MacOutlinedTextField

@Composable
fun ExampleView() {
    MacTheme {
        Column(
            Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MacSearchField(
                onSearchRequested = { println(it) },
                modifier = Modifier.width(200.dp).align(Alignment.End)
            )

            Spacer(Modifier.height(32.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var textFieldValue by remember { mutableStateOf("One of those small text fields") }
                MacOutlinedTextField(
                    textFieldValue,
                    { textFieldValue = it },
                    placeholder = { Text("Phone No.") },
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))


                CheckboxWithLabel("Check", initiallyChecked = false)
                CheckboxWithLabel("Boxes!", initiallyChecked = true)

                Spacer(Modifier.height(16.dp))

                ButtonsView()
            }
        }
    }
}

@Composable
private fun ButtonsView() {
    MacButton(
        macButtonStyle = MacButtonStyle.Large,
        onClick = {}
    ) {
        Text("A large one")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        MacButton(onClick = {}) {
            Text("Primary")
        }
        MacSecondaryButton(onClick = {}) {
            Text("Secondary")
        }
        MacButton(enabled = false, onClick = {}) {
            Text("You can't touch this")
        }
    }
}

@Composable
private fun CheckboxWithLabel(label: String, initiallyChecked: Boolean) {
    var isChecked by remember { mutableStateOf(initiallyChecked) }
    Row(
        Modifier.clickable(indication = null) { isChecked = !isChecked },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MacCheckbox(
            isChecked,
            { isChecked = it }
        )
        Spacer(Modifier.width(6.dp))
        Text(
            label,
            fontSize = 13.sp
        )
    }
}