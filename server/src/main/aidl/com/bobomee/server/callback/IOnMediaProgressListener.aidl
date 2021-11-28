// IOnMediaProgressListener.aidl
package com.bobomee.server.callback;

// Declare any non-default types here with import statements

interface IOnMediaProgressListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void progress(float firstProgress, float secondaryProgress);
}
