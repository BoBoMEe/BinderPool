// IMediaPlayerManager.aidl
package com.bobomee.server.manager;

// Declare any non-default types here with import statements
import com.bobomee.server.callback.IOnMediaProgressListener;


interface IMediaPlayerManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void reset(String path);

    void play();

    void pause();

    boolean isPlaying();

    void seekTo(long ms);

    void registerProgress(IOnMediaProgressListener listener);

    void release();
}
