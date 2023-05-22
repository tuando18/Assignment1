package com.dovantuan.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // Thời gian chờ (3 giây)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        // Sử dụng Handler để chuyển sang màn hình khác sau thời gian chờ
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent để chuyển sang màn hình khác (ví dụ MainActivity)
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Đóng màn hình chờ
            }
        }, SPLASH_DURATION);
    }

}