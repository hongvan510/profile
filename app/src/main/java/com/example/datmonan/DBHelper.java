package com.example.datmonan;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//csdl bảng nhân viên
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DatMonAn.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CUSTOM = "KhachHang";

    private static final String COLUMN_ID = "IDKH";
    private static final String COLUMN_NAME = "TenKH";
    private static final String COLUMN_PASSWORD = "MatkhauKH";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_CUSTOM + "("
                + COLUMN_ID + " CHAR(12) PRIMARY KEY,"
                + COLUMN_NAME + " VARCHAR(100),"
                + COLUMN_PASSWORD + " VARCHAR(100)" + ")";

        sqLiteDatabase.execSQL(sql);

        // Gọi phương thức thêm tài khoản admin nếu chưa tồn tại
        }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Xoá bảng cũ nếu tồn tại và tạo lại bảng mới khi cần nâng cấp
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOM);
        onCreate(sqLiteDatabase);
    }



    // Hàm kiểm tra xem Id_NhanVien đã tồn tại chưa
    public boolean isEmployeeExists(String IDKH) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CUSTOM + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(IDKH)});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        sqLiteDatabase.close();
        return exists;
    }

    //Thêm tài khoản vào csdl
    public boolean addCustom(String IDKH, String TenKH, String MatKhauKH) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, IDKH);
        values.put(COLUMN_NAME, TenKH);
        values.put(COLUMN_PASSWORD, MatKhauKH);

        long result = sqLiteDatabase.insert(TABLE_CUSTOM, null, values);
        sqLiteDatabase.close();

        // Trả về true nếu chèn thành công
        return result != -1;
    }



    public boolean checkLogin(String IDKH, String MatKhauKH) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase(); // Mở cơ sở dữ liệu ở chế độ đọc
        String query = "SELECT * FROM KhachHang WHERE IDKH = ? AND MatKhauKH = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{IDKH, MatKhauKH});

        // Kiểm tra nếu có ít nhất một bản ghi thì trả về true, ngược lại là false
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        sqLiteDatabase.close();

        return isValid;
    }
    // Phương thức lấy mật khẩu theo ID
    public String getPassword(String IDKH) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_CUSTOM, new String[]{COLUMN_PASSWORD}, COLUMN_ID + "=?",
                new String[]{IDKH}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String MatKhauKH = cursor.getString(0);
            cursor.close();
            return MatKhauKH;
        }
        return null;
    }
    //hàm kiểm tra đã đăng nhâp
    public boolean isLoggedIn() {
        // Khởi tạo biến để lưu trạng thái đăng nhập
        boolean isLoggedIn = false;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        // Câu truy vấn kiểm tra trạng thái đăng nhập của người dùng

        try {
            sqLiteDatabase = this.getReadableDatabase();
            String query = "SELECT isLoggedIn FROM KhachHang WHERE isLoggedIn = 1";
            // Mở kết nối tới cơ sở dữ liệu và thực thi câu truy vấn
            cursor = sqLiteDatabase.rawQuery(query, null);
            // Kiểm tra xem có dòng dữ liệu nào trả về không
            if (cursor != null && cursor.moveToFirst()) {
                isLoggedIn = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng cursor và database sau khi hoàn thành

            if (cursor != null) cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
        }

        return isLoggedIn;
    }
    // Hàm thêm tài khoản admin nếu chưa tồn tại
    public void addAdminAccount() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Kiểm tra nếu tài khoản admin chưa tồn tại
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{"admin"});
        if (cursor.getCount() == 0) {
            // Thêm tài khoản admin
            ContentValues values = new ContentValues();
            values.put("username", "admin");
            values.put("password", "12345");  // Mật khẩu mẫu cho tài khoản admin
            db.insert("users", null, values);
        }
        cursor.close();
        db.close();
    }
    public boolean isAdminLoggedIn() {
        // Thực hiện truy vấn để kiểm tra thông tin đăng nhập admin trong cơ sở dữ liệu
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{"admin", "12345"});

        // Kiểm tra nếu có kết quả trả về (tức là tài khoản admin tồn tại và đã đăng nhập)
        boolean isLoggedIn = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isLoggedIn;
    }



}





