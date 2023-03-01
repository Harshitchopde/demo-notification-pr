package com.example.notification.Notification;

import com.google.gson.annotations.SerializedName;

public
class PushNotification {
    @SerializedName("notification")
    private NotificationDataModel Notification;
    @SerializedName("token")
    private String to;
public PushNotification(){}
    public
    PushNotification(NotificationDataModel notification, String to) {
        Notification = notification;
        this.to = to;
    }

    public
    NotificationDataModel getNotification() {
        return Notification;
    }

    public
    void setNotification(NotificationDataModel notification) {
        Notification = notification;
    }

    public
    String getTo() {
        return to;
    }

    public
    void setTo(String to) {
        this.to = to;
    }
}
