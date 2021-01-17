package io.chozzle.composemacostheme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MacDropdownMenu(
    menuItems: List<String>,
    onItemSelected: (selectedIndex: Int) -> Unit,
    toggleModifier: Modifier = Modifier,
    dropdownModifier: Modifier = Modifier
) {
    val longestItem = menuItems.maxByOrNull { it.length }.orEmpty()
    var showMenu by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    val heightOfItem = with(AmbientDensity.current) { FontSize.toDp() + MenuItemPadding * 2 + FontPadding * 2 }
    MacDropdownMenu(
        toggle = {
            DropdownToggle(
                longestItem,
                FontSize,
                menuItems,
                selectedIndex,
                onClick = { showMenu = true }
            )
        },
        toggleModifier = toggleModifier,
        dropdownModifier = dropdownModifier,
        expanded = showMenu,
        onDismissRequest = { showMenu = false },
        dropdownOffset = Position(0.dp, -(heightOfItem * (selectedIndex + 1)))
    ) {
        menuItems.forEachIndexed { index, itemString ->
            var isMouseHovering by remember { mutableStateOf(false) }
            MacDropdownMenuItem(
                onClick = {
                    selectedIndex = index
                    showMenu = false
                    onItemSelected(index)
                },
                modifier = Modifier
                    .pointerMoveFilter(
                        onEnter = {
                            isMouseHovering = true
                            false
                        },
                        onExit = {
                            isMouseHovering = false
                            false
                        }
                    ).padding(horizontal = 6.dp)
                    .background(
                        if (isMouseHovering) MacTheme.colors.primary.copy(alpha = 0.7f) else Color.Unspecified,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Row(
                    Modifier.fillMaxHeight().padding(MenuItemPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val checkOrSpace = if (index == selectedIndex) "\uDBC0\uDD85" else "     "

                    Text(
                        text = checkOrSpace,
                        Modifier.padding(end = MenuItemPadding),
                        color = if (isMouseHovering) Color.White else Color.Unspecified,
                        fontSize = CheckTextStyle.fontSize - 2.sp,
                        fontWeight = CheckTextStyle.fontWeight
                    )

                    Box(contentAlignment = Alignment.CenterStart) {
                        // Invisible text just to calculate potential space taken by longest item
                        Text(
                            text = longestItem,
                            color = Color.Transparent,
                            style = AmbientTextStyle.current.merge(CheckTextStyle)
                        )

                        Text(
                            text = itemString,
                            color = if (isMouseHovering) Color.White else Color.Unspecified,
                            style = AmbientTextStyle.current.merge(CheckTextStyle)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DropdownToggle(
    longestItem: String,
    FontSize: TextUnit,
    menuItems: List<String>,
    selectedIndex: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = onClick)
            .border(Dp.Hairline, MacTheme.colors.border, RoundedCornerShape(4.dp))
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier.padding(horizontal = 6.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                longestItem,
                fontSize = FontSize,
                color = Color.Transparent
            )
            Text(
                menuItems[selectedIndex],
                fontSize = FontSize
            )
        }
        DisclosureDoubleArrow()
    }
}

@Composable
private fun DisclosureDoubleArrow() {
    Surface(
        modifier = Modifier.size(16.dp),
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.small
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                "\uDBC0\uDD87\n\uDBC0\uDD88",
                // TODO Change to 8 and check after this is fixed: https://github.com/JetBrains/compose-jb/issues/171
                fontSize = 7.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 6.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private val FontSize = 13.sp
private val CheckTextStyle = TextStyle(
    fontSize = FontSize,
    fontWeight = FontWeight.W800,
)
private val FontPadding = 1.5.dp
private val MenuItemPadding = 3.dp


