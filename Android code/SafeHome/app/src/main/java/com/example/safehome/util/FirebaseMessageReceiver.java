package com.example.safehome.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.safehome.R;
import com.example.safehome.SwipeUpDetectorActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessageReceiver extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("messagefcm", "From: " + remoteMessage.getFrom());

        if (remoteMessage.getNotification() != null) {
            Log.i("messagefcm", "Message Notification Body: " + remoteMessage.getNotification().getBody());

            RemoteMessage.Notification notification = remoteMessage.getNotification();
            NotificationHelper notificationHelper = new NotificationHelper(this);
            notificationHelper.sendNotification(notification.getTitle(), notification.getBody(), R.drawable.safehome);

            startService(new Intent(getApplicationContext(), AlarmService.class));
            startActivity(new Intent(getApplicationContext(), SwipeUpDetectorActivity.class));
//            new AlarmHelper(getApplicationContext()).playAlarm(getApplicationContext());
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.i("messagefcm", "Refreshed token: " + token);

        // Send the token to your backend server
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send the token to your server
    }
}
