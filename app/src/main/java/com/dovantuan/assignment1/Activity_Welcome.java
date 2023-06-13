package com.dovantuan.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Activity_Welcome extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // Thời gian chờ (3 giây)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Activity_Welcome.this, Activity_Login.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}