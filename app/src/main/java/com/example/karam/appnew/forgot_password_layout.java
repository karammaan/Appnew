package com.example.karam.appnew;

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

public class forgot_password_layout extends AppCompatActivity {
    EditText email;
    EditText password;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_layout);
        email= (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.pass_id);
        update = (Button) findViewById(R.id.update_btn);


    }
    public void update_password (View v){

        String password_et = password.getText().toString();

        JSONObject job = new JSONObject();

        try {
            job.put("pass_key", password_et);
            job.put("email" , getIntent().getStringExtra("email_key"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet_address.ip + "/new_app/update_password.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("result").equals("done")) {

                        Toast.makeText(forgot_password_layout.this , "password updated successfully" ,Toast.LENGTH_SHORT).show();



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

        AppController app = new AppController(forgot_password_layout.this);
        app.addToRequestQueue(jobreq);


    }
}

