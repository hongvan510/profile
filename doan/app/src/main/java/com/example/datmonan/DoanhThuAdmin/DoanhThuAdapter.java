package com.example.datmonan.DoanhThuAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.R;
import com.example.datmonan.ThongBaoAdmin.DonHang;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.google.gson.Gson;

import java.util.List;

public class DoanhThuAdapter extends RecyclerView.Adapter<DoanhThuAdapter.ViewHolder> {
    public List<DonHang> donHangList; // Khai báo public để có thể cập nhật từ DoanhThuAdminActivity
    private Context context;

    public DoanhThuAdapter(Context context, List<DonHang> donHangList) {
        this.context = context;
        this.donHangList = donHangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doanh_thu_admin, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = donHangList.get(position);

        holder.textViewIdDonHang.setText("ID đơn hàng: " + donHang.getId());
        holder.textViewIdKhachHang.setText("ID khách hàng: " + donHang.getKhachHangId());
        holder.textViewMonAn.setText("Danh sách món ăn: " + getMonAnString(donHang.getMonAnList()));
        holder.textViewTongTien.setText("Tổng tiền: " + donHang.getTongTien() + " VND");
        holder.textViewTrangThai.setText("Trạng thái: " + donHang.getTrangThai());
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

        public ViewHolder(View itemView) {
            super(itemView);
            textViewIdDonHang = itemView.findViewById(R.id.textViewIdDonHang);
            textViewIdKhachHang = itemView.findViewById(R.id.textViewIdKhachHang);
            textViewMonAn = itemView.findViewById(R.id.textViewMonAn);
            textViewTongTien = itemView.findViewById(R.id.textViewTongTien);
            textViewTrangThai = itemView.findViewById(R.id.textViewTrangThai);
        }
    }

    private String getMonAnString(List<MonAn> monAnList) {
        StringBuilder monAnString = new StringBuilder();
        for (MonAn monAn : monAnList) {
            monAnString.append(monAn.getTenMonAn()).append(" (x").append(monAn.getSoLuong()).append(")\n");
        }
        return monAnString.toString();
    }
}