package com.example.datmonan.ThongBao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThongBaoAdmin.DonHang;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.TrangChuActivity;
import com.example.datmonan.TrangChuAdminActivity;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class ThongBaoActivity extends AppCompatActivity {
    private TextView textViewIdDonHang;
    private TextView textViewIdKhachHang;
    private TextView textViewMonAn;
    private TextView textViewTongTien;
    private TextView textViewTrangThai;
    private SharedPreferences sharedPreferences;
    private String khachHangId;
    private Button buttonXacNhan;
    private Button buttonKhongNhan; // Nút không nhận hàng
    private DonHang donHang; // Biến lưu trữ DonHang
    private ImageView imageViewTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_bao);

        textViewIdDonHang = findViewById(R.id.textViewIdDonHang);
        textViewIdKhachHang = findViewById(R.id.textViewIdKhachHang);
        textViewMonAn = findViewById(R.id.textViewMonAn);
        textViewTongTien = findViewById(R.id.textViewTongTien);
        textViewTrangThai = findViewById(R.id.textViewTrangThai);
        buttonXacNhan = findViewById(R.id.buttonXacNhan);
        buttonKhongNhan = findViewById(R.id.buttonKhongNhan); // Khởi tạo nút không nhận hàng
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongBaoActivity.this, TrangChuActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        khachHangId = sharedPreferences.getString("khachHangId", "");
        String keyDonHang = "donhang_xacnhan_" + khachHangId;

        // Lấy thông tin đơn hàng từ SharedPreferences
        String donHangJson = sharedPreferences.getString(keyDonHang, "");
        if (!donHangJson.isEmpty()) {
            Gson gson = new Gson();
            Type listType = new TypeToken<DonHang>() {}.getType();
            donHang = gson.fromJson(donHangJson, listType);

            // Thiết lập giá trị cho các TextView
            textViewIdDonHang.setText("ID đơn hàng: " + donHang.getId());
            textViewIdKhachHang.setText("ID khách hàng: " + khachHangId);
            textViewMonAn.setText("Danh sách món ăn: " + getMonAnString(donHang.getMonAnList()));
            textViewTongTien.setText("Tổng tiền: " + donHang.getTongTien() + " VND");
            textViewTrangThai.setText("Trạng thái: " + donHang.getTrangThai());
            // Kiểm tra trạng thái đơn hàng và ẩn nút phù hợp
            if (donHang.getTrangThai().equals("Đã xác nhận")) {
                buttonKhongNhan.setVisibility(View.GONE);
            } else if (donHang.getTrangThai().equals("Không nhận")) {
                buttonXacNhan.setVisibility(View.GONE);
            }

        }

        buttonXacNhan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Cập nhật trạng thái đơn hàng trong SharedPreferences
                updateDonHangStatusInSharedPreferences(donHang);

                // Lưu đơn hàng vào SharedPreferences với key "donhang_list"
                saveDonHangToSharedPreferences(donHang);

                // Ẩn nút "Không nhận"
                buttonKhongNhan.setVisibility(View.GONE);
            }
        });
        buttonKhongNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật trạng thái đơn hàng thành "Không nhận"
                donHang.setTrangThai("Không nhận");
                // Cập nhật trạng thái đơn hàng trong SharedPreferences
                updateDonHangBomStatusInSharedPreferences(donHang);

                // Lưu đơn hàng vào SharedPreferences với key "donhang_bom_list"
                saveDonHangBomToSharedPreferences(donHang);

//                // Chuyển hướng đến danh sách đơn hàng "bom"
//                Intent intent = new Intent(ThongBaoActivity.this, DonHangBomActivity.class);
//                startActivity(intent);

                // Ẩn nút "Nhận hàng"
                buttonXacNhan.setVisibility(View.GONE);
            }
        });
    }

    private String getMonAnString(List<MonAn> monAnList) {
        StringBuilder monAnString = new StringBuilder();
        for (MonAn monAn : monAnList) {
            monAnString.append(monAn.getTenMonAn()).append(" (x").append(monAn.getSoLuong()).append(")\n");
        }
        return monAnString.toString();
    }

    private void updateDonHangStatusInSharedPreferences(DonHang donHang) {
        donHang.setTrangThai("Đã xác nhận"); // Cập nhật trạng thái đơn hàng

        // Lưu đơn hàng đã cập nhật trạng thái vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("donhang_xacnhan_" + khachHangId, new Gson().toJson(donHang));
        editor.apply();

        // Cập nhật trạng thái đơn hàng trong TextView
        textViewTrangThai.setText("Trạng thái: Đã xác nhận");
    }
    private void updateDonHangBomStatusInSharedPreferences(DonHang donHang) {
        donHang.setTrangThai("Không nhận"); // Cập nhật trạng thái đơn hàng

        // Lưu đơn hàng đã cập nhật trạng thái vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("donhang_xacnhan_" + khachHangId, new Gson().toJson(donHang));
        editor.apply();

        // Cập nhật trạng thái đơn hàng trong TextView
        textViewTrangThai.setText("Trạng thái: Không nhận");
    }
    private void saveDonHangToSharedPreferences(DonHang donHang) {
        // Lấy danh sách đơn hàng hiện tại từ SharedPreferences
        List<DonHang> donHangList = getDonHangListFromSharedPreferences();

        // Kiểm tra xem đơn hàng đã có trong danh sách hay chưa
        boolean found = false;
        for (DonHang existingDonHang : donHangList) {
            // Sử dụng phép so sánh trực tiếp cho giá trị nguyên
            if (existingDonHang.getId() == donHang.getId()) {
                found = true;
                existingDonHang.setTrangThai(donHang.getTrangThai()); // Cập nhật trạng thái
                break;
            }
        }

        // Nếu đơn hàng chưa có trong danh sách, thêm vào
        if (!found) {
            donHangList.add(donHang);
        }

        // Lưu danh sách đơn hàng đã cập nhật vào SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("user_info", MODE_PRIVATE).edit();
        editor.putString("donhang_list", new Gson().toJson(donHangList));
        editor.apply();
    }

    private List<DonHang> getDonHangListFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String donHangListJson = sharedPreferences.getString("donhang_list", "[]");
        Type listType = new TypeToken<List<DonHang>>() {}.getType();
        return new Gson().fromJson(donHangListJson, listType);
    }
    // Phương thức lưu đơn hàng "bom" vào SharedPreferences
    private void saveDonHangBomToSharedPreferences(DonHang donHang) {
        // Lấy danh sách đơn hàng "bom" hiện tại từ SharedPreferences
        List<DonHang> donHangBomList = getDonHangBomListFromSharedPreferences();

        // Thêm đơn hàng vào danh sách
        donHangBomList.add(donHang);

        // Lưu danh sách đơn hàng "bom" đã cập nhật vào SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("user_info", MODE_PRIVATE).edit();
        editor.putString("donhang_bom_list", new Gson().toJson(donHangBomList));
        editor.apply();
    }

    // Phương thức lấy danh sách đơn hàng "bom" từ SharedPreferences
    private List<DonHang> getDonHangBomListFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String donHangBomListJson = sharedPreferences.getString("donhang_bom_list", "[]");
        Type listType = new TypeToken<List<DonHang>>() {}.getType();
        return new Gson().fromJson(donHangBomListJson, listType);
    }
    @Override
    public void onBackPressed() {
        // Xóa ID khách hàng khỏi SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("khachHangId");
        editor.apply();

        // Hiển thị lại cả hai nút
        buttonXacNhan.setVisibility(View.VISIBLE);
        buttonKhongNhan.setVisibility(View.VISIBLE);

        super.onBackPressed();
    }

}