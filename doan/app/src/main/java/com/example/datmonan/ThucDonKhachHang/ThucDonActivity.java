package com.example.datmonan.ThucDonKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.datmonan.DangNhapActivity;
import com.example.datmonan.GioHang.GioHang;
import com.example.datmonan.GioHang.GioHangActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThongBao.ThongBaoActivity;
import com.example.datmonan.ThucDonAdmin.DBThucDonHelper;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.ThucDonAdmin.MonAnAdapter;
import com.example.datmonan.TrangChuActivity;

import java.util.List;

public class ThucDonActivity extends AppCompatActivity {

    private RecyclerView recyclerViewThucDon;
    private MonAnAdapter monAnAdapter;
    private DBThucDonHelper dbThucDonHelper;
    private GioHang gioHang; // Khai báo GioHang
    private LinearLayout linearLayoutTrangChu, linearLayoutThongBao,
            linearLayoutThucDon, linearLayoutGioHang,
            linearLayoutDangXuat;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuc_don);
        linearLayoutTrangChu = findViewById(R.id.LinearLayoutTrangChu);
        linearLayoutThongBao = findViewById(R.id.LinearLayoutThongBao);
        linearLayoutThucDon = findViewById(R.id.LinearLayoutThucDon);
        linearLayoutGioHang = findViewById(R.id.LinearLayoutGioHang);
        linearLayoutDangXuat = findViewById(R.id.LinearLayoutDangXuat);
        linearLayoutTrangChu.setOnClickListener(v -> {
            startActivity(new Intent(ThucDonActivity.this, TrangChuActivity.class));
            Toast.makeText(ThucDonActivity.this, "Trang Chủ", Toast.LENGTH_SHORT).show();
        });

        linearLayoutThongBao.setOnClickListener(v -> {
            startActivity(new Intent(ThucDonActivity.this, ThongBaoActivity.class));
            Toast.makeText(ThucDonActivity.this, "Thông Báo", Toast.LENGTH_SHORT).show();
        });

        linearLayoutThucDon.setOnClickListener(v -> {
            Intent intent = new Intent(ThucDonActivity.this, ThucDonActivity.class);
            intent.putExtra("gioHang", gioHang); // Truyền GioHang
            startActivity(intent);
            Toast.makeText(ThucDonActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
        });

        linearLayoutGioHang.setOnClickListener(v -> {
            Intent intent = new Intent(ThucDonActivity.this, GioHangActivity.class);
            intent.putExtra("gioHang", gioHang); // Truyền GioHang
            startActivity(intent);
            Toast.makeText(ThucDonActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ThucDonActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(ThucDonActivity.this, DangNhapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack của activity trước đó
                startActivity(intent);
            }
        });
        recyclerViewThucDon = findViewById(R.id.recyclerViewThucDon);
        recyclerViewThucDon.setLayoutManager(new LinearLayoutManager(this));

        dbThucDonHelper = new DBThucDonHelper(this);
        List<MonAn> monAnList = dbThucDonHelper.getAllMonAn();

        // Khởi tạo GioHang
        gioHang = new GioHang();

        monAnAdapter = new MonAnAdapter(this, monAnList, gioHang); // Truyền GioHang vào constructor
        recyclerViewThucDon.setAdapter(monAnAdapter);

        // Xử lý sự kiện click vào item trong RecyclerView
        recyclerViewThucDon.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerViewThucDon, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Lấy thông tin món ăn được chọn
                        MonAn selectedMonAn = monAnAdapter.getMonAnAtPosition(position);

                        // Chuyển đến ChiTietSanPhamActivity
                        Intent intent = new Intent(ThucDonActivity.this, ChiTietSanPhamActivity.class);
                        intent.putExtra("monAn", selectedMonAn); // Truyền MonAn qua Intent
                        intent.putExtra("gioHang", gioHang);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // Xử lý sự kiện click dài (nếu cần)
                    }
                })
        );
    }

    // Phương thức để cập nhật lại danh sách món ăn trong adapter
    public void updateMonAnList() {
        List<MonAn> monAnList = dbThucDonHelper.getAllMonAn();
        monAnAdapter.updateMonAnList(monAnList);
    }

    // Hàm để xử lý kết quả trả về từ ChiTietSanPhamActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Cập nhật lại danh sách món ăn
            updateMonAnList();
        }
    }
}