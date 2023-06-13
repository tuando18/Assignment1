package com.dovantuan.assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dovantuan.assignment1.PhongBan;
import com.dovantuan.assignment1.R;

import java.util.ArrayList;

public class Activity_PhongBan extends AppCompatActivity {

    ListView listphongban;
    ArrayList<String> list = new ArrayList<>();

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongban_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listphongban = findViewById(R.id.listphongban);

        getSupportActionBar().setTitle("Phòng Ban");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list.add("Nhân Sự");
        list.add("Hành Chính");
        list.add("Đòa Tạo");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listphongban.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_quanphongban,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(Activity_PhongBan.this, Activity_AddNV.class);
        } else if (item.getItemId() == R.id.menu_dangXuat) {
            Intent intent = new Intent(this, Activity_Login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
