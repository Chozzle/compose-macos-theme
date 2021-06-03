package io.chozzle.composemacostheme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.chozzle.composemacostheme.modifiedofficial.MacOutlinedTextField

@Composable
fun MacExampleView() {
    MacTheme {
        Column(
            Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MacSearchField(
                onSearchRequested = { println("Searched: $it") },
                modifier = Modifier.width(200.dp)
            )

            Spacer(Modifier.height(16.dp))

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
                        Modifier.fillMaxWidth().background(color = MaterialTheme.colors.surface),
                        placeholder = { Text("Email") },
                        singleLine = true
                    )

                    var textFieldValue2 by remember { mutableStateOf("An imaginary form") }
                    MacOutlinedTextField(
                        textFieldValue2,
                        { textFieldValue2 = it },
                        Modifier.fillMaxWidth().background(color = MaterialTheme.colors.surface),
                        placeholder = { Text("Phone No.") },
                        singleLine = true
                    )
                }

                Spacer(Modifier.height(16.dp))


                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        CheckboxWithLabel("Haz", checked = true, enabled = false)
                        CheckboxWithLabel("Check", checked = false)
                        CheckboxWithLabel("Boxes!", checked = true)
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        RadioButtonWithLabel(
                            "And",
                            selected = true,
                            enabled = false,
                        )
                        RadioGroup(listOf("Radio", "Buttons"))
                    }
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
        Text("Primary")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        MacButton(onClick = {}) {
            Text("Primary")
        }
        MacSecondaryButton(onClick = {}) {
            Text("Secondary")
        }
        MacButton(enabled = false, onClick = {}) {
            Text("Disabled")
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
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        Modifier.clickable(interactionSource = interactionSource, indication = null) {
            if (!enabled) return@clickable
            isChecked = !isChecked
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MacCheckbox(
            isChecked,
            { isChecked = it },
            enabled = enabled,
            interactionSource = interactionSource
        )
        Spacer(Modifier.width(6.dp))
        Text(
            label,
            color = if (enabled) {
                LocalTextStyle.current.color
            } else {
                LocalTextStyle.current.color.copy(alpha = ContentAlpha.disabled)
            },
            fontSize = 13.sp
        )
    }
}

@Composable
private fun RadioButtonWithLabel(
    label: String,
    selected: Boolean,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MacRadioButton(
            selected,
            null, // null recommended for accessibility with screenreaders
            enabled = enabled,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        )
        Spacer(Modifier.width(6.dp))
        Text(
            label,
            color = if (enabled) {
                LocalTextStyle.current.color
            } else {
                LocalTextStyle.current.color.copy(alpha = ContentAlpha.disabled)
            },
            fontSize = 13.sp
        )
    }
}

@Composable
private fun RadioGroup(
    labels: List<String>,
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(labels[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        labels.forEach { text ->
            val interactionSource = remember { MutableInteractionSource() }
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton,
                        interactionSource = interactionSource,
                        indication = null,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButtonWithLabel(
                    text,
                    selected = (text == selectedOption),
                    enabled = true,
                    interactionSource = interactionSource
                )
            }
        }
    }
}

