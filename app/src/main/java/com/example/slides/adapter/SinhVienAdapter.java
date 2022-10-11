package com.example.slides.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.slides.R;
import com.example.slides.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private Context context;
    private List<SinhVien> list;

    public SinhVienAdapter(Context context, List<SinhVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_sinhvien_item, null);

        }
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDiem = view.findViewById(R.id.tvDiem);
        SinhVien sv = list.get(i);
        tvName.setText(sv.getName());
        tvDiem.setText(""+ sv.getDiem());
        return null;
    }
}
