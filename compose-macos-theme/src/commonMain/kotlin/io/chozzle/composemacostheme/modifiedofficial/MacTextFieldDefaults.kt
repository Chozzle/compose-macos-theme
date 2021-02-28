package io.chozzle.composemacostheme.modifiedofficial

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import io.chozzle.composemacostheme.MacTheme
import kotlinx.coroutines.flow.collect


object MacTextFieldDefaults {

    /**
     * Creates a [TextFieldColors] that represents the default input text, background and content
     * (including label, placeholder, leading and trailing icons) colors used in an
     * [OutlinedTextField].
     */
    @Composable
    fun outlinedTextFieldColors(
        textColor: Color = LocalContentColor.current.copy(LocalContentAlpha.current),
        disabledTextColor: Color = textColor.copy(ContentAlpha.disabled),
        backgroundColor: Color = Color.Transparent,
        cursorColor: Color = MaterialTheme.colors.onSurface,
        errorCursorColor: Color = MaterialTheme.colors.error,
        focusedBorderColor: Color =
            MacTheme.colors.highlight,
        unfocusedBorderColor: Color =
            MacTheme.colors.border.copy(alpha = ContentAlpha.disabled),
        disabledBorderColor: Color = unfocusedBorderColor.copy(alpha = ContentAlpha.disabled),
        errorBorderColor: Color = MaterialTheme.colors.error,
        leadingIconColor: Color =
            MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
        disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
        errorLeadingIconColor: Color = leadingIconColor,
        trailingIconColor: Color =
            MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
        disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
        errorTrailingIconColor: Color = MaterialTheme.colors.error,
        focusedLabelColor: Color =
            MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
        unfocusedLabelColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
        disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled),
        errorLabelColor: Color = MaterialTheme.colors.error,
        placeholderColor: Color = MacTheme.colors.surfaceBorder,
        disabledPlaceholderColor: Color = placeholderColor.copy(ContentAlpha.disabled)
    ): TextFieldColors =
        DefaultTextFieldColors(
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            focusedIndicatorColor = focusedBorderColor,
            unfocusedIndicatorColor = unfocusedBorderColor,
            errorIndicatorColor = errorBorderColor,
            disabledIndicatorColor = disabledBorderColor,
            leadingIconColor = leadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            errorLeadingIconColor = errorLeadingIconColor,
            trailingIconColor = trailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            errorTrailingIconColor = errorTrailingIconColor,
            backgroundColor = backgroundColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,
            placeholderColor = placeholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor
        )
}

