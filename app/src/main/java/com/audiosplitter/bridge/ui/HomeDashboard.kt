package com.audiosplitter.bridge.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.audiosplitter.bridge.utils.*

// 🛋️ The "Living Room" (Home Dashboard)
// This is where we arrange all our "furniture" (the buttons, sliders, and cards).
// For now, it's just a placeholder so we can see the "Interior Design" come together.

@Composable
fun HomeDashboard(
    onStartBridge: () -> Unit
) {
    val context = LocalContext.current
    val appUtils = remember { AppUtils(context) }
    val bluetoothUtils = remember { BluetoothUtils(context) }

    var bridgeVolume by remember { mutableFloatStateOf(0.7f) }
    var localVolume by remember { mutableFloatStateOf(0.5f) }

    // 📱 Selection State
    var selectedApp by remember { mutableStateOf(AppInfo("Spotify", "com.spotify.music", 10085)) }
    var selectedDevice by remember { mutableStateOf(DeviceInfo("Bluetooth Speaker", "")) }
    
    var showAppPicker by remember { mutableStateOf(false) }
    var showDevicePicker by remember { mutableStateOf(false) }

    // 🌌 Premium Background Gradient
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Obsidian, Gunmetal)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🏠 The Title "Header"
        Text(
            text = "AudioBridge",
            style = MaterialTheme.typography.headlineLarge,
            color = PureWhite
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "NATIVE AUDIO ROUTING", // More premium label
            style = MaterialTheme.typography.labelSmall,
            color = ForestGlow.copy(alpha = 0.8f),
            letterSpacing = androidx.compose.ui.unit.TextUnit.Unspecified // Just a slight gap
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔌 The "Source" Card (Where the music comes from)
        RoutingCard(
            title = "SOURCE APP",
            name = selectedApp.name,
            icon = "🎵", // Placeholder icon
            onAction = { showAppPicker = true }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔊 The "Destination" Card (Where the music goes)
        RoutingCard(
            title = "OUTPUT DEVICE",
            name = selectedDevice.name,
            icon = "🔊", // Placeholder icon
            onAction = { showDevicePicker = true }
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

        Spacer(modifier = Modifier.weight(1f))

        // 🏗️ The Premium "Start" Button
        Button(
            onClick = onStartBridge,
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGlow,
                contentColor = Obsidian
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(
                "INITIALIZE AUDIO BRIDGE", 
                style = MaterialTheme.typography.titleMedium,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }

    // 📄 The Selection Dialogs
    if (showAppPicker) {
        SelectionDialog(
            title = "Select App",
            items = appUtils.getInstalledApps(),
            onDismiss = { showAppPicker = false },
            onSelect = { 
                selectedApp = it as AppInfo
                showAppPicker = false 
            },
            itemLabel = { (it as AppInfo).name }
        )
    }

    if (showDevicePicker) {
        SelectionDialog(
            title = "Select Output",
            items = bluetoothUtils.getPairedDevices(),
            onDismiss = { showDevicePicker = false },
            onSelect = { 
                selectedDevice = it as DeviceInfo
                showDevicePicker = false 
            },
            itemLabel = { (it as DeviceInfo).name }
        )
    }
}

// 📄 Reusable Selection Dialog
@Composable
fun SelectionDialog(
    title: String,
    items: List<Any>,
    onDismiss: () -> Unit,
    onSelect: (Any) -> Unit,
    itemLabel: (Any) -> String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title, color = PureWhite) },
        containerColor = Gunmetal,
        tonalElevation = 8.dp,
        text = {
            LazyColumn {
                items(items) { item ->
                    TextButton(
                        onClick = { onSelect(item) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = itemLabel(item),
                            color = SilverText,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("CLOSE", color = ForestGlow)
            }
        }
    )
}

// 📦 Premium Glass Routing Card
@Composable
fun RoutingCard(
    title: String,
    name: String,
    icon: String,
    onAction: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, GlassBorder), 
                RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = GlassSurface // Transparent "Glass"
        )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 🖼️ The Icon
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = ForestGlass,
                border = BorderStroke(1.dp, ForestGlow.copy(alpha = 0.3f))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = icon, style = MaterialTheme.typography.headlineSmall)
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MutedSilver
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = PureWhite
                )
            }

            // 🖱️ The "Change" Button
            TextButton(
                onClick = onAction,
                colors = ButtonDefaults.textButtonColors(contentColor = ForestGlow)
            ) {
                Text("SELECT", style = MaterialTheme.typography.labelLarge)
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
    activeColor: Color
) {
    Column(
        modifier = modifier
            .background(GlassSurface, RoundedCornerShape(12.dp))
            .border(BorderStroke(1.dp, GlassBorder), RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MutedSilver
        )
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = value,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                thumbColor = PureWhite,
                activeTrackColor = activeColor,
                inactiveTrackColor = Gunmetal
            )
        )
        Text(
            text = "${(value * 100).toInt()}%",
            style = MaterialTheme.typography.labelMedium,
            color = activeColor
        )
    }
}
