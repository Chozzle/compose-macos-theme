package io.chozzle.composemacostheme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.CheckboxColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MacCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: MacCheckboxColors = macCheckboxColors()
) {
    Box(
        modifier = modifier.size(CheckboxSize)
            .clip(RoundedCornerShape(RadiusSize))
            .background(color = MaterialTheme.colors.surface)
            .triStateToggleable(
            state = ToggleableState(checked),
            onClick = { onCheckedChange(!checked) },
            enabled = enabled,
            interactionSource = interactionSource,
            indication = if (enabled) LocalIndication.current else null // Unfortunately necessary
        ),
        Alignment.Center
    ) {
        val state = ToggleableState(checked)
        Canvas(
            modifier.wrapContentSize(Alignment.Center)
                .fillMaxSize()
        ) {
            val boxColor = colors.boxColor(enabled, state)
            val borderColor = colors.borderColor(enabled, state)

            val strokeWidthPx = StrokeWidth.toPx()
            drawBox(
                boxColor = boxColor,
                borderColor = borderColor,
                radius = RadiusSize.toPx(),
                strokeWidth = strokeWidthPx
            )
        }
        val checkColor = colors.checkmarkColor(enabled, state)
        MacCheckboxCheck(checkColor)
    }
}

@Composable
expect fun MacCheckboxCheck(checkColor: Color)

@ExperimentalMaterialApi
@Composable
private fun macCheckboxColors(
    checkedColor: Color = MacTheme.colors.primary,
    uncheckedColor: Color = MacTheme.colors.primary50,
    checkmarkColor: Color = MaterialTheme.colors.surface,
    disabledCheckmarkColor: Color = MacTheme.colors.primary50,
    disabledColor: Color = MacDisabledBackgroundColor.copy(alpha = 0.03f),
    disabledIndeterminateColor: Color = checkedColor.copy(alpha = ContentAlpha.disabled)
): MacCheckboxColors {
    return remember(
        checkedColor,
        uncheckedColor,
        checkmarkColor,
        disabledColor,
        disabledIndeterminateColor
    ) {
        MacCheckboxColorsImpl(
            checkedCheckmarkColor = checkmarkColor,
            uncheckedCheckmarkColor = checkmarkColor.copy(alpha = 0f),
            checkedBoxColor = checkedColor,
            uncheckedBoxColor = checkedColor.copy(alpha = 0f),
            disabledCheckmarkColor = disabledCheckmarkColor,
            disabledCheckedBoxColor = disabledColor,
            disabledUncheckedBoxColor = disabledColor,
            disabledIndeterminateBoxColor = disabledIndeterminateColor,
            checkedBorderColor = checkedColor,
            uncheckedBorderColor = uncheckedColor,
            disabledBorderColor = uncheckedColor.copy(alpha = 0.33f),
            disabledIndeterminateBorderColor = disabledIndeterminateColor
        )
    }
}

@ExperimentalMaterialApi
private class MacCheckboxColorsImpl(
    private val checkedCheckmarkColor: Color,
    private val uncheckedCheckmarkColor: Color,
    private val checkedBoxColor: Color,
    private val uncheckedBoxColor: Color,
    private val disabledCheckmarkColor: Color,
    private val disabledCheckedBoxColor: Color,
    private val disabledUncheckedBoxColor: Color,
    private val disabledIndeterminateBoxColor: Color,
    private val checkedBorderColor: Color,
    private val uncheckedBorderColor: Color,
    private val disabledBorderColor: Color,
    private val disabledIndeterminateBorderColor: Color
) : MacCheckboxColors {
    override fun borderColor(enabled: Boolean, state: ToggleableState): Color {
        return if (enabled) {
            when (state) {
                ToggleableState.On, ToggleableState.Indeterminate -> checkedBorderColor
                ToggleableState.Off -> uncheckedBorderColor
            }
        } else {
            when (state) {
                ToggleableState.Indeterminate -> disabledIndeterminateBorderColor
                ToggleableState.On, ToggleableState.Off -> disabledBorderColor
            }
        }
    }

    override fun boxColor(enabled: Boolean, state: ToggleableState): Color {
        return if (enabled) {
            when (state) {
                ToggleableState.On, ToggleableState.Indeterminate -> checkedBoxColor
                ToggleableState.Off -> uncheckedBoxColor
            }
        } else {
            when (state) {
                ToggleableState.On -> disabledCheckedBoxColor
                ToggleableState.Indeterminate -> disabledIndeterminateBoxColor
                ToggleableState.Off -> disabledUncheckedBoxColor
            }
        }
    }

    override fun checkmarkColor(enabled: Boolean, state: ToggleableState): Color {
        return if (enabled) {
            if (state == ToggleableState.Off) {
                uncheckedCheckmarkColor
            } else {
                checkedCheckmarkColor
            }
        } else {
            if (state == ToggleableState.Off) {
                uncheckedCheckmarkColor
            } else {
                disabledCheckmarkColor
            }
        }
    }
}

private fun DrawScope.drawBox(
    boxColor: Color,
    borderColor: Color,
    radius: Float,
    strokeWidth: Float
) {
    val halfStrokeWidth = strokeWidth / 2.0f
    val stroke = Stroke(strokeWidth)
    val checkboxSize = size.width
    drawRoundRect(
        boxColor,
        topLeft = Offset(strokeWidth, strokeWidth),
        size = Size(checkboxSize - strokeWidth * 2, checkboxSize - strokeWidth * 2),
        cornerRadius = CornerRadius(radius / 2),
        style = Fill
    )
    drawRoundRect(
        borderColor,
        topLeft = Offset(halfStrokeWidth, halfStrokeWidth),
        size = Size(checkboxSize - strokeWidth, checkboxSize - strokeWidth),
        cornerRadius = CornerRadius(radius),
        style = stroke
    )
}


/**
 * Copies [CheckboxColors] but with the addition of passing in the enabled state to calculate checkbox color
 */
@ExperimentalMaterialApi
@Stable
interface MacCheckboxColors {

    /**
     * Represents the color used for the checkmark inside the checkbox, depending on [state].
     *
     * @param state the [ToggleableState] of the checkbox
     */
    fun checkmarkColor(enabled: Boolean, state: ToggleableState): Color

    /**
     * Represents the color used for the box (background) of the checkbox, depending on [enabled]
     * and [state].
     *
     * @param enabled whether the checkbox is enabled or not
     * @param state the [ToggleableState] of the checkbox
     */
    fun boxColor(enabled: Boolean, state: ToggleableState): Color

    /**
     * Represents the color used for the border of the checkbox, depending on [enabled] and [state].
     *
     * @param enabled whether the checkbox is enabled or not
     * @param state the [ToggleableState] of the checkbox
     */
    fun borderColor(enabled: Boolean, state: ToggleableState): Color
}

private val RadiusSize = 4.dp
private val StrokeWidth = 0.5.dp
private val CheckboxSize = 14.dp
