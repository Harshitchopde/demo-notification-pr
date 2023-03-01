package com.example.notification;

import static android.content.ContentValues.TAG;

import static com.example.notification.Notification.ConstentData.NOTIFICATION_URL;
import static com.example.notification.Notification.ConstentData.SERVER_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public
class MainActivityNotifiacation extends AppCompatActivity {
    EditText title,body,hisId;
    Button submit;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notifiacation);
        title = findViewById(R.id.titleNotification1);
        body = findViewById(R.id.messageNotification1);
        hisId = findViewById(R.id.tokenNotification1);
        submit = findViewById(R.id.submitfinal);
        FirebaseAuth ma =FirebaseAuth.getInstance();
        Log.e(TAG, "onCreate: "+ma.getCurrentUser() );
        submit.setOnClickListener(v -> {
            if(title.getText().toString().isEmpty()|| body.getText().toString().isEmpty()|| hisId.getText().toString().isEmpty()){
                Toast.makeText(this, "please enter the all", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onCreate:   please enter the all" );
            }
            else{
                String titleTxt = title.getText().toString();
                String message = body.getText().toString();
                String id = hisId.getText().toString();
                FirebaseDatabase.getInstance().getReference("Users/"+id).child("token")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public
                            void onDataChange(@NonNull DataSnapshot snapshot) {
                                String token= snapshot.getValue().toString();
                                Log.e(TAG, "onDataChange: "+token );
                                prepareforNotification(titleTxt,message,token);
//                                sendnotification(token);
                            }

                            @Override
                            public
                            void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });

    }

    private
    void sendnotification(String token) {
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder(token)
                .setMessageId(Integer.toString(23))
                .addData("my_message", "Hello World")
                .addData("my_action", "SAY_HELLO")
                .build());

    }

    private
    void prepareforNotification(String titleTxt, String message, String token) {
        JSONObject to =new JSONObject();
        JSONObject data =new JSONObject();
        try {
            data.put("title",titleTxt);
            data.put("message",message);
            to.put("data",data);
            to.put("to",token);
            sendNotification(to);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private
    void sendNotification(JSONObject to) {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, NOTIFICATION_URL,to, response -> {
                Log.d("notification", "sendNotification: " + response);
            }, error -> {
                Log.d("notification", "sendNotification: " + error);
            }) {
                @Override
                public
                Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> map = new HashMap<>();
                    map.put("Authorization", "key=" + SERVER_KEY);
                    map.put("Content-Type", "application/json");
                    return map;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            request.setRetryPolicy(new DefaultRetryPolicy(30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(request);

    }
}