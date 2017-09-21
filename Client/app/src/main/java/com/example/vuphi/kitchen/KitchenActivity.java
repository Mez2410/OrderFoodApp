package com.example.vuphi.kitchen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.vuphi.kitchen.InformationOrder.InfoOrderActivity;
import com.example.vuphi.loginregister.LoginActivity;
import com.example.vuphi.orderfoodapp.R;

public class KitchenActivity extends AppCompatActivity {

    Toolbar toolbarTable;

    ImageView imgKitchen, imgLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        imgKitchen = (ImageView) findViewById(R.id.img_kitchen_chef);
        imgLogout = (ImageView) findViewById(R.id.img_log_out);

        imgKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KitchenActivity.this, InfoOrderActivity.class);
                startActivity(intent);
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shPref  = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = shPref.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(KitchenActivity.this, LoginActivity.class));
            }
        });
    }
}
