package com.dovantuan.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class Activity_QLNS extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlns_main);

        Button btnPhongBan = findViewById(R.id.btn_phongban);
        Button btnNhanVien = findViewById(R.id.btn_nv);
        Button btnThoat = findViewById(R.id.btn_out);

        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_QLNS.this, Activity_PhongBan.class);
                startActivity(intent);
            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_QLNS.this, Activity_NhanVien.class);
                startActivity(intent);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_QLNS.this, Activity_Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
