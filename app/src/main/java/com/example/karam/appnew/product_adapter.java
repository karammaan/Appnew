package com.example.karam.appnew;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class product_adapter extends RecyclerView.Adapter<product_view_holder> {


    JSONArray jsarr;
    Activity b ;

    public product_adapter(JSONArray jarr , Activity a )
    {

        jsarr = jarr;
        b = a ;
    }


    @Override
    public product_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        product_view_holder v = new product_view_holder(LayoutInflater.from(b).inflate(R.layout.product_cell, parent , false));

        return v ;
    }

    @Override
    public void onBindViewHolder(product_view_holder holder, int position) {

        try {
            JSONObject job = jsarr.getJSONObject(position);

            holder.name_box.setText(job.getString("Product_Name"));
            holder.desc_box.setText(job.getString("Product_Description"));
            holder.gender_box.setText(job.getString("Gender"));
            holder.size_box.setText(job.getString("Size"));
            

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public int getItemCount() {
        return jsarr.length();
    }
}
