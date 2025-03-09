package com.example.datmonan.HangBom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.R;
import com.example.datmonan.ThongBaoAdmin.DonHang;
import com.example.datmonan.ThongBaoAdmin.ThongBaoAdminActivity;
import com.example.datmonan.TrangChuAdminActivity;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class DonHangBomActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDonHangBom;
    private DonHangBomAdapter adapter;
    private SharedPreferences sharedPreferences;
    private ImageView imageViewTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hang_bom);
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonHangBomActivity.this, TrangChuAdminActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        recyclerViewDonHangBom = findViewById(R.id.recyclerViewDonHangBom);
        adapter = new DonHangBomAdapter(this, getDonHangBomListFromSharedPreferences());
        recyclerViewDonHangBom.setAdapter(adapter);
        recyclerViewDonHangBom.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
    }

    // Phương thức lấy danh sách đơn hàng "bom" từ SharedPreferences
    private List<DonHang> getDonHangBomListFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String donHangBomListJson = sharedPreferences.getString("donhang_bom_list", "[]");
        Type listType = new TypeToken<List<DonHang>>() {}.getType();
        return new Gson().fromJson(donHangBomListJson, listType);
    }
}