package me.carsonholzheimer.composemacostheme.modifiedofficial

import androidx.compose.material.*

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
internal fun TextFieldLayout(
    modifier: Modifier = Modifier,
    decoratedTextField: @Composable (Modifier) -> Unit,
    decoratedPlaceholder: @Composable ((Modifier) -> Unit)?,
    decoratedLabel: @Composable (() -> Unit)?,
    leading: @Composable (() -> Unit)?,
    trailing: @Composable (() -> Unit)?,
    singleLine: Boolean,
    leadingColor: Color,
    trailingColor: Color,
    labelProgress: Float,
    indicatorWidth: Dp,
    indicatorColor: Color,
    backgroundColor: Color,
    shape: Shape
) {
    // places leading icon, text field with label and placeholder, trailing icon
    IconsWithTextFieldLayout(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .drawIndicatorLine(
                lineWidth = indicatorWidth,
                color = indicatorColor
            ),
        textField = decoratedTextField,
        placeholder = decoratedPlaceholder,
        label = decoratedLabel,
        leading = leading,
        trailing = trailing,
        singleLine = singleLine,
        leadingColor = leadingColor,
        trailingColor = trailingColor,
        animationProgress = labelProgress
    )
}

/**
 * Layout of the leading and trailing icons and the input field, label and placeholder in
 * [TextField].
 */
@Composable
private fun IconsWithTextFieldLayout(
    modifier: Modifier = Modifier,
    textField: @Composable (Modifier) -> Unit,
    label: @Composable (() -> Unit)?,
    placeholder: @Composable ((Modifier) -> Unit)?,
    leading: @Composable (() -> Unit)?,
    trailing: @Composable (() -> Unit)?,
    singleLine: Boolean,
    leadingColor: Color,
    trailingColor: Color,
    animationProgress: Float
) {
    Layout(
        content = {
            if (leading != null) {
                Box(Modifier.layoutId("leading").iconPadding(start = HorizontalIconPadding)) {
                    Decoration(
                        contentColor = leadingColor,
                        content = leading
                    )
                }
            }
            if (trailing != null) {
                Box(Modifier.layoutId("trailing").iconPadding(end = HorizontalIconPadding)) {
                    Decoration(
                        contentColor = trailingColor,
                        content = trailing
                    )
                }
            }
            val padding = Modifier.padding(horizontal = TextFieldPadding)
            if (placeholder != null) {
                placeholder(Modifier.layoutId(PlaceholderId).then(padding))
            }
            if (label != null) {
                Box(
                    modifier = Modifier
                        .layoutId(LabelId)
                        .iconPadding(
                            start = TextFieldPadding,
                            end = TextFieldPadding
                        )
                ) { label() }
            }
            textField(Modifier.layoutId(TextFieldId).then(padding))
        },
        modifier = modifier
    ) { measurables, incomingConstraints ->
        val topBottomPadding = TextFieldPadding.toIntPx()
        val baseLineOffset = FirstBaselineOffset.toIntPx()
        val bottomPadding = LastBaselineOffset.toIntPx()
        val topPadding = TextFieldTopPadding.toIntPx()
        var occupiedSpaceHorizontally = 0

        // measure leading icon
        val constraints = incomingConstraints.copy(minWidth = 0, minHeight = 0)
        val leadingPlaceable =
            measurables.find { it.layoutId == "leading" }?.measure(constraints)
        occupiedSpaceHorizontally += widthOrZero(
            leadingPlaceable
        )

        // measure trailing icon
        val trailingPlaceable = measurables.find { it.layoutId == "trailing" }
            ?.measure(constraints.offset(horizontal = -occupiedSpaceHorizontally))
        occupiedSpaceHorizontally += widthOrZero(
            trailingPlaceable
        )

        // measure label
        val labelConstraints = constraints
            .offset(
                vertical = -bottomPadding,
                horizontal = -occupiedSpaceHorizontally
            )
        val labelPlaceable =
            measurables.find { it.layoutId == LabelId }?.measure(labelConstraints)
        val lastBaseline = labelPlaceable?.get(LastBaseline)?.let {
            if (it != AlignmentLine.Unspecified) it else labelPlaceable.height
        } ?: 0
        val effectiveLabelBaseline = max(lastBaseline, baseLineOffset)

        // measure input field
        // input field is laid out differently depending on whether the label is present or not
        val verticalConstraintOffset = if (labelPlaceable != null) {
            -bottomPadding - topPadding - effectiveLabelBaseline
        } else {
            -topBottomPadding * 2
        }
        val textFieldConstraints = incomingConstraints
            .copy(minHeight = 0)
            .offset(
                vertical = verticalConstraintOffset,
                horizontal = -occupiedSpaceHorizontally
            )
        val textFieldPlaceable = measurables
            .first { it.layoutId == TextFieldId }
            .measure(textFieldConstraints)

        // measure placeholder
        val placeholderConstraints = textFieldConstraints.copy(minWidth = 0)
        val placeholderPlaceable = measurables
            .find { it.layoutId == PlaceholderId }
            ?.measure(placeholderConstraints)

        val width = calculateWidth(
            leadingPlaceable,
            trailingPlaceable,
            textFieldPlaceable,
            labelPlaceable,
            placeholderPlaceable,
            incomingConstraints
        )
        val height = calculateHeight(
            textFieldPlaceable,
            labelPlaceable,
            effectiveLabelBaseline,
            leadingPlaceable,
            trailingPlaceable,
            placeholderPlaceable,
            incomingConstraints,
            density
        )

        layout(width, height) {
            if (widthOrZero(labelPlaceable) != 0) {
                // label's final position is always relative to the baseline
                val labelEndPosition = (baseLineOffset - lastBaseline).coerceAtLeast(0)
                placeWithLabel(
                    width,
                    height,
                    textFieldPlaceable,
                    labelPlaceable,
                    placeholderPlaceable,
                    leadingPlaceable,
                    trailingPlaceable,
                    singleLine,
                    labelEndPosition,
                    effectiveLabelBaseline + topPadding,
                    animationProgress,
                    density
                )
            } else {
                placeWithoutLabel(
                    width,
                    height,
                    textFieldPlaceable,
                    placeholderPlaceable,
                    leadingPlaceable,
                    trailingPlaceable,
                    singleLine,
                    density
                )
            }
        }
    }
}

