package com.example.datmonan.ThucDonKhachHang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datmonan.GioHang.DBGioHangHelper;
import com.example.datmonan.GioHang.GioHang;
import com.example.datmonan.GioHang.GioHangActivity;
import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.DBThucDonHelper;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.TrangChuActivity;
import com.example.datmonan.TrangChuAdminActivity;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    private ImageView imageViewHinhAnhMonAn;
    private TextView textViewTenMonAn;
    private TextView textViewGiaMonAn;
    private TextView textViewSoLuongMonAn;
    private DBThucDonHelper dbThucDonHelper;
    private GioHang gioHang;
    private MonAn monAn;
    private Button buttonThemGioHang;
    private DBGioHangHelper dbGioHangHelper;
    private String khachHangId;
    private ImageView imageViewTroLai;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_san_pham);

        // Khởi tạo các view
        imageViewHinhAnhMonAn = findViewById(R.id.imageViewHinhAnhMonAn);
        textViewTenMonAn = findViewById(R.id.textViewTenMonAn);
        textViewGiaMonAn = findViewById(R.id.textViewGiaMonAn);
        textViewSoLuongMonAn = findViewById(R.id.textViewSoLuongMonAn);
        buttonThemGioHang = findViewById(R.id.ButtonThemGioHang);
        EditText editTextSoLuong = findViewById(R.id.editTextSoLuong);
        Button buttonTangSoLuong = findViewById(R.id.buttonTangSoLuong);
        Button buttonGiamSoLuong = findViewById(R.id.buttonGiamSoLuong);
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, ThucDonActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        // Khởi tạo DBHelper
        dbThucDonHelper = new DBThucDonHelper(this);
        dbGioHangHelper = new DBGioHangHelper(this);

        // Lấy chi tiết món ăn từ Intent
        monAn = getIntent().getParcelableExtra("monAn");

        // Lấy ID khách hàng từ SharedPreferences
        khachHangId = getSharedPreferences("user_info", MODE_PRIVATE).getString("khachHangId", "");

        // Lấy GioHang từ SQLite
        gioHang = dbGioHangHelper.loadGioHang(khachHangId);

        // Hiển thị chi tiết món ăn
        if (monAn != null) {
            textViewTenMonAn.setText(monAn.getTenMonAn());
            textViewGiaMonAn.setText(monAn.getGia());
            textViewSoLuongMonAn.setText(monAn.getSoLuong());

            // Hiển thị hình ảnh (nếu có)
            if (monAn.getHinhAnh() != null) {
                // Chuyển đổi mảng byte thành Bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(monAn.getHinhAnh(), 0, monAn.getHinhAnh().length);

                // Hiển thị trực tiếp hình ảnh bitmap trong ImageView
                imageViewHinhAnhMonAn.setImageBitmap(bitmap);
            } else {
                // Hiển thị hình ảnh mặc định nếu không có
                imageViewHinhAnhMonAn.setImageResource(R.drawable.ga);
            }
        }

        // Xử lý sự kiện click button tăng số lượng
        buttonTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongHienTai = Integer.parseInt(editTextSoLuong.getText().toString());
                if (soLuongHienTai < Integer.parseInt(monAn.getSoLuong())) { // Kiểm tra giới hạn
                    soLuongHienTai++;
                    editTextSoLuong.setText(String.valueOf(soLuongHienTai));
                }
            }
        });

        // Xử lý sự kiện click button giảm số lượng
        buttonGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongHienTai = Integer.parseInt(editTextSoLuong.getText().toString());
                if (soLuongHienTai > 1) { // Kiểm tra giới hạn
                    soLuongHienTai--;
                    editTextSoLuong.setText(String.valueOf(soLuongHienTai));
                }
            }
        });

        // Xử lý sự kiện click nút "Thêm vào giỏ hàng"
        buttonThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monAn != null) { // Kiểm tra xem monAn đã được lấy từ Intent hay chưa
                    // Lấy số lượng từ EditText
                    int soLuong = Integer.parseInt(editTextSoLuong.getText().toString());

                    // Kiểm tra số lượng món ăn trong thực đơn
                    if (Integer.parseInt(monAn.getSoLuong()) > 0) {
                        // Kiểm tra xem món ăn đã có trong giỏ hàng chưa
                        if (gioHang.isMonAnExists(monAn.getTenMonAn())) {
                            // Nếu món ăn đã có, hiển thị thông báo
                            Toast.makeText(ChiTietSanPhamActivity.this,
                                    "Món ăn này đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Nếu món ăn chưa có, thêm món ăn vào giỏ hàng
                            // Cập nhật số lượng món ăn trong thực đơn
                            int newSoLuong = Integer.parseInt(monAn.getSoLuong()) - soLuong;
                            dbThucDonHelper.updateSoLuongMonAn(monAn.getId(), String.valueOf(newSoLuong));
                            // Thêm món ăn mới vào giỏ hàng
                            monAn.setSoLuong(String.valueOf(soLuong));
                            gioHang.addMonAn(monAn);
                            Toast.makeText(ChiTietSanPhamActivity.this, "Đã thêm " +
                                    monAn.getTenMonAn() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }

                        // Lưu GioHang vào SQLite
                        dbGioHangHelper.saveGioHang(gioHang, khachHangId);

                        // Chuyển sang GioHangActivity
                        Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ChiTietSanPhamActivity.this, "Món ăn đã hết hàng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Lỗi lấy thông tin món ăn", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Nhận dữ liệu từ GioHangActivity
            gioHang = data.getParcelableExtra("gioHang");
        }
    }
}