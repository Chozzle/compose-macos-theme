package io.chozzle.composemacostheme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.chozzle.composemacostheme.modifiedofficial.MacDropdownMenu
import io.chozzle.composemacostheme.modifiedofficial.MacDropdownMenuItem

/**
 * Implementation of a DropdownMenu with DropdownMenuItems styled and arranged to mimic MacOS theme
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MacDropdownMenu(
    menuItems: List<String>,
    selectedIndex: Int,
    onItemSelected: (selectedIndex: Int) -> Unit,
    dropdownModifier: Modifier = Modifier
) {
    val longestItem = menuItems.maxByOrNull { it.length }.orEmpty()
    var expanded by remember { mutableStateOf(false) }
    val heightOfItem = with(LocalDensity.current) { FontSize.toDp() + MenuItemPadding * 2 + FontPadding * 2 }
    DropdownToggle(
        longestItem,
        menuItems,
        selectedIndex,
        onClick = { expanded = true }
    ) {
        MacDropdownMenu(
            expanded = expanded,
            modifier = dropdownModifier,
            onDismissRequest = { expanded = false },
            offset = DpOffset(0.dp, -(heightOfItem * (selectedIndex + 1)))
        ) {
            MenuItems(
                menuItems = menuItems,
                onItemSelected = { selectedIndex ->
                    expanded = false
                    onItemSelected(selectedIndex)
                },
                selectedIndex = selectedIndex,
                longestItem = longestItem
            )
        }
    }
}

@Composable
private fun MenuItems(
    menuItems: List<String>,
    onItemSelected: (selectedIndex: Int) -> Unit,
    selectedIndex: Int,
    longestItem: String
) {
    menuItems.forEachIndexed { index, itemString ->
        var isPointerHovering by remember { mutableStateOf(false) }
        MacDropdownMenuItem(
            onClick = {
                onItemSelected(index)
            },
            modifier = Modifier
                .pointerMoveFilter(
                    onEnter = {
                        isPointerHovering = true
                        false
                    },
                    onExit = {
                        isPointerHovering = false
                        false
                    }
                )
                .background(
                    if (isPointerHovering) MacTheme.colors.primary.copy(alpha = 0.7f) else Color.Unspecified,
                    shape = RoundedCornerShape(4.dp)
                ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                Modifier.fillMaxHeight().padding(
                    start = 2.dp,
                    top = 2.dp,
                    end = 12.dp,
                    bottom = 2.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val checkOrSpace = if (index == selectedIndex) "\uDBC0\uDD85" else "      "

                Text(
                    text = checkOrSpace,
                    Modifier.padding(end = MenuItemPadding),
                    color = if (isPointerHovering) Color.White else Color.Unspecified,
                    fontSize = CheckFontSize,
                    fontWeight = FontWeight.Bold
                )

                Box(contentAlignment = Alignment.CenterStart) {
                    // Invisible text just to calculate potential space taken by longest item
                    Text(
                        text = longestItem,
                        color = Color.Transparent,
                        style = LocalTextStyle.current.copy(fontSize = FontSize)
                    )

                    Text(
                        text = itemString,
                        color = if (isPointerHovering) Color.White else Color.Unspecified,
                        style = LocalTextStyle.current.copy(fontSize = FontSize)
                    )
                }
            }
        }
    }
}

@Composable
private fun DropdownToggle(
    longestItem: String,
    menuItems: List<String>,
    selectedIndex: Int,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        elevation = 2.dp,
        border = BorderStroke(Dp.Hairline, MacTheme.colors.border)
    ) {
        Row(
            modifier = Modifier.clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ).padding(3.dp),
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
        content()
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
            // Unfortunately lineHeight doesn't seem to respect small enough values so used offsets
            Text(
                "\uDBC0\uDD87",
                fontSize = 7.5.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = (-3).dp)
            )
            Text(
                "\uDBC0\uDD88",
                fontSize = 7.5.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = 3.dp)
            )
        }
    }
}

private val CheckFontSize = 10.sp
private val FontSize = 13.sp
private val FontPadding = 1.5.dp
private val MenuItemPadding = 3.dp


