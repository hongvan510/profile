package com.example.datmonan.ThucDonAdmin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.GioHang.GioHang;
import com.example.datmonan.R;
import com.example.datmonan.ThucDonKhachHang.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHolder> {

    private Context context;
    private List<MonAn> monAnList;
    private GioHang gioHang; // Khai báo GioHang

    public MonAnAdapter(Context context, List<MonAn> monAnList, GioHang gioHang) { // Thêm GioHang vào constructor
        this.context = context;
        this.monAnList = monAnList;
        this.gioHang = gioHang; // Lưu GioHang
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mon_an, parent, false);
        return new ViewHolder(itemView, monAnList, gioHang, parent.getContext()); // Truyền gioHang và context vào constructor
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.textViewTenMonAn.setText(monAn.getTenMonAn());
        holder.textViewGiaMonAn.setText(monAn.getGia());
        holder.textViewSoLuongMonAn.setText(monAn.getSoLuong());
//tải hình từ url và hien len Image
        // Sử dụng Picasso để tải hình ảnh từ URL hoặc file
        if (monAn.getHinhAnh() != null) {
            // Chuyển đổi mảng byte thành Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getHinhAnh(), 0, monAn.getHinhAnh().length);

            // Hiển thị trực tiếp hình ảnh bitmap trong ImageView
            holder.imageViewHinhAnh.setImageBitmap(bitmap);
        } else {
            holder.imageViewHinhAnh.setImageResource(R.drawable.ga);
        }
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public void updateMonAnList(List<MonAn> newMonAnList) {
        this.monAnList = newMonAnList;
        notifyDataSetChanged();
    }

    public MonAn getMonAnAtPosition(int position) {
        return monAnList.get(position);
    }

    public void filterMonAnList(List<MonAn> filteredList) {
        this.monAnList = filteredList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewHinhAnh;
        public TextView textViewTenMonAn;
        public TextView textViewGiaMonAn, textViewSoLuongMonAn;
        private List<MonAn> monAnList;
        private GioHang gioHang; // Khai báo GioHang
        private Context context; // Khai báo context trong ViewHolder

        public ViewHolder(@NonNull View itemView, List<MonAn> monAnList, GioHang gioHang, Context context) { // Truyền GioHang và context vào constructor
            super(itemView);
            this.monAnList = monAnList;
            this.gioHang = gioHang; // Gán giá trị cho gioHang
            this.context = context;
            imageViewHinhAnh = itemView.findViewById(R.id.imageViewHinhAnh);
            textViewTenMonAn = itemView.findViewById(R.id.textViewTenMonAn);
            textViewGiaMonAn = itemView.findViewById(R.id.textViewGiaMonAn);
            textViewSoLuongMonAn = itemView.findViewById(R.id.textViewSoLuongMonAn);

            // Xử lý sự kiện click vào item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MonAn monAn = monAnList.get(position);
//                        Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
//                        intent.putExtra("monAn", monAn); // Truyền món ăn sang activity mới
//                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
