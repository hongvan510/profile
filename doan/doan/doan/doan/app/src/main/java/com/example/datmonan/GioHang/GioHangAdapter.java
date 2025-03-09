package com.example.datmonan.GioHang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.DBThucDonHelper;
import com.example.datmonan.ThucDonAdmin.MonAn;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private Context context;
    private List<MonAn> monAnList;
    private GioHang gioHang;
    private DBGioHangHelper dbGioHangHelper;
    private DBThucDonHelper dbThucDonHelper;
    private String khachHangId; // Thêm ID khách hàng

    public GioHangAdapter(Context context, List<MonAn> monAnList, GioHang gioHang, DBGioHangHelper dbGioHangHelper, DBThucDonHelper dbThucDonHelper, String khachHangId) {
        this.context = context;
        this.monAnList = monAnList;
        this.gioHang = gioHang;
        this.dbGioHangHelper = dbGioHangHelper;
        this.dbThucDonHelper = dbThucDonHelper;
        this.khachHangId = khachHangId; // Lưu ID khách hàng
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.textViewTenMon.setText(monAn.getTenMonAn());
        holder.textViewSoLuong.setText("Số lượng: " + monAn.getSoLuong());
        holder.textViewGiaTien.setText("Giá: " + monAn.getGia() + " VND");

        // Hiển thị hình ảnh
        if (monAn.getHinhAnh() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getHinhAnh(), 0, monAn.getHinhAnh().length);
            holder.imageViewHinhAnh.setImageBitmap(bitmap);
        } else {
            holder.imageViewHinhAnh.setImageResource(R.drawable.ga);
        }

        // Xử lý sự kiện click cho item (hiển thị dialog xác nhận xóa)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    MonAn itemToRemove = monAnList.get(currentPosition);
                    new AlertDialog.Builder(context)
                            .setTitle("Xác nhận xóa")
                            .setMessage("Bạn muốn xóa món ăn này không?")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    removeFromCart(currentPosition, itemToRemove);
                                }
                            })
                            .setNegativeButton("Không", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }

    private void removeFromCart(int position, MonAn itemToRemove) {
        gioHang.removeMonAn(itemToRemove);
        monAnList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, monAnList.size());

        // Cập nhật số lượng món ăn trong thực đơn và thực đơn admin
        int currentQuantity = Integer.parseInt(itemToRemove.getSoLuong());
        int newQuantity = currentQuantity + Integer.parseInt(dbThucDonHelper.getMonAnById(itemToRemove.getId()).getSoLuong());
        dbThucDonHelper.updateSoLuongMonAn(itemToRemove.getId(), String.valueOf(newQuantity));

        // Lưu GioHang vào SQLite
        dbGioHangHelper.saveGioHang(gioHang, khachHangId); // Lưu giỏ hàng cho ID khách hàng hiện tại

        if (changeListener != null) {
            changeListener.onChange();
        }
    }
    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    // Cập nhật lại danh sách món ăn
    public void updateMonAnList(List<MonAn> newMonAnList) {
        this.monAnList = newMonAnList;
        notifyDataSetChanged();

        if (changeListener != null) {
            changeListener.onChange();
        }
    }

    public interface ChangeListener {
        void onChange();
    }

    private ChangeListener changeListener;

    public void setOnChangeListener(ChangeListener listener) {
        this.changeListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewHinhAnh;
        public TextView textViewTenMon;
        public TextView textViewGiaTien;
        public TextView textViewSoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHinhAnh = itemView.findViewById(R.id.imageViewHinhAnh);
            textViewTenMon = itemView.findViewById(R.id.textViewTenMonAn);
            textViewGiaTien = itemView.findViewById(R.id.textViewGiaMonAn);
            textViewSoLuong = itemView.findViewById(R.id.textViewSoLuongMonAn);
        }
    }
}