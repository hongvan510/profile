package com.example.datmonan.ThongBaoAdmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.MonAn;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    private Context context;
    private List<DonHang> donHangList;
    private DBDonHangHelper dbDonHangHelper;
    private SharedPreferences sharedPreferences; // Thêm biến SharedPreferences

    public DonHangAdapter(Context context, List<DonHang> donHangList, DBDonHangHelper dbDonHangHelper) {
        this.context = context;
        this.donHangList = donHangList;
        this.dbDonHangHelper = dbDonHangHelper;
        this.sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE); // Sử dụng "user_info"
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thong_bao_admin, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = donHangList.get(position);
        holder.textViewIdDonHang.setText("ID đơn hàng: " + donHang.getId());
        holder.textViewIdKhachHang.setText("ID khách hàng: " + donHang.getKhachHangId());
        holder.textViewTongTien.setText("Tổng tiền: " + donHang.getTongTien() + " VND");
        holder.textViewTrangThai.setText("Trạng thái: " + donHang.getTrangThai());

        // Hiển thị danh sách món ăn
        StringBuilder monAnString = new StringBuilder("Danh sách món ăn:\n");
        for (MonAn monAn : donHang.getMonAnList()) {
            monAnString.append(monAn.getTenMonAn()).append(" (x").append(monAn.getSoLuong()).append(")\n");
        }
        holder.textViewMonAn.setText(monAnString.toString());


        // Thêm sự kiện click vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(donHang); // Gọi phương thức onItemClick
            }
        });
    }

    // Phương thức xử lý sự kiện click vào item
    private void onItemClick(DonHang donHang) {
        // Lưu ID khách hàng vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("khachHangId", donHang.getKhachHangId());
        editor.apply();

        // Mở DonHangDetailActivity và truyền ID đơn hàng
        Intent intent = new Intent(context, ChiTietDonHangActivity.class);
        intent.putExtra("donHangId", donHang.getId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewIdDonHang;
        public TextView textViewIdKhachHang;
        public TextView textViewMonAn;
        public TextView textViewTongTien;
        public TextView textViewTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdDonHang = itemView.findViewById(R.id.textViewIdDonHang);
            textViewIdKhachHang = itemView.findViewById(R.id.textViewIdKhachHang);
            textViewMonAn = itemView.findViewById(R.id.textViewMonAn);
            textViewTongTien = itemView.findViewById(R.id.textViewTongTien);
            textViewTrangThai = itemView.findViewById(R.id.textViewTrangThai);
        }
    }
}