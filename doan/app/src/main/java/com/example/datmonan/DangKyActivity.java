package com.example.datmonan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DangKyActivity extends AppCompatActivity {
    private EditText edtId, edtTen, edtMatKhau, edtNhapLaiMatKhau;
    private Button btnDangKy, btnTroLaiDK;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dang_ky);
        // Khởi tạo các view
        dbHelper = new DBHelper(this);

        edtId = findViewById(R.id.EditTextID);
        edtTen = findViewById(R.id.EditTextTen);
        edtMatKhau = findViewById(R.id.EditTextMatKhau);
   //     edtNhapLaiMatKhau = findViewById(R.id.EditTextNhapLaiMatKhau);
        btnDangKy = findViewById(R.id.ButtonDangKy);
        btnTroLaiDK = findViewById(R.id.ButtonTroLaiDK);

        // Xử lý sự kiện nhấn nút đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = edtId.getText().toString().trim();
                String Ten = edtTen.getText().toString().trim();
                String MatKhau = edtMatKhau.getText().toString().trim();
          //      String NhapLaiMatKhau = edtNhapLaiMatKhau.getText().toString().trim();

                if (ID.isEmpty() || Ten.isEmpty() || MatKhau.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Hàm kiểm tra xem Id_NhanVien đã tồn tại chưa
                if (dbHelper.isEmployeeExists(ID)) {
                    Toast.makeText(DangKyActivity.this, "IDKH đã tồn tại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Thêm tài khoản vào csdl
                boolean isInserted = dbHelper.addCustom(ID, Ten, MatKhau);
                if (isInserted) {
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    // Chuyển sang trang đăng nhập hoặc trang chủ
                    Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                } else {Toast.makeText(DangKyActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnTroLaiDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this, GiaoDienActivity.class);
                startActivity(intent);
            }
        });

    }
}