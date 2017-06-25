package com.example.karam.appnew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Add_products extends AppCompatActivity {
    EditText namee, descrip;
    Spinner genderr, sizee;
    Button button;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        namee =(EditText)  findViewById(R.id.product_name_id);
        descrip =(EditText)  findViewById(R.id.descp_id);
        genderr =(Spinner)  findViewById(R.id.gender_id);
        sizee =(Spinner)  findViewById(R.id.size_id);
        button =(Button) findViewById(R.id.next_button);

        gett_gender();
        gett_size();


    }

    public void register(View v)
    {
      String prod_name = namee.getText().toString();
        String prod_desc =descrip.getText().toString();
        String gender = genderr.getSelectedItem().toString();
        String size = sizee.getSelectedItem().toString();

        if(prod_name.equals("")) {
          Toast.makeText(Add_products.this , "Enter name" ,Toast.LENGTH_SHORT).show();
            return;
        }
        if(prod_desc.equals("")) {
            Toast.makeText(Add_products.this , "Enter description" ,Toast.LENGTH_SHORT).show();
            return;
        }

       Intent i = new Intent(Add_products.this , upload_product_image.class);
        i.putExtra("name_key" , prod_name);
        i.putExtra("description_key" , prod_desc);
        i.putExtra("gender_key" , gender);
        i.putExtra("size_key", size);
        startActivity(i);
    }





    public void gett_gender()
    {
        JSONObject job = new JSONObject();
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet_address.ip+"/new_app/get_gender.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    List<String> name_list = new ArrayList<>();

                    JSONArray jarr = response.getJSONArray("result");

                    for(int i = 0 ; i < jarr.length() ; i++)
                    {
                        JSONObject job = jarr.getJSONObject(i);
                        String name = job.getString("Gender");
                        name_list.add(name);
                    }
                    ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>(Add_products.this, android.R.layout.simple_dropdown_item_1line,name_list);

                    genderr.setAdapter(adapter_spinner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2, 2));

        AppController app = new AppController(Add_products.this);
        app.addToRequestQueue(jobreq);
    }

    public void gett_size()
    {
        JSONObject job = new JSONObject();
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet_address.ip+"/new_app/get_size.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    List<String> name_list = new ArrayList<>();

                    JSONArray jarr = response.getJSONArray("result");

                    for(int i = 0 ; i < jarr.length() ; i++)
                    {
                        JSONObject job = jarr.getJSONObject(i);
                        String name = job.getString("Size");
                        name_list.add(name);
                    }
                    ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>(Add_products.this, android.R.layout.simple_dropdown_item_1line,name_list);

                    sizee.setAdapter(adapter_spinner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2, 2));

        AppController app = new AppController(Add_products.this);
        app.addToRequestQueue(jobreq);
    }
}
