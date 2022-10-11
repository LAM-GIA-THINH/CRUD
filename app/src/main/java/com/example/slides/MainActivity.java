package com.example.slides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.slides.adapter.SinhVienAdapter;
import com.example.slides.model.SinhVien;
import com.example.slides.sqlite.DBHelper;
import com.example.slides.sqlite.SinhVienDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SinhVienAdapter sinhVienAdapter;
    private ListView lvSinhVien;
    private String SinhVienId;
    private List<SinhVien> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        database.close();
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        SinhVienDao dao = new SinhVienDao(this);
        list = dao.getAll();

        sinhVienAdapter = new SinhVienAdapter(this, list);
        lvSinhVien.setAdapter(sinhVienAdapter);
lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SinhVien sv = list.get(i);
        SinhVienId = sv.getId();
    }
});
    }

    @Override
    protected void onResume() {
        super.onResume();
        SinhVienDao dao = new SinhVienDao(this);
        List<SinhVien> updatedList = dao.getAll();
        list.clear();
        updatedList.forEach(item->list.add(item));
        sinhVienAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddOrEditSinhVienActivity.class);
        switch (view.getId()){
            case R.id.btnInsert:

                startActivity(intent);
                break;
            case R.id.btnEdit:
                if(SinhVienId == null){
                    Toast.makeText(this, "Phai chon ID Sinh vien", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("id",SinhVienId);
                intent.putExtra("data",bundle);

                startActivity(intent);
                break;
            case R.id.btnDelete:
                if(SinhVienId == null){
                    Toast.makeText(this,"Phai chon ID Sinh vien", Toast.LENGTH_SHORT).show();
                    return;
                }
                SinhVienDao dao = new SinhVienDao(this);
                dao.delete(SinhVienId);
                SinhVienId= null;
                onResume();
                Toast.makeText(this,"Sinh vien da duoc xoa", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}