package com.example.datmonan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datmonan.DoanhThuAdmin.DoanhThuAdminActivity;
import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.ThongBaoAdmin.ThongBaoAdminActivity;
import com.example.datmonan.ThucDonAdmin.ThucDonAdminActivity;

public class TrangChuAdminActivity extends AppCompatActivity {
    private LinearLayout linearLayoutTrangChuAdmin, linearLayoutThongBaoAdmin,
            linearLayoutThucDonAdmin,
            linearLayoutHangBomAdmin, linearLayoutDoanhThuAdmin, linearLayoutDangXuatAdmin;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu_admin);
        // Ánh xạ các phần tử điều hướng
        linearLayoutTrangChuAdmin = findViewById(R.id.LinearLayoutTrangChuAdmin);
        linearLayoutThucDonAdmin = findViewById(R.id.LinearLayoutThucDonAdmin);
        linearLayoutHangBomAdmin = findViewById(R.id.LinearLayoutHangBomAdmin);
        linearLayoutDoanhThuAdmin = findViewById(R.id.LinearLayoutDoanhThuAdmin);
        linearLayoutThongBaoAdmin = findViewById(R.id.LinearLayoutThongBaoAdmin);
        linearLayoutDangXuatAdmin = findViewById(R.id.LinearLayoutDangXuatAdmin);
        dbHelper = new DBHelper(this);
        // Xử lý sự kiện khi nhấn vào Trang Chủ
//        linearLayoutTrangChuAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TrangChuAdminActivity.this, TrangChuAdminActivity.class);
//                startActivity(intent);
//                Toast.makeText(TrangChuAdminActivity.this, "Trang Chủ", Toast.LENGTH_SHORT).show();
//            }
//        });

        linearLayoutThucDonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrangChuAdminActivity.this, ThucDonAdminActivity.class);
                startActivity(intent);


                Toast.makeText(TrangChuAdminActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutHangBomAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrangChuAdminActivity.this, DonHangBomActivity.class);
                startActivity(intent);


                Toast.makeText(TrangChuAdminActivity.this, "Hàng bom", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutDoanhThuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrangChuAdminActivity.this, DoanhThuAdminActivity.class);
                startActivity(intent);


                Toast.makeText(TrangChuAdminActivity.this, "Doanh thu", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutThongBaoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrangChuAdminActivity.this, ThongBaoAdminActivity.class);
                startActivity(intent);


                Toast.makeText(TrangChuAdminActivity.this, "Thông báo", Toast.LENGTH_SHORT).show();
            }

        });

        linearLayoutDangXuatAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa trạng thái đăng nhập (ví dụ dùng SharedPreferences)
                SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa tất cả thông tin đã lưu
                editor.apply();

                // Hiển thị thông báo đăng xuất
                Toast.makeText(TrangChuAdminActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(TrangChuAdminActivity.this, DangNhapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack của activity trước đó
                startActivity(intent);
            }
        });


    }

}
