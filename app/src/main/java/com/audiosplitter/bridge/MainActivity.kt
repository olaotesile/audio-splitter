package com.audiosplitter.bridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import android.content.Intent
import android.media.projection.MediaProjectionManager
import androidx.activity.result.contract.ActivityResultContracts
import com.audiosplitter.bridge.core.AudioBridgeService
import com.audiosplitter.bridge.ui.theme.AudioBridgeTheme
import com.audiosplitter.bridge.ui.HomeDashboard

// 🚪 The "Front Door" (MainActivity)
class MainActivity : ComponentActivity() {

    // 🎫 The "Golden Ticket" Booth
    // This part handles the "Allow AudioBridge to record?" pop-up
    private val projectionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            // Permission Granted! Now we tell the Worker to start.
            val intent = Intent(this, AudioBridgeService::class.java).apply {
                putExtra("RESULT_CODE", result.resultCode)
                putExtra("RESULT_DATA", result.data)
                // We'll pass the selected UID and Device here in the final version
            }
            startForegroundService(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            AudioBridgeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    HomeDashboard(onStartBridge = {
                        val manager = getSystemService(MediaProjectionManager::class.java)
                        projectionLauncher.launch(manager.createScreenCaptureIntent())
                    })
                }
            }
        }
    }
}
