package com.bobomee.binderpool

import android.app.Application
import com.bobomee.connect.ServiceTools

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceTools.init(
            this,
            pkg = { "com.bobomee.server" },
            act = { "com.bobomee.server.service.MainService" })
    }


}