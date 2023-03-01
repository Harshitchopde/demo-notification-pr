package com.example.notification;

import static android.content.ContentValues.TAG;

import static com.example.notification.Notification.ConstentData.TO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notification.Notification.ApiUtils;
import com.example.notification.Notification.NotificationDataModel;
import com.example.notification.Notification.PushNotification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class Cloudmessaging extends AppCompatActivity {
    String tokenUser;
    FirebaseAuth mAuth ;
    EditText title,body,token;
    Button subimt;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloudmessaging);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        title = findViewById(R.id.titleNotification1);
        body = findViewById(R.id.messageNotification1);
        subimt = findViewById(R.id.submitfinal);
        token= findViewById(R.id.tokenNotification1);
        FirebaseMessaging.getInstance().subscribeToTopic(TO);
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = "Subscribed";
//                        if (!task.isSuccessful()) {
//                            msg = "Subscribe failed";
//                        }
//                        Log.d(TAG, msg);
//                        Toast.makeText(Cloudmessaging.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });


        msg();
        Log.e(TAG, "onCreate: "+mAuth.getCurrentUser().getEmail());

//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if(task.isComplete()){
//                    tokenUser = task.getResult();
//                    Log.e("AppConstants", "onComplete: new Token got: "+tokenUser );
//                    FirebaseDatabase.getInstance().getReference("Users/"+mAuth.getCurrentUser().getUid()).child("token").setValue(tokenUser)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public
//                                void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Log.e(TAG, "onComplete: successfull  ");
//                                    }
//                                    else {
//                                        Log.e(TAG, "onComplete:  not successfull " );
//                                    }
//                                }
//                            });
//
//
//                }
//            }
//        });



        subimt.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                String titletxt = title.toString();
                String bodytxt = body.toString();
                String tokenvalue ="d9BT1O6ASYmeYoqEH12UlU:APA91bEAuVosE66gb0SzxbGEHq8BijbtYYQKtL8m5W11--49ON46RsSZhLv9KG-NE2UoELLcJyPlIQ_m8SS_CpeRdsdZarlDKBaZuFS0FfgdGfcVuV-X_6llnfLoDu6DneUdx5iURbbS";

                sendmessage(tokenvalue,titletxt,bodytxt);

            }
        });


    }

    private
    void getopponentToken() {

    }

    private
    void sendmessage(String tokenval, String titletxt, String bodytxt) {
        NotificationDataModel dataModel = new NotificationDataModel(titletxt,bodytxt);
        PushNotification pushNotification =new PushNotification(dataModel,TO);
        ApiUtils.getClients().sendNotification(pushNotification)
                .enqueue(new Callback<PushNotification>() {
                    @Override
                    public
                    void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(Cloudmessaging.this, "send Notification successFull", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onResponse: "+response.message());
                            Log.d(TAG, "onResponse: response is being send "+response.message());
                        }
                        else {
                            Toast.makeText(Cloudmessaging.this, "faillure in on response", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onResponse:failure  "+response.code() );
                            Log.e(TAG, "onResponse:failure to delever the message  "+response.body() );
                        }
                    }

                    @Override
                    public
                    void onFailure(Call<PushNotification> call, Throwable t) {

                    }
                });

    }
//    private void sendNotificationToPatner(String token) {
//
//        NotificationDataModel notificationDataModel = new NotificationDataModel("fsdf","dfdf");
//        Requestnotification requestNotificaton = new Requestnotification();
//        requestNotificaton.setSendNotificationModel(notificationDataModel);
//        //token is id , whom you want to send notification ,
//        requestNotificaton.setToken(token);
//
//        ApiClient.getClient().create(ApiInterface.class);
//        retrofit2.Call<ResponseBody> responseBodyCall = apiService.sendChatNotification(requestNotificaton);
//
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                Log.d("kkkk","done");
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
//    }


//    private void sendNotificationToPatner(String s, String titletxt, String bodytxt) {
//        NotificationDataModel notificationDataModel =new NotificationDataModel("yes","success full");
//        PushNotification pushNotification=new PushNotification();
//      pushNotification.setNotification(notificationDataModel);
//        //token is id , whom you want to send notification
//        // ,
//        Log.e(TAG, "sendNotificationToPatner: "+s );
//        pushNotification.setTo(s);
//
//        ApiUtils.getClients().sendNotification(pushNotification)
//                .enqueue(new Callback<PushNotification>() {
//                    @Override
//                    public
//                    void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
//                        if (response.isSuccessful()){
//                            Toast.makeText(Cloudmessaging.this, "successfull", Toast.LENGTH_SHORT).show();
//                            Log.e(TAG, "onResponse: successfull" );
//                        }
//                        else {
//                            Toast.makeText(Cloudmessaging.this, "fail", Toast.LENGTH_SHORT).show();
//                            Log.e(TAG, "onResponse: failuerfull "+response.message() );
//
//                        }
//                    }
//
//                    @Override
//                    public
//                    void onFailure(Call<PushNotification> call, Throwable t) {
//                        Toast.makeText(Cloudmessaging.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "onResponse: error 1111" );
//
//
//                    }
//                });
//    }

//    private
//    void sendMSG(String s, String titletxt, String bodytxt) {
//        Log.e(TAG, "sendMSG: "+s );
//        ApiUtils.getClients().sendNotification(new PushNotification(new NotificationDataModel(titletxt,bodytxt),s))
//                .enqueue(new Callback<PushNotification>() {
//                    @Override
//                    public
//                    void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
//                        if (response.isSuccessful()){
//                            Toast.makeText(Cloudmessaging.this, "successfull", Toast.LENGTH_SHORT).show();
//                            Log.e(TAG, "onResponse: successfull" );
//                        }
//                        else {
//                            Toast.makeText(Cloudmessaging.this, "fail", Toast.LENGTH_SHORT).show();
//                            Log.e(TAG, "onResponse: failuerfull "+response.message() );
//
//                        }
//                    }
//
//                    @Override
//                    public
//                    void onFailure(Call<PushNotification> call, Throwable t) {
//                        Toast.makeText(Cloudmessaging.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "onResponse: error 1111" );
//
//
//                    }
//                });
//    }

    private
    void msg() {
        if (mAuth.getCurrentUser()==null){
            Log.e(TAG, "msg: sign anonymously in the app for notification" );
            mAuth.createUserWithEmailAndPassword("chopdehsitdfkj@gmail.com","123456ddd").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public
                void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.e(TAG, "onComplete: success" );
                        return;
                    }
                    Log.e(TAG, "onSuccess: dddd "+task.getException().toString() );
                    Toast.makeText(Cloudmessaging.this, "create user succesfull", Toast.LENGTH_SHORT).show();


                }
//
//
//
            });
//
        }


    }

}
