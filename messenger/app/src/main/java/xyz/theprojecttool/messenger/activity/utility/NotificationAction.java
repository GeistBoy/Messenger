package xyz.theprojecttool.messenger.activity.utility;

import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import xyz.theprojecttool.messenger.R;

public class NotificationAction {

    private static final String TAG = "test";
    private static int counter = 0;
    public static String channelIdWechat = "MessengerChannel_1";

    // Send notification
    public static void sendNotification(Context context, String contentTitle, String contentText){
        Log.e(TAG, "sentNotification");

        // Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelIdWechat)
                .setSmallIcon(R.drawable.smallicon)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
        Log.e(TAG, "sentNotification done");
    }
}
