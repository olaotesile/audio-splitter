package com.audiosplitter.bridge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 🛋️ The "Living Room" (Home Dashboard)
// This is where we arrange all our "furniture" (the buttons, sliders, and cards).
// For now, it's just a placeholder so we can see the "Interior Design" come together.

@Composable
fun HomeDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🏠 The Title "Header"
        Text(
            text = "AudioBridge",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "The Patch Bay",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary // Use our Neon Cyan glow!
        )

        Spacer(modifier = Modifier.height(48.dp))

        // 🏗️ Placeholder for the "Bridge" Switch
        // We'll build the real custom switch later in Phase 1
        Text(
            text = "[ BRIDGE TOGGLE ]",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
