package com.example.safehome.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmHelper{
    Intent intent;
    public AlarmHelper(Context context){
        intent = new Intent(context, AlarmService.class);
    }
    public void playAlarm(Context context){
        context.startService(intent);
    }
    public void stopAlarm(Context context){
        context.stopService(intent);
    }
}
