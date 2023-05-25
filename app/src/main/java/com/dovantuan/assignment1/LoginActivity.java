package com.dovantuan.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        EditText edtUsername = findViewById(R.id.edt_username);
        EditText edtPassword = findViewById(R.id.edt_password);

        Bundle data = getIntent().getExtras();

        edtUsername.setText(data.getString("username"));
        edtPassword.setText(data.getString("password"));

        Button dk = findViewById(R.id.btn_dki);
        Button btnLogin = findViewById(R.id.btn_login);

        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){

        Intent data = new Intent();
        data.putExtra("number",18);

        setResult(RESULT_OK, data);
        super.onBackPressed();
    }
}