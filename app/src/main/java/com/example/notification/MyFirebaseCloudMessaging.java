package com.example.notification;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public
class MyFirebaseCloudMessaging extends FirebaseMessagingService {
    private static final String TAG = "fgdfgsdf";

    @Override
    public
    void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "onMessageReceived: 0" );

        // Check if the message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "onMessageReceived: ttt" );

//            String title = remoteMessage.getData().get("title");
//            String message = remoteMessage.getData().get("message");
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            // Create an explicit intent for an Activity in your app
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            // Build the notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                    .setSmallIcon(R.drawable.join)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // Show the notification
            notificationManager.notify(0, builder.build());
        }

    }

    @Override
    public
    void onNewToken(@NonNull String token) {
        Log.e(TAG, "onNewToken: token genrated" );
        updateToken(token);

//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();  // depreceted
        Log.d(TAG, "Refreshed token1: " + token);
//        Log.d(TAG, "Refreshed token2: " + tokeng[0]);
        super.onNewToken(token);
    }
    private void updateToken(String token) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            databaseReference.updateChildren(map);
        }

    }


}