@Immutable
private class DefaultTextFieldColors(
    private val textColor: Color,
    private val disabledTextColor: Color,
    private val cursorColor: Color,
    private val errorCursorColor: Color,
    private val focusedIndicatorColor: Color,
    private val unfocusedIndicatorColor: Color,
    private val errorIndicatorColor: Color,
    private val disabledIndicatorColor: Color,
    private val leadingIconColor: Color,
    private val disabledLeadingIconColor: Color,
    private val errorLeadingIconColor: Color,
    private val trailingIconColor: Color,
    private val disabledTrailingIconColor: Color,
    private val errorTrailingIconColor: Color,
    private val backgroundColor: Color,
    private val focusedLabelColor: Color,
    private val unfocusedLabelColor: Color,
    private val disabledLabelColor: Color,
    private val errorLabelColor: Color,
    private val placeholderColor: Color,
    private val disabledPlaceholderColor: Color
) : TextFieldColors {

    @Composable
    override fun leadingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledLeadingIconColor
                isError -> errorLeadingIconColor
                else -> leadingIconColor
            }
        )
    }

    @Composable
    override fun trailingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledTrailingIconColor
                isError -> errorTrailingIconColor
                else -> trailingIconColor
            }
        )
    }

    @Composable
    override fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val interactions = remember { mutableStateListOf<Interaction>() }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is FocusInteraction.Focus -> {
                        interactions.add(interaction)
                    }
                    is FocusInteraction.Unfocus -> {
                        interactions.remove(interaction.focus)
                    }
                }
            }
        }
        val interaction = interactions.lastOrNull()

        val targetValue = when {
            !enabled -> disabledIndicatorColor
            isError -> errorIndicatorColor
            interaction is FocusInteraction.Focus -> focusedIndicatorColor
            else -> unfocusedIndicatorColor
        }
        return if (enabled) {
            // TODO: MacTheme: Tried keyframe animation here to start focus animation at 0% alpha, but there always
            // seems to be 1 frame of of 100% alpha which ruins animation
            animateColorAsState(targetValue, tween(durationMillis = AnimationDuration))
        } else {
            rememberUpdatedState(targetValue)
        }
    }

    @Composable
    private fun animateMacFlash(targetValue: Color) = animateColorAsState(
        targetValue, keyframes {
            durationMillis = AnimationDuration
            targetValue.copy(alpha = 0f) at 0 with FastOutLinearInEasing
        }
    )

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(backgroundColor)
    }

    @Composable
    override fun placeholderColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) placeholderColor else disabledPlaceholderColor)
    }

    @Composable
    override fun labelColor(
        enabled: Boolean,
        error: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val interactions = remember { mutableStateListOf<Interaction>() }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is FocusInteraction.Focus -> {
                        interactions.add(interaction)
                    }
                    is FocusInteraction.Unfocus -> {
                        interactions.remove(interaction.focus)
                    }
                }
            }
        }
        val interaction = interactions.lastOrNull()

        val targetValue = when {
            !enabled -> disabledLabelColor
            error -> errorLabelColor
            interaction is FocusInteraction.Focus -> focusedLabelColor
            else -> unfocusedLabelColor
        }
        return if (enabled) {
            animateColorAsState(targetValue, tween(durationMillis = AnimationDuration))
        } else {
            rememberUpdatedState(targetValue)
        }
    }

    @Composable
    override fun textColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) textColor else disabledTextColor)
    }

    @Composable
    override fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultTextFieldColors

        if (textColor != other.textColor) return false
        if (disabledTextColor != other.disabledTextColor) return false
        if (cursorColor != other.cursorColor) return false
        if (errorCursorColor != other.errorCursorColor) return false
        if (focusedIndicatorColor != other.focusedIndicatorColor) return false
        if (unfocusedIndicatorColor != other.unfocusedIndicatorColor) return false
        if (errorIndicatorColor != other.errorIndicatorColor) return false
        if (disabledIndicatorColor != other.disabledIndicatorColor) return false
        if (leadingIconColor != other.leadingIconColor) return false
        if (disabledLeadingIconColor != other.disabledLeadingIconColor) return false
        if (errorLeadingIconColor != other.errorLeadingIconColor) return false
        if (trailingIconColor != other.trailingIconColor) return false
        if (disabledTrailingIconColor != other.disabledTrailingIconColor) return false
        if (errorTrailingIconColor != other.errorTrailingIconColor) return false
        if (backgroundColor != other.backgroundColor) return false
        if (focusedLabelColor != other.focusedLabelColor) return false
        if (unfocusedLabelColor != other.unfocusedLabelColor) return false
        if (disabledLabelColor != other.disabledLabelColor) return false
        if (errorLabelColor != other.errorLabelColor) return false
        if (placeholderColor != other.placeholderColor) return false
        if (disabledPlaceholderColor != other.disabledPlaceholderColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = textColor.hashCode()
        result = 31 * result + disabledTextColor.hashCode()
        result = 31 * result + cursorColor.hashCode()
        result = 31 * result + errorCursorColor.hashCode()
        result = 31 * result + focusedIndicatorColor.hashCode()
        result = 31 * result + unfocusedIndicatorColor.hashCode()
        result = 31 * result + errorIndicatorColor.hashCode()
        result = 31 * result + disabledIndicatorColor.hashCode()
        result = 31 * result + leadingIconColor.hashCode()
        result = 31 * result + disabledLeadingIconColor.hashCode()
        result = 31 * result + errorLeadingIconColor.hashCode()
        result = 31 * result + trailingIconColor.hashCode()
        result = 31 * result + disabledTrailingIconColor.hashCode()
        result = 31 * result + errorTrailingIconColor.hashCode()
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + focusedLabelColor.hashCode()
        result = 31 * result + unfocusedLabelColor.hashCode()
        result = 31 * result + disabledLabelColor.hashCode()
        result = 31 * result + errorLabelColor.hashCode()
        result = 31 * result + placeholderColor.hashCode()
        result = 31 * result + disabledPlaceholderColor.hashCode()
        return result
    }
}