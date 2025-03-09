package com.example.datmonan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datmonan.GioHang.GioHangActivity;
import com.example.datmonan.ThongBao.ThongBaoActivity;
import com.example.datmonan.ThucDonKhachHang.ThucDonActivity;

public class GiaoDienActivity extends AppCompatActivity {
    private Button btnDangKy, btnDangNhap;
    private LinearLayout linearLayoutTrangChu, linearLayoutThongBao,
            linearLayoutThucDon,  linearLayoutDangXuat, linearLayoutGioHang;
    private DBHelper dbHelper;
    private EditText edtIdKH, edtMatKhauKH;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giao_dien);

        // Ánh xạ các phần tử điều hướng
        linearLayoutTrangChu = findViewById(R.id.LinearLayoutTrangChu);
        linearLayoutThongBao = findViewById(R.id.LinearLayoutThongBao);
        linearLayoutThucDon = findViewById(R.id.LinearLayoutThucDon);
        linearLayoutDangXuat = findViewById(R.id.LinearLayoutDangXuat);
        linearLayoutGioHang = findViewById(R.id.LinearLayoutGioHang);
        btnDangKy = findViewById(R.id.ButtonDangKy);
        btnDangNhap = findViewById(R.id.ButtonDangNhap);
        dbHelper = new DBHelper(this);

        // Sự kiện click vào nút đăng ký và đăng nhập
        AddEvent();

        // Xử lý sự kiện khi nhấn vào Trang Chủ
        linearLayoutTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái đăng nhập
                boolean isLoggedIn = dbHelper.checkLogin("admin", "12345"); // Kiểm tra admin đã đăng nhập hay chưa
                if (!isLoggedIn) {
                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(GiaoDienActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    Toast.makeText(GiaoDienActivity.this, "Vui lòng đăng nhập để vào Trang Chủ!", Toast.LENGTH_SHORT).show();
                } else {
                    // Mở trang chủ admin
                    Intent intent = new Intent(GiaoDienActivity.this, TrangChuAdminActivity.class); // Thay thế TrangChuActivity bằng TrangChuAdminActivity
                    startActivity(intent);
                }
                Toast.makeText(GiaoDienActivity.this, "Trang Chủ", Toast.LENGTH_SHORT).show();
            }
        });
        // Xử lý sự kiện khi nhấn vào Thực đơn
        linearLayoutThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái đăng nhập
                boolean isLoggedIn = dbHelper.checkLogin("admin", "12345");
                if (!isLoggedIn) {
                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(GiaoDienActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    Toast.makeText(GiaoDienActivity.this, "Vui lòng đăng nhập để vào Trang Chủ!", Toast.LENGTH_SHORT).show();
                } else {
                    // Mở thực đơn
                    Intent intent = new Intent(GiaoDienActivity.this, ThucDonActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(GiaoDienActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
            }
        });
        // Xử lý sự kiện khi nhấn vào Giỏ hàng
        linearLayoutGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái đăng nhập
                boolean isLoggedIn = dbHelper.checkLogin("admin", "12345");
                if (!isLoggedIn) {
                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(GiaoDienActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    Toast.makeText(GiaoDienActivity.this, "Vui lòng đăng nhập để vào Trang Chủ!", Toast.LENGTH_SHORT).show();
                } else {
                    // Mở giỏ hàng
                    Intent intent = new Intent(GiaoDienActivity.this, GioHangActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(GiaoDienActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
        // Xử lý sự kiện khi nhấn vào Thông Báo
        linearLayoutThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái đăng nhập
                boolean isLoggedIn = dbHelper.checkLogin("admin", "12345");
                if (!isLoggedIn) {
                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(GiaoDienActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    Toast.makeText(GiaoDienActivity.this, "Vui lòng đăng nhập để vào Trang Chủ!", Toast.LENGTH_SHORT).show();
                } else {
                    // Mở thông báo
                    Intent intent = new Intent(GiaoDienActivity.this, ThongBaoActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(GiaoDienActivity.this, "Thông Báo", Toast.LENGTH_SHORT).show();
            }
        });
        // Xử lý sự kiện khi nhấn vào đăng xuất
        linearLayoutDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái đăng nhập
                boolean isLoggedIn = dbHelper.checkLogin("admin", "12345");
                if (!isLoggedIn) {
                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(GiaoDienActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    Toast.makeText(GiaoDienActivity.this, "Vui lòng đăng nhập để vào Trang Chủ!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(GiaoDienActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(GiaoDienActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();
            }
        });



    }

    // Sự kiện click vào nút đăng ký và đăng nhập
    private void AddEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiaoDienActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiaoDienActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
}