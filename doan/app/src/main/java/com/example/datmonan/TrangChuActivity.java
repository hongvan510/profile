package com.example.datmonan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.DatHang.DatHangActivity;
import com.example.datmonan.GioHang.GioHang;
import com.example.datmonan.GioHang.GioHangActivity;
import com.example.datmonan.ThongBao.ThongBaoActivity;
import com.example.datmonan.ThucDonAdmin.DBThucDonHelper;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.ThucDonAdmin.MonAnAdapter;
import com.example.datmonan.ThucDonKhachHang.ChiTietSanPhamActivity;
import com.example.datmonan.ThucDonKhachHang.RecyclerItemClickListener;
import com.example.datmonan.ThucDonKhachHang.ThucDonActivity;

import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    private LinearLayout linearLayoutTrangChu, linearLayoutThongBao,
            linearLayoutThucDon, linearLayoutGioHang,
            linearLayoutDangXuat;
    private DBHelper dbHelper;
    private GioHang gioHang; // Khai báo biến GioHang
    private AutoCompleteTextView autoCompleteTextViewTimKiem;
    private DBThucDonHelper dbThucDonHelper;
    private List<MonAn> allMonAn;
    private ImageView imageViewTimKiem;
    private RecyclerView recyclerViewThucDon;
    private MonAnAdapter monAnAdapter;
    private List<MonAn> displayedMonAn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);
        initializeViews();

        dbHelper = new DBHelper(this);
        gioHang = new GioHang(); // Khởi tạo GioHang
        dbThucDonHelper = new DBThucDonHelper(this);
        allMonAn = dbThucDonHelper.getAllMonAn(); // Tải tất cả mục một lần
        displayedMonAn = new ArrayList<>();
        monAnAdapter = new MonAnAdapter(this, displayedMonAn, gioHang);
        recyclerViewThucDon.setAdapter(monAnAdapter);
        setupAutoCompleteTextView();
      //  setupRecyclerView();
        setupEventListeners();
        setupRecyclerViewClickListener();
    }
    private void setupRecyclerViewClickListener() {
        recyclerViewThucDon.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerViewThucDon, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MonAn selectedMonAn = displayedMonAn.get(position);
                        Intent intent = new Intent(TrangChuActivity.this, ChiTietSanPhamActivity.class);
                        intent.putExtra("monAn", selectedMonAn);
                        intent.putExtra("gioHang", gioHang);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // Handle long click if needed
                    }
                })
        );
    }
    private void initializeViews() {
        linearLayoutTrangChu = findViewById(R.id.LinearLayoutTrangChu);
        linearLayoutThongBao = findViewById(R.id.LinearLayoutThongBao);
        linearLayoutThucDon = findViewById(R.id.LinearLayoutThucDon);
        linearLayoutGioHang = findViewById(R.id.LinearLayoutGioHang);
        linearLayoutDangXuat = findViewById(R.id.LinearLayoutDangXuat);
        autoCompleteTextViewTimKiem = findViewById(R.id.AutoCompleteTextViewTimKiem);
        imageViewTimKiem = findViewById(R.id.ImageViewTimKiem);
        recyclerViewThucDon = findViewById(R.id.recyclerViewThucDon);
    }

    private void setupAutoCompleteTextView() {
        List<String> monAnNames = new ArrayList<>();
        for (MonAn monAn : allMonAn) {
            monAnNames.add(monAn.getTenMonAn());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, monAnNames);
        autoCompleteTextViewTimKiem.setAdapter(adapter);
    }
    private void setupRecyclerView() {
        monAnAdapter = new MonAnAdapter(this, allMonAn, gioHang);
        recyclerViewThucDon.setAdapter(monAnAdapter);
    }

    private void setupEventListeners() {
        imageViewTimKiem.setOnClickListener(v -> {
            String searchTerm = autoCompleteTextViewTimKiem.getText().toString().trim();
            if (searchTerm.isEmpty()) {
                Toast.makeText(TrangChuActivity.this, "Vui lòng nhập tên món ăn để tìm kiếm!", Toast.LENGTH_SHORT).show();
                displayedMonAn.clear();
                monAnAdapter.updateMonAnList(displayedMonAn);
                return;
            }

            displayedMonAn = filterMonAn(searchTerm);
            if (displayedMonAn.isEmpty()) {
                Toast.makeText(TrangChuActivity.this, "Không tìm thấy món ăn!", Toast.LENGTH_SHORT).show();
            }
            monAnAdapter.updateMonAnList(displayedMonAn);
        });

        linearLayoutTrangChu.setOnClickListener(v -> {
            startActivity(new Intent(TrangChuActivity.this, TrangChuActivity.class));
            Toast.makeText(TrangChuActivity.this, "Trang Chủ", Toast.LENGTH_SHORT).show();
        });

        linearLayoutThongBao.setOnClickListener(v -> {
            startActivity(new Intent(TrangChuActivity.this, ThongBaoActivity.class));
            Toast.makeText(TrangChuActivity.this, "Thông Báo", Toast.LENGTH_SHORT).show();
        });

        linearLayoutThucDon.setOnClickListener(v -> {
            Intent intent = new Intent(TrangChuActivity.this, ThucDonActivity.class);
            intent.putExtra("gioHang", gioHang); // Truyền GioHang
            startActivity(intent);
            Toast.makeText(TrangChuActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
        });

        linearLayoutGioHang.setOnClickListener(v -> {
            Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
            intent.putExtra("gioHang", gioHang); // Truyền GioHang
            startActivity(intent);
            Toast.makeText(TrangChuActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
        });

        linearLayoutDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa trạng thái đăng nhập (ví dụ dùng SharedPreferences)
                SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa tất cả thông tin đã lưu
                editor.apply();

                // Hiển thị thông báo đăng xuất
                Toast.makeText(TrangChuActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(TrangChuActivity.this, DangNhapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack của activity trước đó
                startActivity(intent);
            }
        });
    }

    // Hàm lọc danh sách món ăn
    private List<MonAn> filterMonAn(String searchTerm) {
        List<MonAn> filteredList = new ArrayList<>();
        for (MonAn monAn : allMonAn) {
            if (monAn.getTenMonAn().toLowerCase().equals(searchTerm)) { // Use equals() for exact match
                filteredList.add(monAn);
            }
        }
        return filteredList;
    }
}
