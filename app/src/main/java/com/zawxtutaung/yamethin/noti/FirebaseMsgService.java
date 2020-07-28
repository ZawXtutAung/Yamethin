package com.zawxtutaung.yamethin.noti;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.zawxtutaung.yamethin.MainActivity;
import com.zawxtutaung.yamethin.R;

/**
 * Created by zawxtutaung on 12/28/2016.
 */

public class FirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "MyAndroidFCMService";

    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        createNotification(remoteMessage.getNotification().getBody(),remoteMessage.getTtl());


    }

    long when =System.currentTimeMillis();

    private void createNotification(String body,int mylong) {
        int time =mylong;
        Intent intent = new Intent( this ,MainActivity. class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(this ,0, intent,
                PendingIntent.FLAG_ONE_SHOT);


        Uri notificationSoundURI= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationCompatbuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ymtmain)
                .setContentTitle("Yamethin")
                .setContentText(body)
                .setAutoCancel( true )
                .setWhen(when)
                .setSound(notificationSoundURI)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mNotificationCompatbuilder.build());

    }


}
