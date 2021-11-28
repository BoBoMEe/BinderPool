package com.bobomee.server.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MainService : Service() {

    private val mBinder by lazy { BinderPoolService(this) }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}
