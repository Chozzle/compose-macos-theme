package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AmbientTextStyle
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                onSearchRequested = { println("Searched: $it") },
                modifier = Modifier.width(200.dp).align(Alignment.End)
            )

            Spacer(Modifier.height(32.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.width(180.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    var textFieldValue by remember { mutableStateOf("") }
                    MacOutlinedTextField(
                        textFieldValue,
                        { textFieldValue = it },
                        Modifier.fillMaxWidth(),
                        placeholder = { Text("Email") },
                        singleLine = true
                    )

                    var textFieldValue2 by remember { mutableStateOf("An imaginary form") }
                    MacOutlinedTextField(
                        textFieldValue2,
                        { textFieldValue2 = it },
                        Modifier.fillMaxWidth(),
                        placeholder = { Text("Phone No.") },
                        singleLine = true
                    )
                }

                Spacer(Modifier.height(16.dp))


                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    CheckboxWithLabel("Haz", checked = true, enabled = false)
                    CheckboxWithLabel("Check", checked = false)
                    CheckboxWithLabel("Boxes!", checked = true)
                }

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
private fun CheckboxWithLabel(
    label: String,
    checked: Boolean,
    enabled: Boolean = true
) {
    var isChecked by remember { mutableStateOf(checked) }
    Row(
        Modifier.clickable(indication = null) {
            if (!enabled) return@clickable
            isChecked = !isChecked
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MacCheckbox(
            isChecked,
            { isChecked = it },
            enabled = enabled
        )
        Spacer(Modifier.width(6.dp))
        Text(
            label,
            color = if (enabled) {
                AmbientTextStyle.current.color
            } else {
                AmbientTextStyle.current.color.copy(alpha = ContentAlpha.disabled)
            },
            fontSize = 13.sp
        )
    }
}