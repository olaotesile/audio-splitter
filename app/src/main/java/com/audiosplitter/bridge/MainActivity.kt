package com.audiosplitter.bridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.audiosplitter.bridge.ui.theme.AudioBridgeTheme
import com.audiosplitter.bridge.ui.HomeDashboard

// 🚪 The "Front Door" (MainActivity)
// When the user taps the app icon, this is the first set of instructions that runs.
// Its only job is to open the door and show our "Home Dashboard".

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // This is where we tell the phone to use our custom style and show the Dashboard
        setContent {
            AudioBridgeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    HomeDashboard()
                }
            }
        }
    }
}
