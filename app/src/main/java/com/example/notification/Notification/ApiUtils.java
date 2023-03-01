package com.example.notification.Notification;

import static com.example.notification.Notification.ConstentData.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public
class ApiUtils {
    public static
    Retrofit retrofit =null;
    public static ApiInterface getClients(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
