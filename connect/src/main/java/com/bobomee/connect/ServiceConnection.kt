package com.bobomee.connect

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.IInterface
import com.bobomee.server.IBinderPool
import com.bobomee.server.manager.IBookManager
import com.bobomee.server.manager.IMediaPlayerManager

class ServiceConnection {
    private var mContext: Context? = null
    private var mBinderPool: IBinderPool? = null
    private var mDestroyByUser = false
    private var mConnected = false
    private val mServiceMap by lazy { mutableMapOf<String, IInterface?>() }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mConnected = false
            mServiceMap.clear()
            if (!mDestroyByUser&& null != mContext) {
                bindService(mContext!!)
            }
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinderPool = IBinderPool.Stub.asInterface(service)
            mConnected = true
        }

    }

    fun bindService(context: Context) {
        mContext = context.applicationContext
        val intent = Intent(ServiceTools.getServiceAct())
        intent.`package` = ServiceTools.getServicePkg()
        mContext!!.bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }

    fun unbindService() {
        mDestroyByUser = true
        mContext?.unbindService(mServiceConnection)
        mContext = null
    }

    fun findService(tag: String): IInterface? {
        return if (mServiceMap.containsKey(tag)) {
            mServiceMap[tag]
        } else {
            mServiceMap[tag] = findInternal(tag)
            if (mServiceMap[tag] == null) {
                mServiceMap.remove(tag)
            } else {
                mServiceMap[tag]
            }
        }
    }

    private fun findInternal(tag: String): IInterface? {
        return when (tag) {
            ServiceTag.BOOK -> {
                IBookManager.Stub.asInterface(mBinderPool?.queryBinder(tag))
            }
            ServiceTag.MEDIA -> {
                IMediaPlayerManager.Stub.asInterface(mBinderPool?.queryBinder(tag))
            }
            else -> null
        }
    }

    fun isConnected(): Boolean {
        return mConnected
    }
}