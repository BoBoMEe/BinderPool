package com.bobomee.server.activity

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TranslucentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startServiceWrapper()
        finish()
    }

    private fun startServiceWrapper() {
        val intent = Intent()
        val servicePkg = "com.bobomee.server"
        val serviceClz = "com.bobomee.server.service.MainService"
        intent.component = ComponentName(servicePkg,serviceClz)
        intent.action = "com.bobomee.server.service.MainService"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}