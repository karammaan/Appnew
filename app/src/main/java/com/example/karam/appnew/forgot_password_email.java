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


        EditText email_idd = (EditText) findViewById(R.id.email_id);

        final String email = email_idd.getText().toString();



        JSONObject job = new JSONObject();

        try {
            job.put("email_key", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet_address.ip + "/new_app/verify_email.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("key").equals("done")) {

                        SharedPreferences.Editor sp = getSharedPreferences("user_info", MODE_PRIVATE).edit();

                        sp.putString("user_id", response.getString("user_id"));

                        sp.commit();

                        int randompin =   (int) (((Math.random())*9000)+1000);

                        Intent i = new Intent(forgot_password_email.this ,forgot_password_user_verify.class);

                        i.putExtra("email_key", email);

                        i.putExtra("pin_key" , randompin);

                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(forgot_password_email.this, "Enter correct email", Toast.LENGTH_SHORT).show();

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

        AppController app = new AppController(forgot_password_email.this);

        app.addToRequestQueue(jobreq);
    }


}

