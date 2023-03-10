package com.example.notification.Notification;

import static com.example.notification.Notification.ConstentData.CONTENT_TYPE;
import static com.example.notification.Notification.ConstentData.SERVER_KEY;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public
interface ApiInterface {
    @Headers({"Authorization: key="+SERVER_KEY,"Content-Type:"+CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotification> sendNotification(@Body PushNotification pushNotification);

}
