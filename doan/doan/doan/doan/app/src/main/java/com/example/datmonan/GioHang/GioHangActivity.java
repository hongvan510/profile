package com.example.datmonan.GioHang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.DangKyActivity;
import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThucDonAdmin.DBThucDonHelper;
import com.example.datmonan.ThucDonAdmin.MonAn;
import com.example.datmonan.DatHang.DatHangActivity;
import com.example.datmonan.ThongBaoAdmin.DBDonHangHelper; // Thêm import DBDonHangHelper
import com.example.datmonan.ThucDonKhachHang.ChiTietSanPhamActivity;
import com.example.datmonan.TrangChuActivity;
import com.example.datmonan.TrangChuAdminActivity;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {

    private RecyclerView recyclerViewGioHang;
    private GioHangAdapter gioHangAdapter;
    private GioHang gioHang;
    private TextView textViewTongTien;
    private Button buttonThanhToan, buttonDatHang;
    private DBGioHangHelper dbGioHangHelper;
    private DBThucDonHelper dbThucDonHelper;
    private DBDonHangHelper dbDonHangHelper; // Khởi tạo DBDonHangHelper
    private boolean isPaymentSelected = false;
    private String khachHangId;
    private ImageView imageViewTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gio_hang);

        recyclerViewGioHang = findViewById(R.id.recyclerViewGioHang);
        recyclerViewGioHang.setLayoutManager(new LinearLayoutManager(this));
        textViewTongTien = findViewById(R.id.textViewTongTien);
        buttonThanhToan = findViewById(R.id.buttonThanhToan);
        buttonDatHang = findViewById(R.id.buttonDatHang);
        imageViewTroLai = findViewById(R.id.ImageViewTroLai);
        imageViewTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioHangActivity.this, TrangChuActivity.class); // Chuyển đến Trang Chủ
                startActivity(intent);
            }
        });
        dbGioHangHelper = new DBGioHangHelper(this);
        dbThucDonHelper = new DBThucDonHelper(this);
        dbDonHangHelper = new DBDonHangHelper(this); // Khởi tạo DBDonHangHelper

        // Lấy ID khách hàng từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        khachHangId = sharedPreferences.getString("khachHangId", "");

        // Load giỏ hàng của ID khách hàng hiện tại
        gioHang = dbGioHangHelper.loadGioHang(khachHangId);

        gioHangAdapter = new GioHangAdapter(this, gioHang.getMonAnList(), gioHang, dbGioHangHelper, dbThucDonHelper, khachHangId);
        recyclerViewGioHang.setAdapter(gioHangAdapter);

        updateTongTien();

        gioHangAdapter.setOnChangeListener(new GioHangAdapter.ChangeListener() {
            @Override
            public void onChange() {
                updateTongTien();
                dbGioHangHelper.saveGioHang(gioHang, khachHangId);
            }
        });

        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(GioHangActivity.this)
                        .setTitle("Chọn phương thức thanh toán")
                        .setItems(new String[]{"Thanh toán tiền mặt"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    buttonThanhToan.setText("Thanh toán tiền mặt");
                                    isPaymentSelected = true;
                                }
                            }
                        })
                        .show();
            }
        });

        // Xử lý sự kiện click cho nút "Đặt hàng"
        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra xem phương thức thanh toán đã được chọn chưa
                if (!isPaymentSelected) {
                    Toast.makeText(GioHangActivity.this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra xem khách hàng đã đặt hàng chưa
                if (dbDonHangHelper.isDonHangExists(khachHangId)) { // Kiểm tra xem đã có đơn hàng nào của khách hàng này chưa
                    Toast.makeText(GioHangActivity.this, "Bạn đã đặt hàng rồi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo Intent để chuyển sang DatHangActivity
                Intent intent = new Intent(GioHangActivity.this, DatHangActivity.class);

                // Truyền danh sách món ăn, tổng tiền và ID khách hàng sang DatHangActivity
                intent.putParcelableArrayListExtra("monAnList", new ArrayList<>(gioHang.getMonAnList()));
                intent.putExtra("tongTien", gioHang.getTongTien());
                intent.putExtra("khachHangId", khachHangId);
                Toast.makeText(GioHangActivity.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();

                // Bắt đầu DatHangActivity
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load giỏ hàng của ID khách hàng hiện tại
        gioHang = dbGioHangHelper.loadGioHang(khachHangId);

        gioHangAdapter.updateMonAnList(gioHang.getMonAnList());

        updateTongTien();
    }

    private void updateTongTien() {
        int tongTien = gioHang.getTongTien();

        textViewTongTien.setText("Tổng tiền: " + tongTien + " VND");
    }

}