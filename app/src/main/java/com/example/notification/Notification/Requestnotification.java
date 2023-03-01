package com.example.notification.Notification;

import com.google.gson.annotations.SerializedName;

public
class Requestnotification {

    @SerializedName("token") //  "to" changed to token
    private String token;

    @SerializedName("notification")
    private NotificationDataModel sendNotificationModel;

    public NotificationDataModel getSendNotificationModel() {
        return sendNotificationModel;
    }

    public void setSendNotificationModel(NotificationDataModel sendNotificationModel) {
        this.sendNotificationModel = sendNotificationModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
