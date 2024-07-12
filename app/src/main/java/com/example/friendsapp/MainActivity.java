package com.example.friendsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    AppCompatButton b1,b2;
    String apiurl= "https://friendsapi-re5a.onrender.com/adddata";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        b1= (AppCompatButton) findViewById(R.id.submit);
        b2= (AppCompatButton) findViewById(R.id.view);
        ed1= (EditText) findViewById(R.id.ed1);
        ed2= (EditText) findViewById(R.id.ed2);
        ed3= (EditText) findViewById(R.id.ed3);
        ed4= (EditText) findViewById(R.id.ed4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="",s2="",s3="",s4="";;
                try{
                    s1 = ed1.getText().toString();
                    s2 = ed2.getText().toString();
                    s3 = ed3.getText().toString();
                    s4 = ed4.getText().toString();
                    if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")){
                        Toast.makeText(MainActivity.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Toast.makeText(MainActivity.this, s1+" "+s2+" "+s3+" "+s4, Toast.LENGTH_SHORT).show();
                        JSONObject friend = new JSONObject();
                        try {
                            friend.put("name",s1);
                            friend.put("friendName",s2);
                            friend.put("friendNickName",s3);
                            friend.put("DescribeYourFriend",s4);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        JsonObjectRequest request = new JsonObjectRequest(
                                Request.Method.POST,
                                apiurl,
                                friend,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        ed1.setText("");
                                        ed2.setText("");
                                        ed3.setText("");
                                        ed4.setText("");
                                        Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        //request queue
                        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(request);

                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view.class);
                startActivity(i);
            }
        });

    }
}