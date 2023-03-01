package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public
class MainActivity extends AppCompatActivity {

    private static final String CHHANEL = "My App";
    private static final int ID = 100;
    private static final int REQUEST_CODE = 101;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable =  ResourcesCompat.getDrawable(getResources(),R.drawable.green,null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        // Pending intent it will execute when user will click on it

//             intent getApplication context to mainactivity
            Intent iNotify = new Intent(getApplicationContext(),MainActivity.class);
            //below is do if intent activitu is already is in the stack than it will clear the top all activity
            iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // i made that setFlags that is why my app crash

            PendingIntent pi = PendingIntent.getActivity(this,REQUEST_CODE,iNotify,PendingIntent.FLAG_IMMUTABLE);//FLAG_UPDATE_CURRENT -> will update the activity it will prvent to create multiplt instance of the same


            // Big picture style
//        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
//                .bigPicture(((BitmapDrawable)  ResourcesCompat.getDrawable(getResources(),R.drawable.join,null)).getBitmap())
//                .setBigContentTitle("View img")
//                .bigLargeIcon(largeIcon)
//                .setSummaryText("Image Message");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.join)
                    .setLargeIcon(largeIcon)
                    .setContentText("Message new")
//                    .setStyle(bigPictureStyle)
                    .setContentIntent(pi)
                    .setSubText("New message from _Harshit ")
                    .setChannelId(CHHANEL)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHHANEL, "My app", NotificationManager.IMPORTANCE_HIGH));
        }
        else {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.join)
                    .setLargeIcon(largeIcon)
                    .setContentText("Message new")
                    .setSubText("New message from Aman ")
                    .build();


        }
        nm.notify(ID,notification);


    }
}