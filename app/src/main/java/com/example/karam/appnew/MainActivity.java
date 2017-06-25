package com.example.karam.appnew;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    TextView add_pro, view_pro, find_pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        add_pro = (TextView) findViewById(R.id.add_product_id);
        view_pro = (TextView) findViewById(R.id.view_products_id);
        find_pro = (TextView) findViewById(R.id.find_products_id);

    }
    public void open_drawer(View v) {

        drawer.openDrawer(Gravity.LEFT);
    }

    public void open_to_add(View v){

        Intent i = new Intent(MainActivity.this, Add_products.class);
        startActivity(i);
        drawer.closeDrawer(Gravity.LEFT);
    }
    public void open_to_view(View v){

        Intent i = new Intent(MainActivity.this, View_products.class);
        startActivity(i);
        drawer.closeDrawer(Gravity.LEFT);
    }
    public void open_find_product(View v){

        Intent i = new Intent(MainActivity.this, find_product.class);
        startActivity(i);
        drawer.closeDrawer(Gravity.LEFT);
    }
    public void sign_out(View v)
    {
        finish();
    }
}
