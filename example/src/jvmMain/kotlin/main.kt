import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.chozzle.composemacostheme.MacDropdownMenu
import io.chozzle.composemacostheme.MacExampleView
import io.chozzle.composemacostheme.MacTheme
import io.chozzle.composenativetheme.NativeExampleView
import io.chozzle.composewindowstheme.WindowsExampleView

@OptIn(ExperimentalMaterialApi::class)
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Native theme for Compose :O") {
        MaterialTheme {
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    Text("Windows:")
                    WindowsExampleView()
                }
                Column(
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    Text("Native theme:")
                    NativeExampleView()
                }

                Column(
                    Modifier.weight(1f)
                        .fillMaxHeight()
                        .background(color = Color(red = 246, green = 246, blue = 246, alpha = 255)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    Text("Mac:")
                    MacTheme {
                        MacExampleView()

                        val menuItems = listOf(
                            "Some options",
                            "Orange",
                            "Yellow",
                            "Green",
                            "Blue",
                            "Indigo",
                            "Kinda brownish gray",
                        )
                        var selectedIndex1 by remember { mutableStateOf(0) }
                        MacDropdownMenu(
                            menuItems,
                            selectedIndex1,
                            onItemSelected = {
                                selectedIndex1 = it
                            },
                        )

                        Spacer(Modifier.height(8.dp))

                        var selectedIndex2 by remember { mutableStateOf(0) }
                        MacDropdownMenu(
                            menuItems,
                            selectedIndex2,
                            onItemSelected = {
                                selectedIndex2 = it
                            },
                        )

                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}