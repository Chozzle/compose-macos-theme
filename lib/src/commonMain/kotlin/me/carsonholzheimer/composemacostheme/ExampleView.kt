package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            var text by remember { mutableStateOf("Hello, World!") }
            Text("COMPLETED:")
            MacButton(
                onClick = {
                    text = "Hello, Desktop!"
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

            var textFieldValue by remember { mutableStateOf("Hi") }
            OutlinedTextField(
                textFieldValue,
                { textFieldValue = it }
            )
            var textFieldValue2 by remember { mutableStateOf("Hi") }
            MacOutlinedTextField(
                textFieldValue2,
                { textFieldValue2 = it },
                leadingIcon = { Text("􀊫") },
                placeholder = { Text("Search") }
            )

            val items = listOf("Blue", "Red", "Green")
            var showMenu by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }

            Box(Modifier.height(20.dp))
            Text("TODO:")

            var isChecked by remember { mutableStateOf(false) }
            Checkbox(
                isChecked,
                {
                    isChecked = it
                }
            )

        }
    }
}