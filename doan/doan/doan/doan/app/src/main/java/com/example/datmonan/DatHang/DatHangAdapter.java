package com.example.datmonan.DatHang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DatHangAdapter extends RecyclerView.Adapter<DatHangAdapter.ViewHolder> {

    private List<MonAn> monAnList;
    private Context context;

    public DatHangAdapter(Context context, List<MonAn> monAnList) {
        this.context = context;
        this.monAnList = monAnList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dat_hang, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.textViewTenMon.setText(monAn.getTenMonAn());
        holder.textViewSoLuong.setText("Số lượng: " + monAn.getSoLuong());
        holder.textViewGiaTien.setText("Giá: " + monAn.getGia() + " VND");

        //dùng để tải hình ảnh lên item
        // Sử dụng Picasso để tải hình ảnh từ URL hoặc file
        if (monAn.getHinhAnh() != null) {
            // Chuyển đổi mảng byte thành Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getHinhAnh(), 0, monAn.getHinhAnh().length);

            // Hiển thị trực tiếp hình ảnh bitmap trong ImageView
            holder.imageViewMonAn.setImageBitmap(bitmap);
        } else {
            holder.imageViewMonAn.setImageResource(R.drawable.ga);
        }
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewMonAn;
        public TextView textViewTenMon;
        public TextView textViewGiaTien;
        public TextView textViewSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMonAn = itemView.findViewById(R.id.imageViewMonAn);
            textViewTenMon = itemView.findViewById(R.id.textViewTenMon);
            textViewGiaTien = itemView.findViewById(R.id.textViewGiaTien);
            textViewSoLuong = itemView.findViewById(R.id.textViewSoLuong);

        }
    }
}