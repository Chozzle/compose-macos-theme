package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.Indication
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
            Modifier.padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MacSearchField(
                onSearchRequested = { println(it) },
                modifier = Modifier.width(200.dp).align(Alignment.End)
            )

            Spacer(Modifier.height(32.dp))

            Column(
                Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                var textFieldValue by remember { mutableStateOf("One of those small text fields") }
                MacOutlinedTextField(
                    textFieldValue,
                    { textFieldValue = it },
                    placeholder = { Text("Phone No.") },
                )

                CheckboxWithLabel()

                Spacer(Modifier.height(16.dp))
                var text by remember { mutableStateOf("Those familiar buttons") }
                MacButton(
                    onClick = {
                        text = "With the subtle interaction"
                    }
                ) {
                    Text(
                        text,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
private fun CheckboxWithLabel() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        var isChecked by remember { mutableStateOf(false) }
        MacCheckbox(
            isChecked,
            { isChecked = it }
        )
        Spacer(Modifier.width(6.dp))
        Text(
            "You better check yourself!",
            Modifier.clickable(indication = null) { isChecked = !isChecked },
            fontSize = 13.sp
        )
    }
}