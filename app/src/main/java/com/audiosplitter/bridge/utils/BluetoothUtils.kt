package com.audiosplitter.bridge.utils

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context

/**
 * 🔊 The Bluetooth Picker Info
 * A small card for each speaker or headphone found.
 */
data class DeviceInfo(
    val name: String,
    val address: String
)

class BluetoothUtils(private val context: Context) {
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

    /**
     * 🔎 Finding your speakers
     * This searches for devices you've already "Paired" or "Saved" on your phone.
     */
    @SuppressLint("MissingPermission")
    fun getPairedDevices(): List<DeviceInfo> {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        return pairedDevices?.map { device ->
            DeviceInfo(
                name = device.name ?: "Unknown Device",
                address = device.address
            )
        } ?: emptyList()
    }
}
