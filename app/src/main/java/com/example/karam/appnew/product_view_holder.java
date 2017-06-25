package com.example.karam.appnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class product_view_holder extends RecyclerView.ViewHolder {

    TextView name_box, desc_box, gender_box, size_box;
    ImageView image_box;

    public product_view_holder(View itemView) {
        super(itemView);

        name_box = (TextView) itemView.findViewById(R.id.prod_name_id);
        desc_box = (TextView) itemView.findViewById(R.id.prod_desc_id);
        gender_box = (TextView) itemView.findViewById(R.id.prod_gender_id);
        size_box = (TextView) itemView.findViewById(R.id.prod_size_id);
        image_box = ((ImageView) itemView.findViewById(R.id.prod_image_id));

    }
}