private fun calculateWidth(
    leadingPlaceable: Placeable?,
    trailingPlaceable: Placeable?,
    textFieldPlaceable: Placeable,
    labelPlaceable: Placeable?,
    placeholderPlaceable: Placeable?,
    constraints: Constraints
): Int {
    val middleSection = maxOf(
        textFieldPlaceable.width,
        widthOrZero(labelPlaceable),
        widthOrZero(placeholderPlaceable)
    )
    val wrappedWidth =
        widthOrZero(leadingPlaceable) + middleSection + widthOrZero(
            trailingPlaceable
        )
    return max(wrappedWidth, constraints.minWidth)
}

private fun calculateHeight(
    textFieldPlaceable: Placeable,
    labelPlaceable: Placeable?,
    labelBaseline: Int,
    leadingPlaceable: Placeable?,
    trailingPlaceable: Placeable?,
    placeholderPlaceable: Placeable?,
    constraints: Constraints,
    density: Float
): Int {
    val bottomPadding = LastBaselineOffset.value * density
    val topPadding = TextFieldTopPadding.value * density
    val topBottomPadding = TextFieldPadding.value * density

    val inputFieldHeight = max(textFieldPlaceable.height, heightOrZero(placeholderPlaceable))
    val middleSectionHeight = if (labelPlaceable != null) {
        labelBaseline + topPadding + inputFieldHeight + bottomPadding
    } else {
        topBottomPadding * 2 + inputFieldHeight
    }
    return maxOf(
        middleSectionHeight.roundToInt(),
        max(heightOrZero(leadingPlaceable), heightOrZero(trailingPlaceable)),
        constraints.minHeight
    )
}

