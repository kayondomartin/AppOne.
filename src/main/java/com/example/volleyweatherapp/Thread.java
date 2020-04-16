package com.example.volleyweatherapp;

import android.os.Handler;
import android.os.HandlerThread;

public class Thread extends HandlerThread {
    Handler handler;
    public Thread(String name, int priority) {
        super(name, priority);
    }

    @Override
    protected void onLooperPrepared() {
         handler = new Handler();

    }
}
