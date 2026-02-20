package com.audiosplitter.bridge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// 💡 The "Lighting System" (Theme)
// This tells every screen in the app: "Use these colors by default"

private val DarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    secondary = ElectricPurple,
    tertiary = GlassWhite,
    background = DeepSpace,
    surface = DeepSpace,
    onPrimary = PureWhite,
    onSecondary = PureWhite,
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
