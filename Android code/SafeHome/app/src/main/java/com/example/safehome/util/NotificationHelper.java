package com.example.safehome.util;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

//import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.safehome.R;

public class NotificationHelper {

    private static final String CHANNEL_ID = "my_channel_id";
    private static final int NOTIFICATION_ID = 1;

    private Context mContext;

    public NotificationHelper(Context context) {
        mContext = context;
    }

    public void createNotificationChannel(String channelName, String channelDescription, int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
//@DrawableRes
    public void showNotification(String title, String content,   int iconResId) {
        Bitmap iconBitmap = BitmapFactory.decodeResource(mContext.getResources(), iconResId);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(iconResId)
                .setLargeIcon(iconBitmap)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    public void sendNotification(String title, String content, int image){
        createNotificationChannel("My Channel", "Description", NotificationManager.IMPORTANCE_HIGH);
//        showNotification("My Notification", "This is my notification content.", R.drawable.safehome);
        showNotification(title, content, image);
    }
}
