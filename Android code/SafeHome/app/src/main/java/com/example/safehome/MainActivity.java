package com.example.safehome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.safehome.util.AlarmHelper;
import com.example.safehome.util.AlarmService;
import com.example.safehome.util.NotificationHelper;

public class MainActivity extends AppCompatActivity {
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = findViewById(R.id.cvSwipe);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SwipeUpDetectorActivity.class);
                startActivity(intent);
            }
        });
    }
    public void notify(View view){
        NotificationHelper notificationHelper = new NotificationHelper(this);
//        notificationHelper.createNotificationChannel("My Channel", "Description", NotificationManager.IMPORTANCE_HIGH);
//        notificationHelper.showNotification("My Notification", "This is my notification content.", R.drawable.safehome);
        notificationHelper.sendNotification("My Notification", "This is my notification content.", R.drawable.safehome);
    }

    public void playAlarm(View view){
        new AlarmHelper(getApplicationContext()).playAlarm(getApplicationContext());
        Log.i("message", "service started in main activity");
        startActivity(new Intent(getApplicationContext(), SwipeUpDetectorActivity.class));
    }
}