/**
 * Places the provided text field, placeholder and label with respect to the baseline offsets in
 * [TextField] when there is a label. When there is no label, [placeWithoutLabel] is used.
 */
private fun Placeable.PlacementScope.placeWithLabel(
    width: Int,
    height: Int,
    textfieldPlaceable: Placeable,
    labelPlaceable: Placeable?,
    placeholderPlaceable: Placeable?,
    leadingPlaceable: Placeable?,
    trailingPlaceable: Placeable?,
    singleLine: Boolean,
    labelEndPosition: Int,
    textPosition: Int,
    animationProgress: Float,
    density: Float
) {
    val topBottomPadding = (TextFieldPadding.value * density).roundToInt()

    leadingPlaceable?.placeRelative(
        0,
        Alignment.CenterVertically.align(leadingPlaceable.height, height)
    )
    trailingPlaceable?.placeRelative(
        width - trailingPlaceable.width,
        Alignment.CenterVertically.align(trailingPlaceable.height, height)
    )
    labelPlaceable?.let {
        // if it's a single line, the label's start position is in the center of the
        // container. When it's a multiline text field, the label's start position is at the
        // top with padding
        val startPosition = if (singleLine) {
            Alignment.CenterVertically.align(it.height, height)
        } else {
            topBottomPadding
        }
        val distance = startPosition - labelEndPosition
        val positionY = startPosition - (distance * animationProgress).roundToInt()
        it.placeRelative(widthOrZero(leadingPlaceable), positionY)
    }
    textfieldPlaceable.placeRelative(widthOrZero(leadingPlaceable), textPosition)
    placeholderPlaceable?.placeRelative(widthOrZero(leadingPlaceable), textPosition)
}

/**
 * Places the provided text field and placeholder in [TextField] when there is no label. When
 * there is a label, [placeWithLabel] is used
 */
private fun Placeable.PlacementScope.placeWithoutLabel(
    width: Int,
    height: Int,
    textPlaceable: Placeable,
    placeholderPlaceable: Placeable?,
    leadingPlaceable: Placeable?,
    trailingPlaceable: Placeable?,
    singleLine: Boolean,
    density: Float
) {
    val topBottomPadding = (TextFieldPadding.value * density).roundToInt()

    leadingPlaceable?.placeRelative(
        0,
        Alignment.CenterVertically.align(leadingPlaceable.height, height)
    )
    trailingPlaceable?.placeRelative(
        width - trailingPlaceable.width,
        Alignment.CenterVertically.align(trailingPlaceable.height, height)
    )

    // Single line text field without label places its input center vertically. Multiline text
    // field without label places its input at the top with padding
    val textVerticalPosition = if (singleLine) {
        Alignment.CenterVertically.align(textPlaceable.height, height)
    } else {
        topBottomPadding
    }
    textPlaceable.placeRelative(
        widthOrZero(leadingPlaceable),
        textVerticalPosition
    )

    // placeholder is placed similar to the text input above
    placeholderPlaceable?.let {
        val placeholderVerticalPosition = if (singleLine) {
            Alignment.CenterVertically.align(placeholderPlaceable.height, height)
        } else {
            topBottomPadding
        }
        it.placeRelative(
            widthOrZero(leadingPlaceable),
            placeholderVerticalPosition
        )
    }
}

/**
 * A draw modifier that draws a bottom indicator line in [TextField]
 */
private fun Modifier.drawIndicatorLine(lineWidth: Dp, color: Color): Modifier {
    return drawBehind {
        val strokeWidth = lineWidth.value * density
        val y = size.height - strokeWidth / 2
        drawLine(
            color,
            Offset(0f, y),
            Offset(size.width, y),
            strokeWidth
        )
    }
}

private val FirstBaselineOffset = 20.dp
private val LastBaselineOffset = 10.dp
private val TextFieldTopPadding = 4.dp
const val ContainerAlpha = 0.12f