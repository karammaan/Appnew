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

public class find_product extends AppCompatActivity {
    EditText pro_id;
    Button get_pro_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_product);

        pro_id = (EditText) findViewById(R.id.product_id );
        get_pro_id=(Button) findViewById(R.id.get_product_id);
    }

    public void get_product(View v)
    {
        String product_id = pro_id.getText().toString();

        JSONObject job = new JSONObject();

        try {
            job.put("add_product_id", product_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet_address.ip+"/new_app/find_product.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals(""))
                    {

                        Intent i = new Intent(find_product.this, get_product.class);

                        startActivity(i);
                        finish();
                    }

                    else {
                        Toast.makeText(find_product.this, "error", Toast.LENGTH_SHORT).show();

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

        AppController app = new AppController(find_product.this);

        app.addToRequestQueue(jobreq);
    }

}
