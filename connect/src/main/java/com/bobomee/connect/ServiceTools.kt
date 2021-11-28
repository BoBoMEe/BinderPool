package com.bobomee.connect

import android.content.Context
import android.os.IInterface

object ServiceTools {
    private var servicePkg: String = ""
    private var serviceAct: String = ""
    private val mServiceConnection by lazy { ServiceConnection() }

    fun init(context: Context, pkg: (() -> String), act: (() -> String)) {
        servicePkg = pkg.invoke()
        serviceAct = act.invoke()
        if (serviceAct.isNotEmpty() && servicePkg.isNotEmpty())
            mServiceConnection.bindService(context)
    }

    fun release() {
        mServiceConnection.unbindService()
    }

    fun <S : IInterface> findService(tag: String): S? {
        return mServiceConnection.findService(tag) as S ?: null
    }

    fun isConnected(): Boolean {
        return mServiceConnection.isConnected()
    }

    fun getServicePkg(): String {
        return servicePkg
    }

    fun getServiceAct(): String {
        return serviceAct
    }

}