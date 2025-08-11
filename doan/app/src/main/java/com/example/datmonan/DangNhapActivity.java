package com.example.datmonan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {

    private EditText edtId, edtMatKhau;
    private Button btnDangNhap, btnTroLaiDN;
    private CheckBox chkLuu;
    private DBHelper dbHelper;
    private static final String PREF_NAME = "DangNhapPrefs";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);

        // Khởi tạo các view
        dbHelper = new DBHelper(this);

        edtId = findViewById(R.id.EditTextID);
        edtMatKhau = findViewById(R.id.EditTextMatKhau);
        chkLuu = findViewById(R.id.CheckBoxLuu);
        btnDangNhap = findViewById(R.id.ButtonDangNhap);
        btnTroLaiDN = findViewById(R.id.ButtonTroLaiDN);
        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        restoreLoginInfo();

        // Nút trở lại đăng nhập
        btnTroLaiDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, GiaoDienActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = edtId.getText().toString().trim();
                String MatKhau = edtMatKhau.getText().toString().trim();

                if (ID.isEmpty() || MatKhau.isEmpty()) {
                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra nếu là admin
                if (ID.equals("admin") && MatKhau.equals("12345")) {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập với quyền admin thành công!", Toast.LENGTH_SHORT).show();
                    // Chuyển đến giao diện admin
                    Intent intent = new Intent(DangNhapActivity.this, TrangChuAdminActivity.class);
                    startActivity(intent);
                    finish();
                } else if (dbHelper.checkLogin(ID, MatKhau)) { // Kiểm tra thông tin đăng nhập là khách hàng
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    saveLoginInfo(ID, MatKhau);

                    // Lưu ID khách hàng vào SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("khachHangId", ID); // Lưu ID khách hàng
                    editor.apply();

                    // Chuyển đến giao diện khách hàng
                    Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class); // Chuyển đến Trang Chủ
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DangNhapActivity.this, "ID hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveLoginInfo(String idKH, String matKhauKH) {
        if (chkLuu.isChecked()) {
            editor.putString("IDKH", idKH);
            editor.putString("MatKhauKH", matKhauKH);
            editor.putBoolean("LuuDangNhap", true);
        } else {
            editor.clear();
        }
        editor.apply();
    }
//hàm check lưu thông tin đăng nhập
    private void restoreLoginInfo() {
        boolean luuDangNhap = preferences.getBoolean("LuuDangNhap", false);
        if (luuDangNhap) {
            String idKH = preferences.getString("IDKH", "");
            String matKhauKH = preferences.getString("MatKhauKH", "");
            edtId.setText(idKH);
            edtMatKhau.setText(matKhauKH);
            chkLuu.setChecked(true);
        }
    }
}