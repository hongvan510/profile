package com.example.datmonan.ThongBaoAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.DangNhapActivity;
import com.example.datmonan.R;
import com.example.datmonan.TrangChuActivity;
import com.example.datmonan.TrangChuAdminActivity;

import java.util.List; // Import lớp List

public class ThongBaoAdminActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDonHang;
    private DonHangAdapter donHangAdapter;
    private DBDonHangHelper dbDonHangHelper;
    private ImageView imageViewTroLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.thong_bao_admin);
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongBaoAdminActivity.this, TrangChuAdminActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        recyclerViewDonHang = findViewById(R.id.recyclerViewDonHang);
        recyclerViewDonHang.setLayoutManager(new LinearLayoutManager(this));

        dbDonHangHelper = new DBDonHangHelper(this);

        // Load danh sách đơn hàng từ cơ sở dữ liệu
        List<DonHang> donHangList = dbDonHangHelper.getAllDonHang(); // Sử dụng List<DonHang>
        donHangAdapter = new DonHangAdapter(this, donHangList, dbDonHangHelper); // Truyền Context vào constructor
        recyclerViewDonHang.setAdapter(donHangAdapter);
    }
}