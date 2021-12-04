package io.chozzle.composenativetheme

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun NativeExampleView() {

    var currentDisplayingTheme: NativeTheme by remember { mutableStateOf(Mac) }

    LaunchedEffect(currentDisplayingTheme) {
        while (true) {
            delay(2000)

            currentDisplayingTheme = when (currentDisplayingTheme) {
                Mac -> Windows
                Windows -> Material
                Material -> Mac
            }
        }
    }

    Crossfade(
        targetState = currentDisplayingTheme,
        animationSpec = TweenSpec(1000, easing = FastOutLinearInEasing)
    ) { screen ->
        CompositionLocalProvider(
            LocalTheme provides screen,
        ) {
            exampleContent()
        }
    }
}

@Composable
fun exampleContent() {
    NativeTheme {
        Column(
            Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ButtonsView()

            Spacer(Modifier.height(8.dp))

            Text(
                "Please feel free to add more components yourself. It's fun and easy with Compose 🤩",
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ButtonsView() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(
            onClick = {},
            nativeButtonStyle = NativeButtonStyle.Secondary
        ) {
            Text("Secondary")
        }

        Button(
            enabled = false,
            onClick = {},
            nativeButtonStyle = NativeButtonStyle.Secondary
        ) {
            Text("Disabled")
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(
            onClick = {},
            colors = NativeButtonDefaults.accentColors
        ) {
            Text("Primary")
        }

        Button(
            enabled = false,
            onClick = {},
            colors = NativeButtonDefaults.accentColors
        ) {
            Text("Disabled")
        }
    }
}
