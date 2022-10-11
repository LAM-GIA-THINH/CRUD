package com.example.slides.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.slides.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {
    private SQLiteDatabase db;

    public SinhVienDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<SinhVien> get(String sql, String ...selectArgs){
        List<SinhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            SinhVien sv = new SinhVien();
            sv.setId(cursor.getString(cursor.getColumnIndex("id")));
            sv.setName(cursor.getString(cursor.getColumnIndex("name")));
            sv.setDiem(cursor.getFloat(cursor.getColumnIndex("diem")));
            list.add(sv);
        }
        return list;
    }
    public List<SinhVien> getAll(){
        String sql = "SELECT * FROM sinhvien";
                return get(sql);
    }
    public SinhVien getById(String id){
        String sql = "SELECT * FROM sinhvien WHERE id = ?";
        List<SinhVien> list = get(sql,id);
        return list.get(0);
    }
    public long insert(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("id", sv.getId());
        values.put("name", sv.getName());
        values.put("diem",sv.getDiem());
        return db.insert("sinhvien", null,values);
    }
    public long update(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("id", sv.getId());
        values.put("name", sv.getName());
        values.put("diem",sv.getDiem());
        return db.update("sinhvien", values, "id=?", new String[]{sv.getId()});

    }
    public int delete(String id){
        return db.delete("sinhvien", "id=?", new String[]{id});
    }
}
