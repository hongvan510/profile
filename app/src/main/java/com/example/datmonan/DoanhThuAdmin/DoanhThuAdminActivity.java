package com.example.datmonan.DoanhThuAdmin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThongBaoAdmin.DonHang;
import com.example.datmonan.TrangChuAdminActivity;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuAdminActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDonHang;
    private DoanhThuAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;
    private TextView textViewTongDoanhThu;
    private ImageView imageViewTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doanh_thu_admin);
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoanhThuAdminActivity.this, TrangChuAdminActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        recyclerViewDonHang = findViewById(R.id.recyclerViewDonHang);
        adapter = new DoanhThuAdapter(this, getDonHangListFromSharedPreferences());
        recyclerViewDonHang.setAdapter(adapter);
        recyclerViewDonHang.setLayoutManager(new LinearLayoutManager(this));

        textViewTongDoanhThu = findViewById(R.id.textViewTongDoanhThu);

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);

        // Đăng ký lắng nghe thay đổi SharedPreferences
        sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("donhang_list")) {
                    // Cập nhật danh sách đơn hàng
                    adapter.donHangList = getDonHangListFromSharedPreferences();
                    adapter.notifyDataSetChanged();
                    // Cập nhật tổng doanh thu
                    updateTongDoanhThu();
                }
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);

        // Cập nhật tổng doanh thu ban đầu
        updateTongDoanhThu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy đăng ký lắng nghe thay đổi SharedPreferences
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    // Phương thức lấy danh sách đơn hàng từ SharedPreferences
    private List<DonHang> getDonHangListFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String donHangListJson = sharedPreferences.getString("donhang_list", "[]");
        Type listType = new TypeToken<List<DonHang>>() {}.getType();
        return new Gson().fromJson(donHangListJson, listType);
    }

    // Phương thức cập nhật tổng doanh thu
    private void updateTongDoanhThu() {
        int tongDoanhThu = 0;
        for (DonHang donHang : adapter.donHangList) {
            tongDoanhThu += donHang.getTongTien();
        }
        textViewTongDoanhThu.setText("Tổng doanh thu: " + tongDoanhThu + " VND");
    }
}