package com.example.vuphi.orderfoodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vuphi.loginregister.LoginActivity;

public class LogoActivity extends Activity {
    protected int splashTime = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
//                overridePendingTransition(R.anim.anim_fade_out, R.anim.anim_fade_out);
                startActivity(intent);

            }
        }, splashTime);
    }
}
