package com.audiosplitter.bridge.core

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioPlaybackCaptureConfiguration
import android.media.AudioRecord
import android.media.projection.MediaProjection

/**
 * 📡 The "Sensor" (AudioCaptureProcessor)
 * 
 * This is the part of the app that acts like a sensor in the "Audio River".
 * It uses the "Golden Ticket" (MediaProjection) to listen to specific apps.
 */
class AudioCaptureProcessor(
    private val mediaProjection: MediaProjection
) {
    private var audioRecord: AudioRecord? = null
    private val sampleRate = 44100 // CD quality audio
    
    @SuppressLint("MissingPermission")
    fun startCapture(targetUid: Int) {
        // 🎫 Phase 1: Configure the "Selective Hearing"
        // We tell Android exactly which app we want to hear.
        val config = AudioPlaybackCaptureConfiguration.Builder(mediaProjection)
            .addMatchingUid(targetUid) // "Only give me audio from Spotify"
            .build()

        // 🏗️ Phase 2: Set the audio quality standards
        val audioFormat = AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(sampleRate)
            .setChannelMask(AudioFormat.CHANNEL_IN_STEREO)
            .build()

        // 📡 Phase 3: Initialize the Sensor (AudioRecord)
        val minBufferSize = AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        audioRecord = AudioRecord.Builder()
            .setAudioPlaybackCaptureConfig(config)
            .setAudioFormat(audioFormat)
            .setBufferSizeInBytes(minBufferSize * 2) // Extra room for the "Bucket"
            .build()

        audioRecord?.startRecording()
    }

    fun stopCapture() {
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }

    /**
     * 🪣 Reading the "Water" into the Bucket
     * This function pulls the raw data out so we can send it to the private pipe later.
     */
    fun read(buffer: ShortArray, size: Int): Int {
        return audioRecord?.read(buffer, 0, size) ?: 0
    }
}
