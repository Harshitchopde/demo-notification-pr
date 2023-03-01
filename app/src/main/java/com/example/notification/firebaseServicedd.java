package com.example.notification;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public
class firebaseServicedd extends FirebaseMessagingService {

    @Override
    public
    void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Map<String, String> map = remoteMessage.getData();


            String title = map.get("title");
            String message = map.get("message");


            Log.d("TAG", "onMessageReceived: Chat Notification");


            Log.d("TAG", "onMessageReceived: chatID is " + title + "\n hisID" + message);

        }
        super.onMessageReceived(remoteMessage);
    }
}

