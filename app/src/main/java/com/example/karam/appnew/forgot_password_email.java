package com.example.karam.appnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class forgot_password_email extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_email);

    }
    public void verify_button(View v) {


        EditText email_id = (EditText) findViewById(R.id.email_id);

        String email = email_id.getText().toString();

        if (email.length() < 10) {
            Toast.makeText(forgot_password_email.this, "please enter valid email ", Toast.LENGTH_SHORT).show();

            return;
        }

        int randompin =   (int) (((Math.random())*9000)+1000);

        Intent i = new Intent(forgot_password_email.this ,forgot_password_user_verify.class);

        i.putExtra("email_key" ,email);

        i.putExtra("pin_key" , randompin);

        startActivity(i);
        finish();

    }
}