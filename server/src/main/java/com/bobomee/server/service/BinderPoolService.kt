package com.bobomee.server.service

import android.content.Context
import android.os.IBinder
import com.bobomee.server.IBinderPool
import com.bobomee.server.service.manager.BookManagerService
import com.bobomee.server.service.manager.MediaPlayerManagerService

class BinderPoolService(private val context: Context) : IBinderPool.Stub() {

    override fun queryBinder(tag: String): IBinder? {
        return when (tag) {
            ServiceTag.BOOK -> BookManagerService()
            ServiceTag.MEDIA -> MediaPlayerManagerService(context)
            else -> null
        }
    }
}