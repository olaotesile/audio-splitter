package com.audiosplitter.bridge.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

/**
 * 📱 The App Picker Info
 * This is just a small "ID card" for each app on the phone.
 */
data class AppInfo(
    val name: String,
    val packageName: String,
    val uid: Int
)

class AppUtils(private val context: Context) {
    /**
     * 🕵️‍♂️ Finding all installed apps
     * This searches the phone's "Filing Cabinet" (PackageManager) for all apps.
     */
    fun getInstalledApps(): List<AppInfo> {
        val pm = context.packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        
        return apps
            .filter { (it.flags and ApplicationInfo.FLAG_SYSTEM) == 0 } // Only apps user installed
            .map { app ->
                AppInfo(
                    name = pm.getApplicationLabel(app).toString(),
                    packageName = app.packageName,
                    uid = app.uid
                )
            }
            .sortedBy { it.name }
    }
}
