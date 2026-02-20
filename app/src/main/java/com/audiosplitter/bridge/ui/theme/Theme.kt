package com.audiosplitter.bridge.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// 💡 Updated "Lighting System"
// Now using the Forest Green and Dark Gray theme.

private val DarkColorScheme = darkColorScheme(
    primary = ForestGreen,
    secondary = PureWhite,
    tertiary = GlassLayer,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = PureWhite,
    onSecondary = DarkGray,
    onBackground = PureWhite,
    onSurface = PureWhite,
)

@Composable
fun AudioBridgeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
