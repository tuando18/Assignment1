package com.dovantuan.assignment1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Activity_NhanVien extends AppCompatActivity {

    AdapterDSSV adapterSv;
    ListView lvdssv;
    ArrayList<Listdsnv> listSv = new ArrayList<>();
    Context context=this;
    public static final String KEY_SV_MODEL = "sv_model";
//    AdapterDSSV adapterSv;

    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String manv = b.getString(Activity_AddNV.KEY_MANV);
                        String ten = b.getString(Activity_AddNV.KEY_TEN_NV);
                        String phongban = b.getString(Activity_AddNV.KEY_PHONG_BAN);
                        listSv.add(new Listdsnv(manv, ten, phongban));
                        fill();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Nhân Viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lvdssv = findViewById(R.id.lv_nv);
//
//        // Khởi tạo dữ liệu danh sách
//        listSv.add(new Listdsnv("PH31763", "Nguyễn Văn Tuấn", "Hành Chính"));
//        listSv.add(new Listdsnv("PH31764", "Đỗ Văn Tuấn", "Nhân Sự"));
//        listSv.add(new Listdsnv("PH31765", "Nguyễn Công Thưởng", "Đào Tạo"));
//        listSv.add(new Listdsnv("PH31766", "Nguyễn Vinh Tài", "Nhân Sự"));
//        listSv.add(new Listdsnv("PH31767", "Cấn Gia Khiêm", "Hành Chính"));
        fill();

//        Button btnadd = findViewById(R.id.btn_add);
//        btnadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity_NhanVien.this, Activity_AddNV.class);
//                getData.launch(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_quanphongban, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterSv.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSv.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(Activity_NhanVien.this, Activity_AddNV.class);
            getData.launch(intent);
        } else if (item.getItemId() == R.id.menu_dangXuat) {
            Intent intent = new Intent(this, Activity_Login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void fill() {
        adapterSv = new AdapterDSSV(Activity_NhanVien.this, listSv,
                new DeleteItem() {
                    @Override
                    public void onClickForDelete(int pos) {
                        readWriteStudent readWriteStudent2 = new readWriteStudent(context);
                        readWriteStudent2.deleteOneStudent(context, listSv, pos, "nhanvien.txt");
                    }
                },
                new UpdateItem() {
                    @Override
                    public void onClickForUpdate(int pos, String maNV, String name, String phongBan) {
                        readWriteStudent readWriteStudent1 = new readWriteStudent(context);
                        readWriteStudent1.updateStudent(context, listSv, pos, new Listdsnv(maNV, name, phongBan), "nhanvien.txt");
                    }
                }
        );
        lvdssv.setAdapter(adapterSv);
    }

    public void deleteSV(int index) {
        listSv.remove(index);
        fill();
    }

    ActivityResultLauncher<Intent> goToEditNV = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String manv = b.getString(Activity_AddNV.KEY_MANV);
                        //Log.d("coso", "nhan " + cs);
                        String ten = b.getString(Activity_AddNV.KEY_TEN_NV);
                        String phongban = b.getString(Activity_AddNV.KEY_PHONG_BAN);

                        svModel.name = ten;
                        svModel.phongBan = phongban;
                        svModel.maNV = manv;

                        fill();
                    }
                }
            }
    );

    private Listdsnv svModel;

    public void updateSV(int position) {

        Intent intent = new Intent(Activity_NhanVien.this, Activity_AddNV.class);

        svModel = listSv.get(position);
        intent.putExtra(KEY_SV_MODEL, svModel);

        goToEditNV.launch(intent);
    }



    private class AdapterDSSV extends BaseAdapter implements Filterable  {

        Activity activity;
        ArrayList<Listdsnv> list, listOld;
        DeleteItem deleteItem;
        UpdateItem updateItem;

        public AdapterDSSV(Activity activity, ArrayList<Listdsnv> list,DeleteItem deleteItem, UpdateItem updateItem) {
            this.activity = activity;
            this.list = list;
            this.listOld = list;
            this.updateItem= updateItem;
            this.deleteItem = deleteItem;
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

            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_listview_nv, viewGroup, false);

            Listdsnv listsv = list.get(i);

            TextView tvMaNV = view.findViewById(R.id.tvMaNv);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvPhongBan = view.findViewById(R.id.tvPhongBan);

            tvMaNV.setText(listsv.getMaNV());
            tvName.setText(listsv.getName());
            tvPhongBan.setText(listsv.getPhongBan());

            ImageButton btnRemove = view.findViewById(R.id.btn_remove);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Xóa sinh viên");
                    builder.setMessage("Bạn có chắc chắn muốn xóa sinh viên này không?");
                    builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteItem.onClickForDelete(i);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Hủy", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });

            ImageButton btnUpdate = view.findViewById(R.id.btn_edit);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity_NhanVien) activity).updateSV(i);
//                    Toast.makeText(activity, "Update thành công!", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String s = constraint.toString();
                    if (s.isEmpty()) {
                        list = listOld;
                    } else {
                        ArrayList<Listdsnv> listS = new ArrayList<>();
                        for (Listdsnv st : listOld) {
                            if (st.getName().toLowerCase().contains(s.toLowerCase())) {
                                listS.add(st);
                            }
                        }
                        list = listS;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = list;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    list = (ArrayList<Listdsnv>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}