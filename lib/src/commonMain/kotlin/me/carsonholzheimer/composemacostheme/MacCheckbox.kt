package me.carsonholzheimer.composemacostheme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MacCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = macCheckboxColors()
) {
    Box(
        modifier = modifier.size(CheckboxSize)
            .clip(RoundedCornerShape(RadiusSize))
            .clickable {
            onCheckedChange(!checked)
        },
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
        val checkColor = colors.checkmarkColor(state)
        Text("\uDBC0\uDD85", fontSize = 10.sp, color = checkColor, fontWeight = FontWeight.W800)
    }
}

@ExperimentalMaterialApi
@Composable
private fun macCheckboxColors(
    checkedColor: Color = MacTheme.colors.primary,
    uncheckedColor: Color = MacTheme.colors.primary50,
    checkmarkColor: Color = MaterialTheme.colors.surface,
    disabledColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    disabledIndeterminateColor: Color = checkedColor.copy(alpha = ContentAlpha.disabled)
): CheckboxColors {
    return remember(
        checkedColor,
        uncheckedColor,
        checkmarkColor,
        disabledColor,
        disabledIndeterminateColor
    ) {
        MacCheckboxColors(
            checkedBorderColor = checkedColor,
            checkedBoxColor = checkedColor,
            checkedCheckmarkColor = checkmarkColor,
            uncheckedCheckmarkColor = checkmarkColor.copy(alpha = 0f),
            uncheckedBoxColor = checkedColor.copy(alpha = 0f),
            disabledCheckedBoxColor = disabledColor,
            disabledUncheckedBoxColor = disabledColor.copy(alpha = 0f),
            disabledIndeterminateBoxColor = disabledIndeterminateColor,
            uncheckedBorderColor = uncheckedColor,
            disabledBorderColor = disabledColor,
            disabledIndeterminateBorderColor = disabledIndeterminateColor
        )
    }
}

@ExperimentalMaterialApi
private class MacCheckboxColors(
    private val checkedCheckmarkColor: Color,
    private val uncheckedCheckmarkColor: Color,
    private val checkedBoxColor: Color,
    private val uncheckedBoxColor: Color,
    private val disabledCheckedBoxColor: Color,
    private val disabledUncheckedBoxColor: Color,
    private val disabledIndeterminateBoxColor: Color,
    private val checkedBorderColor: Color,
    private val uncheckedBorderColor: Color,
    private val disabledBorderColor: Color,
    private val disabledIndeterminateBorderColor: Color
) : CheckboxColors {
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

    override fun checkmarkColor(state: ToggleableState): Color {
        return if (state == ToggleableState.Off) {
            uncheckedCheckmarkColor
        } else {
            checkedCheckmarkColor
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

private val RadiusSize = 4.dp
private val StrokeWidth = 0.5.dp
private val CheckboxSize = 14.dp
