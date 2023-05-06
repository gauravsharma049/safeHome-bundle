package com.example.safehome.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.safehome.R;

public class AlarmService extends Service {
    MediaPlayer player;
    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.alarmsound);
        player.setLooping(true);
        Log.i("message", "service created");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        player.start();
        Log.i("message", "service started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }


}