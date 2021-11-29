package com.bobomee.server.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.bobomee.server.NotificationHelper

class MainService : Service() {

    private val mBinder by lazy { BinderPoolService(this) }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.initHelper(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNotification()
        return START_STICKY_COMPATIBILITY
    }

    //绑定通知栏
    private fun bindNotification() {
        startForeground(
            1000,
            NotificationHelper.bindVoiceService()
        )
    }
}
