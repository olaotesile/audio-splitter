package com.audiosplitter.bridge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*

// 🛋️ The "Living Room" (Home Dashboard)
// This is where we arrange all our "furniture" (the buttons, sliders, and cards).
// For now, it's just a placeholder so we can see the "Interior Design" come together.

@Composable
fun HomeDashboard() {
    var bridgeVolume by remember { mutableFloatStateOf(0.7f) }
    var localVolume by remember { mutableFloatStateOf(0.5f) }

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
            color = MaterialTheme.colorScheme.primary // This is now our Forest Green!
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔌 The "Source" Card (Where the music comes from)
        RoutingCard(
            title = "SOURCE APP",
            name = "Spotify",
            icon = "�", // Placeholder icon
            onAction = { /* We'll handle "Change App" later */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔊 The "Destination" Card (Where the music goes)
        RoutingCard(
            title = "OUTPUT DEVICE",
            name = "Bluetooth Speaker",
            icon = "🔊", // Placeholder icon
            onAction = { /* We'll handle "Select Device" later */ }
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 🎚️ The "Live Mixer" Section
        Text(
            text = "LIVE MIXER",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bridge Slider
            MixerSlider(
                label = "BRIDGE",
                value = bridgeVolume,
                onValueChange = { bridgeVolume = it },
                modifier = Modifier.weight(1f),
                activeColor = MaterialTheme.colorScheme.primary
            )

            // Local Slider
            MixerSlider(
                label = "LOCAL",
                value = localVolume,
                onValueChange = { localVolume = it },
                modifier = Modifier.weight(1f),
                activeColor = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 〰️ The Visualizer Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "WAVEFORM VISUALIZER",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 🏗️ The "Bridge" Switch
        // For now, it's a big button that will eventually trigger the Golden Ticket.
        Button(
            onClick = { /* Start Bridge */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("START AUDIO BRIDGE")
        }
    }
}

// 📦 Reusable Routing Card
@Composable
fun RoutingCard(
    title: String,
    name: String,
    icon: String,
    onAction: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary // Our "Glass" layer
        )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 🖼️ The Icon (e.g., Spotify logo or Speaker icon)
            Text(text = icon, style = MaterialTheme.typography.headlineMedium)
            
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            // 🖱️ The "Change" Button
            TextButton(onClick = onAction) {
                Text("CHANGE", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

// 🎚️ Custom Mixer Slider
@Composable
fun MixerSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    activeColor: androidx.compose.ui.graphics.Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                thumbColor = activeColor,
                activeTrackColor = activeColor,
                inactiveTrackColor = activeColor.copy(alpha = 0.2f)
            )
        )
        Text(
            text = "${(value * 100).toInt()}%",
            style = MaterialTheme.typography.labelMedium,
            color = activeColor
        )
    }
}
