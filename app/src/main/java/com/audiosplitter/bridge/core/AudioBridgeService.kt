package com.audiosplitter.bridge.core

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import androidx.core.app.NotificationCompat

/**
 * 🚀 The "Worker" (AudioBridgeService)
 * 
 * This service stays alive even when the user leaves the app. 
 * it holds the "Golden Ticket" (MediaProjection) and manages our "Sensor".
 */
class AudioBridgeService : Service() {

    private var mediaProjection: MediaProjection? = null
    private var captureProcessor: AudioCaptureProcessor? = null
    private val CHANNEL_ID = "audio_bridge_service_channel"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val resultCode = intent?.getIntExtra("RESULT_CODE", -1) ?: -1
        val resultData = intent?.getParcelableExtra<Intent>("RESULT_DATA")

        if (resultCode != -1 && resultData != null) {
            setupForeground()
            startBridge(resultCode, resultData)
        }

        return START_STICKY
    }

    private fun setupForeground() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "AudioBridge Active",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("AudioBridge is Active")
            .setContentText("Routing Spotify to Bluetooth Speaker")
            .setSmallIcon(android.R.drawable.ic_media_play) // Standard system play icon
            .build()

        startForeground(1, notification)
    }

    private fun startBridge(resultCode: Int, resultData: Intent) {
        val projectionManager = getSystemService(MediaProjectionManager::class.java)
        mediaProjection = projectionManager.getMediaProjection(resultCode, resultData)
        
        mediaProjection?.let { projection ->
            captureProcessor = AudioCaptureProcessor(projection)
            // For now, we'll use a placeholder UID
            // We'll add the "Select App" logic to get real UIDs in Phase 1 polish
            // bridgeProcessor?.startCapture(10085) // Example Spotify UID
        }
    }

    override fun onDestroy() {
        captureProcessor?.stopCapture()
        mediaProjection?.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
