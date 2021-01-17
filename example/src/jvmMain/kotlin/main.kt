import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.chozzle.composemacostheme.ExampleView
import io.chozzle.composemacostheme.MacDropdownMenu
import io.chozzle.composemacostheme.MacTheme


fun main() = Window(title = "MacOS theme for Compose :)") {
    MacTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ExampleView()

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
            MacDropdownMenu(
                menuItems,
                onItemSelected = { selectedIndex ->
                    println("Selected: ${menuItems[selectedIndex]}")
                },
                toggleModifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}