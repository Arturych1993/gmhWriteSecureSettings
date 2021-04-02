package com.tribalfs.gmhwritesecure

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        grantSecPerm()
    }

    private fun grantSecPerm() {
        arrayListOf("com.tribalfs.gmh", "com.tribalfs.n20umaxhz").forEach {
            if (isPackageInstalled(it)) {
                try {
                    Superuser.executeCommandSU("pm grant $it android.permission.WRITE_SECURE_SETTINGS")
                    findViewById<TextView>(R.id.tvHw).text = "WRITE_SECURE_SETTINGS successfully granted to $it app.  You can now uninstall me."
                } catch (e: Exception) {
                    e.printStackTrace()
                    findViewById<TextView>(R.id.tvHw).text =
                            "Failed to grant WRITE_SECURE_SETTINGS to $it. Ensure that this device is rooted and allow this app root permission."
                }
            }
        }
    }

    private fun isPackageInstalled(pkg: String): Boolean {
        return try {
            packageManager.getApplicationInfo(pkg, 0)
            true
        } catch (_: PackageManager.NameNotFoundException) {
            false
        }
    }
}