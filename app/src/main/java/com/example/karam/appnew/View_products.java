package com.example.karam.appnew;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_products extends AppCompatActivity {

    static RecyclerView recycle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);


        recycle = (RecyclerView) findViewById(R.id.recycle_id);
        recycle.setLayoutManager(new LinearLayoutManager(View_products.this, LinearLayoutManager.VERTICAL, false));


        get_product_lists(View_products.this);

    }

    public static void get_product_lists(final Activity a) {

        JSONObject job = new JSONObject();



        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet_address.ip + "/new_app/get_products_list.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jarr = response.getJSONArray("result");
                    for(int i = 0 ; i < jarr.length() ; i++)
                    {
                        product_adapter ad = new product_adapter(jarr , a);

                        recycle.setAdapter(ad);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)

            {
                System.out.println(error);
            }
        });

        AppController app = new AppController(a);

        app.addToRequestQueue(jobreq);
    }


}


