import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.chozzle.composemacostheme.MacExampleView
import io.chozzle.composewindowstheme.WindowsExampleView
import io.chozzle.composemacostheme.MacDropdownMenu
import io.chozzle.composemacostheme.MacTheme

fun main() = Window(title = "Native theme for Compose :)") {
    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
        WindowsExampleView()

        Column(
            Modifier.fillMaxSize()
                .background(color = Color(red = 246, green = 246, blue = 246, alpha = 255)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Mac:")
            MacTheme {


                MacExampleView()

                Spacer(Modifier.height(16.dp))

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
                    toggleModifier = Modifier.padding(start = 16.dp)
                )

                Spacer(Modifier.height(16.dp))

                var selectedIndex2 by remember { mutableStateOf(0) }
                MacDropdownMenu(
                    menuItems,
                    selectedIndex2,
                    onItemSelected = {
                        selectedIndex2 = it
                    },
                    toggleModifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}