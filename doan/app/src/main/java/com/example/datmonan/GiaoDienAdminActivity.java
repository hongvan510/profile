//package com.example.datmonan;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.widget.LinearLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class GiaoDienAdminActivity extends AppCompatActivity {
//    private LinearLayout linearLayoutTrangChuAdmin, linearLayoutThongBaoAdmin,
//            linearLayoutThucDonAdmin,
//            linearLayoutHangBomAdmin, linearLayoutDoanhThuAdmin, linearLayoutDangXuatAdmin;
//    private DBHelper dbHelper;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.giaodienadmin);
//
//        // Ánh xạ các phần tử điều hướng
//        linearLayoutTrangChuAdmin = findViewById(R.id.LinearLayoutTrangChu);
//        linearLayoutThucDonAdmin = findViewById(R.id.LinearLayoutThucDonAdmin);
//        linearLayoutHangBomAdmin = findViewById(R.id.LinearLayoutHangBomAdmin);
//        linearLayoutDoanhThuAdmin = findViewById(R.id.LinearLayoutDoanhThuAdmin);
//        linearLayoutThongBaoAdmin = findViewById(R.id.LinearLayoutThongBaoAdmin);
//        linearLayoutDangXuatAdmin = findViewById(R.id.LinearLayoutDangXuatAdmin);
//        dbHelper = new DBHelper(this);
//
////        // Cấu hình sự kiện nhấn vào Trang Chủ
////        linearLayoutTrangChuAdmin.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(GiaoDienAdminActivity.this, TrangChuAdminActivity.class);
////                startActivity(intent);
////                Toast.makeText(GiaoDienAdminActivity.this, "Chào mừng admin đến Trang Chủ!", Toast.LENGTH_SHORT).show();
////            }
////        });
//    }
//}