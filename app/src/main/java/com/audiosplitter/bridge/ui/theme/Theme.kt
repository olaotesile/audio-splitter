package com.audiosplitter.bridge.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// 💡 Premium "Obsidian" Lighting System

private val DarkColorScheme = darkColorScheme(
    primary = ForestGlow,        // Brighter green for highlights
    secondary = SilverText,      // Silver for labels
    tertiary = GlassSurface,     // Glassmorphism base
    background = Obsidian,       // True premium dark background
    surface = Gunmetal,          // Slightly lighter for cards
    onPrimary = Obsidian,        // Black text on green buttons
    onSecondary = Obsidian,
    onBackground = SilverText,
    onSurface = SilverText,
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
