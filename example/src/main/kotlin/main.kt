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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Position
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun main() = Window {
    var text by remember { mutableStateOf("Hello, World!") }

    MacTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "\uDBC0\uDD87\n" +
                        "\uDBC0\uDD88 \n" +
                        " Hello",
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                fontFamily = MacFonts.SFPro()
            )
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
//            var isChecked by remember { mutableStateOf(false) }
//            Checkbox(
//                isChecked,
//                {
//                    isChecked = it
//                }
//            )

//            var textFieldValue by remember { mutableStateOf("Hi") }
//            OutlinedTextField(
//                textFieldValue,
//                { textFieldValue = it }
//            )
//            var textFieldValue2 by remember { mutableStateOf("Hi") }
//            OutlinedTextField(
//                textFieldValue2,
//                { textFieldValue2 = it }
//            )

            val items = listOf("Blue", "Red", "Green")
            var showMenu by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }

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
                        MacButton(
                            onClick = { showMenu = true },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.size(16.dp)
                        ) {
                            Text(
                                "\uDBC0\uDD87\n\uDBC0\uDD88",
                                style = TextStyle(fontSize = 8.sp, fontWeight = W700, lineHeight = 6.sp)
                            )
                        }
                    }
                },
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                Modifier.border(1.dp, MacColors.lightGray, RoundedCornerShape(4.dp)),
                dropdownOffset = Position((-100).dp, (-20).dp) // TODO get exact widths
            ) {
                items.forEachIndexed { index, string ->
                    var isMouseHovering by remember { mutableStateOf(false) }
                    DropdownMenuItem(
                        onClick = {
                            selectedIndex = index
                            showMenu = false
                        },
                        modifier = Modifier.background(if (isMouseHovering) MacColors.lightBlue else Color.Unspecified)
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
                            text = string,
                            color = if (isMouseHovering) Color.White else Color.Unspecified,
                            style = TextStyle(fontSize = 13.sp)

                        )
                    }
                }
            }
        }
    }
}