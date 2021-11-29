package com.bobomee.binderpool

import android.app.Application
import com.bobomee.connect.ServiceTools

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceTools.pckage { "com.bobomee.server" }
            .activity({ "com.bobomee.server.activity.TranslucentActivity" })
            .service({ "com.bobomee.server.service.MainService" }).init(this)
    }


}