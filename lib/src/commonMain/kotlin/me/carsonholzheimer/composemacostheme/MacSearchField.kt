package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.carsonholzheimer.composemacostheme.modifiedofficial.MacOutlinedTextField

@Composable
fun MacSearchField(
    onSearchRequested: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }
    MacOutlinedTextField(
        value,
        onValueChange = { value = it },

        // Not sure this is the best way to handle enter pressed. IME actions don't seem reliable yet on desktop
        modifier = modifier.onKeyEvent {
            if (it.type == KeyEventType.KeyUp && it.key == Key.Enter) {
                onSearchRequested(value)
                return@onKeyEvent true
            }
            false
        },
        leadingIcon = { Text("􀊫") },
        trailingIcon = {
            if (value.isNotEmpty()) {
                CloseIcon(
                    closeClicked = {
                        value = ""
                        onSearchRequested(value)
                    }
                )
            }
        },
        placeholder = { Text("Search") },
        singleLine = true,
        cornerRadius = SearchTextFieldCornerRadius
    )
}

@Composable
private fun CloseIcon(closeClicked: () -> Unit) {
    Text(
        "\uDBC0\uDD84",
        Modifier.clickable {
            closeClicked()
        },
        fontSize = 10.sp,
        fontWeight = FontWeight.W700
    )
}

private val SearchTextFieldCornerRadius = 8.dp