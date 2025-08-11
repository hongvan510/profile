package com.example.datmonan.DatHang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.ThongBaoAdmin.DBDonHangHelper;
import com.example.datmonan.ThongBaoAdmin.DonHang;
import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.TrangChuActivity;
import com.example.datmonan.TrangChuAdminActivity;

import java.util.ArrayList;

public class DatHangActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDatHang;
    private DatHangAdapter datHangAdapter;
    private TextView textViewTongTienDatHang;
    private DBDonHangHelper dbDonHangHelper; // Khởi tạo DBDonHangHelper
    private ImageView imageViewTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_hang);

        recyclerViewDatHang = findViewById(R.id.recyclerViewDatHang);
        recyclerViewDatHang.setLayoutManager(new LinearLayoutManager(this));
        textViewTongTienDatHang = findViewById(R.id.textViewTongTienDatHang);
        dbDonHangHelper = new DBDonHangHelper(this); // Khởi tạo DBDonHangHelper
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatHangActivity.this, TrangChuActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        // Nhận dữ liệu từ GioHangActivity
        Intent intent = getIntent();
        ArrayList<MonAn> monAnList = intent.getParcelableArrayListExtra("monAnList");
        int tongTien = intent.getIntExtra("tongTien", 0);
        String khachHangId = intent.getStringExtra("khachHangId"); // Lấy ID khách hàng từ Intent
        Log.d("DatHangActivity", "khachHangId: " + khachHangId); // In log kiểm tra ID khách hàng

        // Kiểm tra xem ID khách hàng có hợp lệ không
        if (khachHangId != null && !khachHangId.isEmpty()) {
            // Hiển thị danh sách món ăn
            datHangAdapter = new DatHangAdapter(this, monAnList);
            recyclerViewDatHang.setAdapter(datHangAdapter);

            // Hiển thị tổng tiền
            textViewTongTienDatHang.setText("Tổng tiền: " + tongTien + " VND");

            // Lưu đơn hàng vào cơ sở dữ liệu
            DonHang donHang = new DonHang(0, khachHangId, monAnList, tongTien, "Chờ xác nhận"); // Sử dụng khachHangId
            dbDonHangHelper.insertDonHang(donHang); // Gọi phương thức insertDonHang()
        } else {
            Log.e("DatHangActivity", "ID khách hàng không hợp lệ!");
            // Xử lý lỗi, ví dụ hiển thị thông báo lỗi cho người dùng
        }
    }
}