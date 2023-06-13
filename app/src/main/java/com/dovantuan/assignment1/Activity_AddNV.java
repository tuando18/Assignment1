package com.dovantuan.assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_AddNV extends AppCompatActivity {

    ArrayList<SinhVien> arrSchool = new ArrayList<>();

    SchoolSpinnerAdapter adapterSchool;
    Context context=this;

//    String selectedItemSpinner = "";

    public static final String KEY_MANV = "coso";
    public static final String KEY_TEN_NV = "ten";
    public static final String KEY_PHONG_BAN = "diachi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nv);

        EditText edtname = findViewById(R.id.edt_tennv);
        EditText edtaddress = findViewById(R.id.edt_manv);

        Spinner spSchool = findViewById(R.id.spinerphongban);

        arrSchool.add(new SinhVien("Nhân Sự", R.drawable.ic_nhansu));
        arrSchool.add(new SinhVien("Hành Chính", R.drawable.ic_hanhchinh));
        arrSchool.add(new SinhVien("Đào Tạo", R.drawable.ic_daotao));

        Listdsnv svModel = (Listdsnv) getIntent().getSerializableExtra(Activity_NhanVien.KEY_SV_MODEL);

        adapterSchool = new SchoolSpinnerAdapter(this, arrSchool);

        spSchool.setAdapter(adapterSchool);

        if (svModel != null) { // sua sinh vien
            edtname.setText(svModel.name);
            edtaddress.setText(svModel.maNV);

            int position = arrSchool.indexOf(svModel.phongBan);
            spSchool.setSelection(position);
        }

        Button btnSubmit = findViewById(R.id.btn_them);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtname.getText().toString();
                String address = edtaddress.getText().toString();

                int index = spSchool.getSelectedItemPosition();
                String branch = arrSchool.get(index).namePB;

                if (name.trim().equals("")) {
                    Toast.makeText(Activity_AddNV.this, "Tên SV không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (address.trim().equals("")) {
                    Toast.makeText(Activity_AddNV.this, "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Listdsnv> ls = new ArrayList<>();
                    readWriteStudent readWriteStudent=new readWriteStudent(context);
                    ls = new readWriteStudent(context).readAllStudents("nhanvien.txt");
                    readWriteStudent.writeStudent(context,"nhanvien.txt",new Listdsnv(arrSchool.get(index).namePB, edtname.getText().toString(),edtaddress.getText().toString()),ls);
                    Intent intent = new Intent();

                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_TEN_NV, name);
                    bundle.putString(KEY_MANV, address);
                    bundle.putString(KEY_PHONG_BAN, branch);

                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private class SchoolSpinnerAdapter extends BaseAdapter {

        Context context;
        ArrayList<SinhVien> list;

        public SchoolSpinnerAdapter(Context context, ArrayList<SinhVien> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_addnv, viewGroup, false);

            SinhVien schoolModel = list.get(i);
            ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
            TextView tvName = view.findViewById(R.id.txtName);

            ivAvatar.setImageResource(schoolModel.getIconId());
            tvName.setText(schoolModel.getNamePB());

            return view;
        }
    }

}
