package com.example.karam.appnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.util.Patterns;

public class sign_up_layout extends AppCompatActivity {

    EditText name_id, password_id, age_id, email_id;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);

        name_id = (EditText) findViewById(R.id.name_et);
        password_id = (EditText) findViewById(R.id.password_et);
        age_id = (EditText) findViewById(R.id.age_et);
        email_id = (EditText) findViewById(R.id.email_et);
        button = (Button) findViewById(R.id.button_signup);

    }

    public void register(View v) {
        String name = name_id.getText().toString();
        String password = password_id.getText().toString();
        String email = email_id.getText().toString();
        String age = age_id.getText().toString();

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$";

        if (name.length() < 4 || !name.matches("[a-zA-Z ]+")) {

            Toast.makeText(sign_up_layout.this, "name must be 4 character long and not contain any digits", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!password.matches(pattern) || password.length() < 8) {
            Toast.makeText(sign_up_layout.this, "password must contain atleast one alphabet , digit , special character and length must be 8 character", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.contains("_")) {
            Toast.makeText(sign_up_layout.this, "please enter valid email type", Toast.LENGTH_SHORT).show();
            return;
        }
        if (age.equals("")) {
            Toast.makeText(sign_up_layout.this, "please enter age", Toast.LENGTH_SHORT).show();

            return;
        }


        JSONObject job = new JSONObject();

        try {
            job.put("name_key", name);
            job.put("pass_key", password);
            job.put("email_key", email);
            job.put("age_key", age);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet_address.ip + "/new_app/signup.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("key").equals("1")) {

                        SharedPreferences.Editor sp = getSharedPreferences("user_info", MODE_PRIVATE).edit();

                        sp.putString("user_id", response.getString("user_id"));

                        sp.commit();
                        Intent i = new Intent(sign_up_layout.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(sign_up_layout.this);
        app.addToRequestQueue(jobreq);


    }


}