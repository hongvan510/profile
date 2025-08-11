package com.example.datmonan.HangBom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.ThongBaoAdmin.DonHang;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.R;
import java.util.List;

public class DonHangBomAdapter extends RecyclerView.Adapter<DonHangBomAdapter.ViewHolder> {
    private List<DonHang> donHangBomList;
    private Context context;

    public DonHangBomAdapter(Context context, List<DonHang> donHangBomList) {
        this.context = context;
        this.donHangBomList = donHangBomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hang_bom, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = donHangBomList.get(position);

        holder.textViewIdDonHang.setText("ID đơn hàng: " + donHang.getId());
        holder.textViewIdKhachHang.setText("ID khách hàng: " + donHang.getKhachHangId());
        holder.textViewMonAn.setText("Danh sách món ăn: " + getMonAnString(donHang.getMonAnList()));
        holder.textViewTongTien.setText("Tổng tiền: " + donHang.getTongTien() + " VND");
        holder.textViewTrangThai.setText("Trạng thái: " + donHang.getTrangThai());
    }

    @Override
    public int getItemCount() {
        return donHangBomList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewIdDonHang;
        public TextView textViewIdKhachHang;
        public TextView textViewMonAn;
        public TextView textViewTongTien;
        public TextView textViewTrangThai;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewIdDonHang = itemView.findViewById(R.id.textViewIdDonHangBom);
            textViewIdKhachHang = itemView.findViewById(R.id.textViewIdKhachHangBom);
            textViewMonAn = itemView.findViewById(R.id.textViewMonAnBom);
            textViewTongTien = itemView.findViewById(R.id.textViewTongTienBom);
            textViewTrangThai = itemView.findViewById(R.id.textViewTrangThaiBom);
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
