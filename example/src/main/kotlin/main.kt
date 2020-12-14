import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Position
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fromOfficalCompose.MacOutlinedTextField


fun main() = Window {
    var text by remember { mutableStateOf("Hello, World!") }

    MacTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
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
                placeholder = { Text("Search")}
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

            DropdownMenu(
                toggle = {
                    Row(
                        modifier = Modifier.clickable(onClick = { showMenu = true }),

                        ) {
                        Text(
                            items[selectedIndex],
                            modifier = Modifier.padding(2.dp),
                            style = TextStyle(fontSize = 13.sp)
                        )
                        Surface(
                            modifier = Modifier.size(16.dp),
                            color = MaterialTheme.colors.primary,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                "\uDBC0\uDD87\uDBC0\uDD88",
                                fontSize = 8.sp,
                                fontWeight = W700,
                                lineHeight = 6.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                Modifier.border(1.dp, MacTheme.colors.border, RoundedCornerShape(4.dp)),
                dropdownOffset = Position((-100).dp, (-20).dp) // TODO get exact widths
            ) {
                items.forEachIndexed { index, string ->
                    var isMouseHovering by remember { mutableStateOf(false) }
                    DropdownMenuItem(
                        onClick = {
                            selectedIndex = index
                            showMenu = false
                        },
                        modifier = Modifier.background(if (isMouseHovering) MacTheme.colors.highlight else Color.Unspecified)
                            .height(20.dp)
                            .pointerMoveFilter(
                                onEnter = {
                                    isMouseHovering = true
                                    false
                                },
                                onExit = {
                                    isMouseHovering = false
                                    false
                                }
                            )
                    ) {
                        Text(
                            text = (if (index == selectedIndex) "􀆅 " else "     ") + string,
                            color = if (isMouseHovering) Color.White else Color.Unspecified,
                            style = TextStyle(fontSize = 13.sp)

                        )
                    }
                }
            }
        }
    }
}