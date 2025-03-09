package com.example.datmonan.ThucDonAdmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datmonan.DangNhapActivity;
import com.example.datmonan.DoanhThuAdmin.DoanhThuAdminActivity;
import com.example.datmonan.GioHang.GioHang;
import com.example.datmonan.HangBom.DonHangBomActivity;
import com.example.datmonan.R;
import com.example.datmonan.ThongBaoAdmin.ThongBaoAdminActivity;
import com.example.datmonan.TrangChuAdminActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ThucDonAdminActivity extends AppCompatActivity {

    private EditText editTextTenMonAn, editTextGiaMonAn, editTextSoLuongMonAn;
    private ImageView imageViewHinhAnhMonAn;
    private Button buttonThem, buttonSua, buttonXoa, buttonChonHinh;
    private RecyclerView recyclerView;
    private MonAnAdapter monAnAdapter;
    private DBThucDonHelper dbThucDonHelper;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap selectedImageBitmap;
    // Biến lưu trữ thông tin món ăn được chọn để sửa hoặc xóa
    private int selectedMonAnId = -1;
    private LinearLayout linearLayoutTrangChuAdmin, linearLayoutThongBaoAdmin,
            linearLayoutThucDonAdmin,
            linearLayoutHangBomAdmin, linearLayoutDoanhThuAdmin, linearLayoutDangXuatAdmin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuc_don_admin);
        Picasso.setSingletonInstance(new Picasso.Builder(this).build());
        // Kết nối các thành phần giao diện
        editTextTenMonAn = findViewById(R.id.editTextTenMonAn);
        editTextGiaMonAn = findViewById(R.id.editTextGiaMonAn);
        editTextSoLuongMonAn = findViewById(R.id.editTextSoluongMonAn);
        imageViewHinhAnhMonAn = findViewById(R.id.imageViewHinhAnhMonAn);
        buttonThem = findViewById(R.id.buttonThem);
        buttonSua = findViewById(R.id.buttonSua);
        buttonXoa = findViewById(R.id.buttonXoa);
        buttonChonHinh = findViewById(R.id.buttonChonHinh);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutTrangChuAdmin = findViewById(R.id.LinearLayoutTrangChuAdmin);
        linearLayoutThucDonAdmin = findViewById(R.id.LinearLayoutThucDonAdmin);
        linearLayoutHangBomAdmin = findViewById(R.id.LinearLayoutHangBomAdmin);
        linearLayoutDoanhThuAdmin = findViewById(R.id.LinearLayoutDoanhThuAdmin);
        linearLayoutThongBaoAdmin = findViewById(R.id.LinearLayoutThongBaoAdmin);
        linearLayoutDangXuatAdmin = findViewById(R.id.LinearLayoutDangXuatAdmin);

        // Khởi tạo DBThucDonHelper
        dbThucDonHelper = new DBThucDonHelper(this);
        // Khởi tạo GioHang
        GioHang gioHang = new GioHang();
        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Sử dụng LinearLayoutManager
        monAnAdapter = new MonAnAdapter(this, dbThucDonHelper.getAllMonAn(), gioHang); // Truyền GioHang vào constructor
        recyclerView.setAdapter(monAnAdapter);

        // Lắng nghe sự kiện click vào item trong RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Lấy thông tin món ăn được chọn
                        MonAn selectedMonAn = monAnAdapter.getMonAnAtPosition(position);
                        selectedMonAnId = selectedMonAn.getId();
                        editTextTenMonAn.setText(selectedMonAn.getTenMonAn());
                        editTextGiaMonAn.setText(selectedMonAn.getGia()); // Hiển thị giá trực tiếp từ String
                        editTextSoLuongMonAn.setText(selectedMonAn.getSoLuong());
                        if (selectedMonAn.getHinhAnh() != null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(selectedMonAn.getHinhAnh(), 0, selectedMonAn.getHinhAnh().length);
                            imageViewHinhAnhMonAn.setImageBitmap(bitmap);
                            selectedImageBitmap = bitmap;
                        } else {
                            imageViewHinhAnhMonAn.setImageResource(android.R.color.transparent);
                            selectedImageBitmap = null;
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        // Xử lý sự kiện cho Button Thêm
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMonAn = editTextTenMonAn.getText().toString();
                String giaMonAn = editTextGiaMonAn.getText().toString();
                String soLuongMonAn = editTextSoLuongMonAn.getText().toString();
                byte[] imageByteArray = null;
                if (selectedImageBitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    imageByteArray = stream.toByteArray();
                }
                // Kiểm tra xem món ăn đã tồn tại hay chưa(-1) là kiểm tra bất kỳ khi nào nhấn vào
                if (dbThucDonHelper.isMonAnExists(tenMonAn, -1)) { // -1 ensures all items are checked
                    Toast.makeText(ThucDonAdminActivity.this, "Món ăn đã tồn tại!", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = dbThucDonHelper.insertMonAn(tenMonAn, giaMonAn, soLuongMonAn, imageByteArray);
                if (isInserted) {
                    Toast.makeText(ThucDonAdminActivity.this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
                    // Cập nhật danh sách món ăn trong RecyclerView
                    monAnAdapter.updateMonAnList(dbThucDonHelper.getAllMonAn());
                    // Xóa dữ liệu trong các EditText và ImageView
                    editTextTenMonAn.setText("");
                    editTextGiaMonAn.setText("");
                    editTextSoLuongMonAn.setText("");
                    imageViewHinhAnhMonAn.setImageResource(android.R.color.transparent);
                    selectedImageBitmap = null;
                } else {
                    Toast.makeText(ThucDonAdminActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện cho Button Sửa
        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonAnId != -1) {
                    String tenMonAn = editTextTenMonAn.getText().toString();
                    String giaMonAn = editTextGiaMonAn.getText().toString();
                    String soLuongMonAn = editTextSoLuongMonAn.getText().toString();
                    byte[] imageByteArray = null;
                    if (selectedImageBitmap != null) {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        imageByteArray = stream.toByteArray();
                    }
                    // Kiểm tra xem món ăn đã tồn tại hay chưa
                    if (dbThucDonHelper.isMonAnExists(tenMonAn, selectedMonAnId)) {
                        Toast.makeText(ThucDonAdminActivity.this, "Món ăn đã tồn tại!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean isUpdated = dbThucDonHelper.updateMonAn(selectedMonAnId, tenMonAn, giaMonAn, soLuongMonAn, imageByteArray);
                    if (isUpdated) {
                        Toast.makeText(ThucDonAdminActivity.this, "Sửa món ăn thành công", Toast.LENGTH_SHORT).show();
                        // Cập nhật danh sách món ăn trong RecyclerView
                        monAnAdapter.updateMonAnList(dbThucDonHelper.getAllMonAn());
                        // Xóa dữ liệu trong các EditText và ImageView
                        editTextTenMonAn.setText("");
                        editTextGiaMonAn.setText("");
                        editTextSoLuongMonAn.setText("");
                        imageViewHinhAnhMonAn.setImageResource(android.R.color.transparent);
                        selectedImageBitmap = null;
                        selectedMonAnId = -1;
                    } else {
                        Toast.makeText(ThucDonAdminActivity.this, "Sửa món ăn thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThucDonAdminActivity.this, "Vui lòng chọn món ăn cần sửa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện cho Button Xóa
        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonAnId != -1) {
                    boolean isDeleted = dbThucDonHelper.deleteMonAn(selectedMonAnId);
                    if (isDeleted) {
                        Toast.makeText(ThucDonAdminActivity.this, "Xóa món ăn thành công", Toast.LENGTH_SHORT).show();
                        // Cập nhật danh sách món ăn trong RecyclerView
                        monAnAdapter.updateMonAnList(dbThucDonHelper.getAllMonAn());
                        // Xóa dữ liệu trong các EditText và ImageView
                        editTextTenMonAn.setText("");
                        editTextGiaMonAn.setText("");
                        editTextSoLuongMonAn.setText("");
                        imageViewHinhAnhMonAn.setImageResource(android.R.color.transparent);
                        selectedImageBitmap = null;
                        selectedMonAnId = -1;
                    } else {
                        Toast.makeText(ThucDonAdminActivity.this, "Xóa món ăn thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThucDonAdminActivity.this, "Vui lòng chọn món ăn cần xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện cho Button Chọn hình
        buttonChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở trình chọn hình ảnh
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });
        linearLayoutTrangChuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThucDonAdminActivity.this, TrangChuAdminActivity.class);
                startActivity(intent);


                Toast.makeText(ThucDonAdminActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutThucDonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThucDonAdminActivity.this, ThucDonAdminActivity.class);
                startActivity(intent);


                Toast.makeText(ThucDonAdminActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutHangBomAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThucDonAdminActivity.this, DonHangBomActivity.class);
                startActivity(intent);


                Toast.makeText(ThucDonAdminActivity.this, "Thực đơn", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutDoanhThuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThucDonAdminActivity.this, DoanhThuAdminActivity.class);
                startActivity(intent);


                Toast.makeText(ThucDonAdminActivity.this, "Doanh thu", Toast.LENGTH_SHORT).show();
            }

        });
        linearLayoutThongBaoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThucDonAdminActivity.this, ThongBaoAdminActivity.class);
                startActivity(intent);


                Toast.makeText(ThucDonAdminActivity.this, "Thông báo", Toast.LENGTH_SHORT).show();
            }

        });

        linearLayoutDangXuatAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa trạng thái đăng nhập (ví dụ dùng SharedPreferences)
                SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa tất cả thông tin đã lưu
                editor.apply();

                // Hiển thị thông báo đăng xuất
                Toast.makeText(ThucDonAdminActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(ThucDonAdminActivity.this, DangNhapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack của activity trước đó
                startActivity(intent);
            }
        });
    }
//xử lý kết quả
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Lấy Uri của hình ảnh được chọn
            Uri imageUri = data.getData();
            try {
                // Tải hình ảnh từ Uri
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Thu nhỏ kích thước hình ảnh
                int maxWidth = 500; // Ví dụ: Giới hạn chiều rộng tối đa là 500 pixel
                int maxHeight = 500; // Ví dụ: Giới hạn chiều cao tối đa là 500 pixel
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, maxWidth, maxHeight, true);
                imageViewHinhAnhMonAn.setImageBitmap(scaledBitmap);
                selectedImageBitmap = scaledBitmap;

                // Lưu hình ảnh vào database
                byte[] imageByteArray = null;
                if (selectedImageBitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    imageByteArray = stream.toByteArray();

                    // In ra Log để kiểm tra
                    Log.d("ThucDonAdminActivity", "imageByteArray: " + imageByteArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ThucDonAdminActivity.this, "Lỗi tải hình ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Class RecyclerItemClickListener
    private static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;
        private GestureDetector mGestureDetector;

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onLongItemClick(View view, int position);
        }

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null && mListener != null) {
                        mListener.onLongItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

    }

}