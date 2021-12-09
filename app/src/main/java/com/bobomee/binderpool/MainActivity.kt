package com.bobomee.binderpool

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bobomee.connect.ServiceTag
import com.bobomee.connect.ServiceTools
import com.bobomee.server.bean.Book
import com.bobomee.server.callback.OnMediaProgressListener
import com.bobomee.server.manager.IBookManager
import com.bobomee.server.manager.IMediaPlayerManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mBookManager:IBookManager? = null

    private var mMediaManager:IMediaPlayerManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            ServiceTools.start()
        }

        bind.setOnClickListener {
            ServiceTools.bind()
        }

        getBinder.setOnClickListener {
            mBookManager = ServiceTools.findService<IBookManager>(ServiceTag.BOOK)
            mMediaManager = ServiceTools.findService<IMediaPlayerManager>(ServiceTag.MEDIA)
        }

        //测试跨进程服务通信
        addBook.setOnClickListener {
            mBookManager?.addBook(Book("热爱Android", "BGQ"))
        }
        getBooks.setOnClickListener {
            Toast.makeText(this, mBookManager?.books.toString(), Toast.LENGTH_SHORT).show()
        }

        //媒体服务测试，可前台进度
        register.setOnClickListener {
            mMediaManager?.registerProgress(object : OnMediaProgressListener() {
                override fun progress(firstProgress: Float, secondaryProgress: Float) {
                    runOnUiThread {
                        progress.progress = (1000 * firstProgress).toInt()
                        progress.secondaryProgress = (1000 * secondaryProgress).toInt()
                    }
                }
            })
        }

        //可后台息屏播放
        play.setOnClickListener {
            mMediaManager?.reset("/storage/emulated/0/netease/cloudmusic/Music/123.mp3")
            mMediaManager?.play()
        }

        pause.setOnClickListener {
            mMediaManager?.pause()
        }


    }
}
