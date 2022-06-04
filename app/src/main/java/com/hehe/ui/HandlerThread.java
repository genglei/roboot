package com.hehe.ui;

import android.os.Looper;

public class HandlerThread extends Thread {

    private Looper mLooper;

    @Override
    public void run() {
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
        }
        Looper.loop();
    }

    public Looper getLooper() throws Exception {
        if (!isAlive()) {
            throw new Exception("current thread is not alive");
        }
        if (mLooper == null) {
            throw new Exception("current thread is not start");
        }
        return mLooper;
    }
}


