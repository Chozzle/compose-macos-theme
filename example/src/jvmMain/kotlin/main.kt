import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.carsonholzheimer.composemacostheme.ExampleView
import me.carsonholzheimer.composemacostheme.MacDropdownMenu
import me.carsonholzheimer.composemacostheme.MacTheme


fun main() = Window(title = "MacOS theme for Compose :)") {
    MacTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ExampleView()

            val menuItems = listOf(
                "Some options",
                "Orange",
                "Yellow",
                "Green",
                "Blue",
                "Indigo",
                "Kinda brownish gray",
            )
            MacDropdownMenu(
                menuItems,
                onItemSelected = { selectedIndex ->
                    println(menuItems[selectedIndex] + " selected")
                },
                toggleModifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}