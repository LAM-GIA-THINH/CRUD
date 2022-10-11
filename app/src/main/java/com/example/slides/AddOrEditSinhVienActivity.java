package com.example.slides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slides.model.SinhVien;
import com.example.slides.sqlite.SinhVienDao;

public class AddOrEditSinhVienActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etSinhVienId, etName, etDiem;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_sinh_vien);
        etSinhVienId = findViewById(R.id.etSinhVienId);
        etName = findViewById(R.id.etName);
        etDiem = findViewById(R.id.etDiem);
        btnSave=findViewById(R.id.btnSave);
                btnSave.setOnClickListener(this);
        findViewById(R.id.btnListSV).setOnClickListener(this);
        readSinhVien();
    }

    private void readSinhVien(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null){
            return;
        }
        String id = bundle.getString("id");
        SinhVienDao dao = new SinhVienDao(this);
        SinhVien sv = dao.getById(id);
        etSinhVienId.setText(sv.getId());
        etName.setText(sv.getName());
        etDiem.setText(""+ sv.getDiem());

        btnSave.setText("Update");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                SinhVienDao dao = new SinhVienDao(this);
                SinhVien sv = new SinhVien();
                sv.setId(etSinhVienId.getText().toString());
                sv.setName(etName.getText().toString());
                sv.setDiem(Float.parseFloat(etDiem.getText().toString()));
                if(btnSave.getText().equals("Save")){
                    dao.insert(sv);
                }else{
                    dao.update(sv);
                }

                Toast.makeText(this, "Sinh vien moi da duoc luu", Toast.LENGTH_SHORT).show();


                break;
        }
    }
}