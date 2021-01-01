import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import me.carsonholzheimer.composemacostheme.ExampleView
import me.carsonholzheimer.composemacostheme.MacDropdownMenu
import me.carsonholzheimer.composemacostheme.MacTheme


fun main() = Window {
    MacTheme {
        Column {
            ExampleView()

            MacDropdownMenu(
                listOf(
                    "Red",
                    "Orange",
                    "Yellow",
                    "Green",
                    "Blue",
                    "Indigo",
                    "Violet long word",
                    "Yellow",
                    "Green",
                    "Blue",
                    "Indigo"
                )
            ) { selectedIndex ->

            }
        }
    }
}