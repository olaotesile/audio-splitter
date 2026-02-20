package com.audiosplitter.bridge.core

import android.media.*
import android.os.Build

/**
 * 🚿 The "Pipe" (AudioRoutingEngine)
 * 
 * This is the private pipe that sends audio directly to our target hardware. 
 */
class AudioRoutingEngine {
    private var audioTrack: AudioTrack? = null
    private val sampleRate = 44100

    fun startRouting() {
        // 🧪 Phase 1: Create the Private Pipe
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        val audioFormat = AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(sampleRate)
            .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
            .build()

        val bufferSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        audioTrack = AudioTrack.Builder()
            .setAudioAttributes(audioAttributes)
            .setAudioFormat(audioFormat)
            .setBufferSizeInBytes(bufferSize)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()

        audioTrack?.play()
    }

    /**
     * 🛰️ Binding to the Hardware
     * This is the magic "Diversion" command.
     */
    fun routeToDevice(device: AudioDeviceInfo?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioTrack?.preferredDevice = device
        }
    }

    /**
     * 🌊 Pouring the "Water" into the Pipe
     */
    fun write(buffer: ShortArray, size: Int): Int {
        return audioTrack?.write(buffer, 0, size) ?: 0
    }

    fun stopRouting() {
        audioTrack?.stop()
        audioTrack?.release()
        audioTrack = null
    }
}
