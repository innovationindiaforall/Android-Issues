package com.issues.android

import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    //----------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        check_My_Android_Mobile_Developer_Mode_Status()

        //check_My_USB_Debug_Mode_Status()

        //check_My_WireLess_Debug_Mode_Status()
    }

    //----------------------------------------------------------------------------------------------

    private fun check_My_Android_Mobile_Developer_Mode_Status() {

        if (Settings.Secure.getInt(this.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
                0) == 1
        ) {
            // debugging enabled
            Toast.makeText(this,
                "Developer Mode enabled: Show Alert user to Security Thread",
                Toast.LENGTH_LONG).show()

            check_My_USB_Debug_Mode_Status()
            check_My_WireLess_Debug_Mode_Status()

        } else {
            //;debugging does not enabled
            Toast.makeText(this, "Developer Mode Disabled:Running Safe Mode", Toast.LENGTH_LONG)
                .show()
        }
    }
    //----------------------------------------------------------------------------------------------
    private fun check_My_USB_Debug_Mode_Status() {

        val adb: Int = Settings.Secure.getInt(this.contentResolver, Settings.Global.ADB_ENABLED, 0)

        if (adb == 1)
            Toast.makeText(this, "USB Debug Mode:ENABLED", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "USB Debug Mode:DISABLED", Toast.LENGTH_LONG).show()

    }
    //----------------------------------------------------------------------------------------------
    private fun check_My_WireLess_Debug_Mode_Status() {

        if (isWifiAdbEnabled())
            Toast.makeText(this, "WireLess Debug Mode: Enabled", Toast.LENGTH_LONG).show()
        else {
            Toast.makeText(this, "WireLess Debug Mode: Disabled", Toast.LENGTH_LONG).show()
        }
    }

    //Settings.Global.ADB_WIFI_ENABLED = "adb_wifi_enabled"
    private fun isWifiAdbEnabled(): Boolean {
        // "this" is your activity or context
        return Settings.Global.getInt(this.contentResolver, "adb_wifi_enabled", 0) != 0
    }

    //----------------------------------------------------------------------------------------------
}