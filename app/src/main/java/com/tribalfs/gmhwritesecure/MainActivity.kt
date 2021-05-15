package com.tribalfs.gmhwritesecure

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grantSecPerm()
    }


    private fun grantSecPerm() {
        listOf("com.tribalfs.gmh", "com.tribalfs.n20umaxhz").forEach {
            if (isPackageInstalled(it)) {
                val command = "pm grant $it android.permission.WRITE_SECURE_SETTINGS"
                findViewById<TextView>(R.id.tvHw).text = Superuser.executeCommandSU(command)
                        ?: "$command successfully executed"
            }
        }
    }

    private fun isPackageInstalled(pkg: String): Boolean {
        return try {
            packageManager.getApplicationInfo(pkg, 0)
            true
        } catch (_: Exception) {
            false
        }
    }
}