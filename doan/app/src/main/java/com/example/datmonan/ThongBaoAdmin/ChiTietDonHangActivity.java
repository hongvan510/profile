package com.example.datmonan.ThongBaoAdmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.TrangChuAdminActivity;
import com.google.gson.Gson;

public class ChiTietDonHangActivity extends AppCompatActivity {
    private TextView textViewIdDonHangDetail;
    private TextView textViewIdKhachHangDetail;
    private TextView textViewMonAnDetail;
    private TextView textViewTongTienDetail;
    private TextView textViewTrangThaiDetail;
    private Button buttonXacNhan;
    private TextView textViewThongBaoXacNhan;
    private DBDonHangHelper dbDonHangHelper;
    private SharedPreferences sharedPreferences;
    private ImageView imageViewTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_don_hang);

        textViewIdDonHangDetail = findViewById(R.id.textViewIdDonHangDetail);
        textViewIdKhachHangDetail = findViewById(R.id.textViewIdKhachHangDetail);
        textViewMonAnDetail = findViewById(R.id.textViewMonAnDetail);
        textViewTongTienDetail = findViewById(R.id.textViewTongTienDetail);
        textViewTrangThaiDetail = findViewById(R.id.textViewTrangThaiDetail);
        buttonXacNhan = findViewById(R.id.buttonXacNhan);
        textViewThongBaoXacNhan = findViewById(R.id.textViewThongBaoXacNhan);
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietDonHangActivity.this, TrangChuAdminActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        dbDonHangHelper = new DBDonHangHelper(this);
        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE); // Sử dụng "user_info"

        // Lấy ID đơn hàng từ Intent
        int donHangId = getIntent().getIntExtra("donHangId", -1);
        if (donHangId != -1) {
            DonHang donHang = dbDonHangHelper.getDonHangById(donHangId);
            if (donHang != null) {
                textViewIdDonHangDetail.setText("ID Đơn Hàng: " + donHang.getId());
                textViewIdKhachHangDetail.setText("ID Khách Hàng: " + donHang.getKhachHangId());

                // Hiển thị danh sách món ăn
                StringBuilder monAnString = new StringBuilder("Danh sách món ăn:\n");
                for (MonAn monAn : donHang.getMonAnList()) {
                    monAnString.append(monAn.getTenMonAn()).append(" (x").append(monAn.getSoLuong()).append(")\n");
                }
                textViewMonAnDetail.setText(monAnString.toString());

                textViewTongTienDetail.setText("Tổng tiền: " + donHang.getTongTien() + " VND");
                textViewTrangThaiDetail.setText("Trạng thái: " + donHang.getTrangThai());

                // Xử lý sự kiện click nút Xác nhận
                buttonXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dbDonHangHelper.updateDonHang(donHang.getId(), "Đã xác nhận")) {
                            textViewThongBaoXacNhan.setText("Đơn hàng đã được xác nhận!");

                            // Lưu thông tin đơn hàng đã được xác nhận vào SharedPreferences
                            String khachHangId = sharedPreferences.getString("khachHangId", "");
                            String keyDonHang = "donhang_xacnhan_" + khachHangId;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(keyDonHang, new Gson().toJson(donHang));
                            editor.apply();
                        } else {
                            Toast.makeText(ChiTietDonHangActivity.this, "Xác nhận đơn hàng thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Không tìm thấy đơn hàng!", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "ID đơn hàng không hợp lệ!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
