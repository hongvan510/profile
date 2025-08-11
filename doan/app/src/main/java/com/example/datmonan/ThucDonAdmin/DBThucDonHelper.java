package com.example.datmonan.ThucDonAdmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBThucDonHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "datmonan.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MON_AN = "MonAn";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN = "ten";
    private static final String COLUMN_GIA = "gia"; // Kiểu dữ liệu của gia là STRING
    private static final String COLUMN_SOLUONG = "so_luong";
    private static final String COLUMN_HINH_ANH = "hinh_anh";

    public DBThucDonHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_MON_AN + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEN + " TEXT, " +
                COLUMN_GIA + " TEXT, " +  // Kiểu dữ liệu của gia là STRING
                COLUMN_SOLUONG + " TEXT, " +
                COLUMN_HINH_ANH + " BLOB)"; // Sử dụng BLOB cho hình ảnh
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MON_AN);
        onCreate(db);
    }

    // Hàm chèn món ăn mới vào cơ sở dữ liệu
    public boolean insertMonAn(String ten, String gia, String soLuong, byte[] hinhAnh) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TEN, ten);
            values.put(COLUMN_GIA, gia); // Lưu trữ gia dưới dạng String
            values.put(COLUMN_SOLUONG, soLuong);
            values.put(COLUMN_HINH_ANH, hinhAnh); // Lưu trữ hình ảnh dưới dạng BLOB

            Log.d("DatabaseInsert", "Giá trị: " + values.toString());

            long result = db.insert(TABLE_MON_AN, null, values);
            db.close();
            return result != -1;
        } catch (SQLiteException e) {
            Log.e("DatabaseError", "Lỗi chèn dữ liệu vào database: " + e.getMessage());
            return false;
        } catch (Exception e) {
            Log.e("DatabaseError", "Lỗi chèn dữ liệu: " + e.getMessage());
            return false;
        }
    }

    // Hàm cập nhật thông tin của món ăn
    public boolean updateMonAn(int id, String ten, String gia, String soLuong, byte[] hinhAnh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN, ten);
        values.put(COLUMN_GIA, gia); // Giữ nguyên kiểu String cho giá
        values.put(COLUMN_SOLUONG, soLuong);
        values.put(COLUMN_HINH_ANH, hinhAnh); // Cập nhật hình ảnh dưới dạng BLOB
        int result = db.update(TABLE_MON_AN, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    public boolean updateSoLuongMonAn(int id, String soLuongMoi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SOLUONG, soLuongMoi);
        int result = db.update(TABLE_MON_AN, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    // Hàm xóa món ăn khỏi cơ sở dữ liệu
    public boolean deleteMonAn(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_MON_AN, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }


    // Phương thức kiểm tra xem món ăn đã tồn tại hay chưa (với ID loại trừ)
    public boolean isMonAnExists(String tenMonAn, int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MON_AN, new String[]{COLUMN_ID},
                COLUMN_TEN + " = ? AND " + COLUMN_ID + " != ?",
                new String[]{tenMonAn, String.valueOf(id)}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
    // Hàm lấy tất cả món ăn từ cơ sở dữ liệu
    public List<MonAn> getAllMonAn() {
        List<MonAn> monAnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MON_AN, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String ten = cursor.getString(cursor.getColumnIndex(COLUMN_TEN));
                // Lấy giá từ database (kiểu String)
                String gia = cursor.getString(cursor.getColumnIndex(COLUMN_GIA));
                String soLuong = cursor.getString(cursor.getColumnIndex(COLUMN_SOLUONG));
                byte[] hinhAnh = cursor.getBlob(cursor.getColumnIndex(COLUMN_HINH_ANH)); // Lấy hình ảnh từ database dưới dạng BLOB
                MonAn monAn = new MonAn(id, ten, gia, hinhAnh, soLuong);
                monAnList.add(monAn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return monAnList;
    }
    //phương thức getMonAnById(int id) để lấy thông tin món ăn dựa trên ID
    public MonAn getMonAnById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MON_AN, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int tenIndex = cursor.getColumnIndex(COLUMN_TEN);
            int giaIndex = cursor.getColumnIndex(COLUMN_GIA);
            int hinhAnhIndex = cursor.getColumnIndex(COLUMN_HINH_ANH);
            int soLuongIndex = cursor.getColumnIndex(COLUMN_SOLUONG);

            int itemId = cursor.getInt(idIndex);
            String tenMonAn = cursor.getString(tenIndex);
            String giaMonAn = cursor.getString(giaIndex);
            byte[] hinhAnh = cursor.getBlob(hinhAnhIndex); // Lấy hình ảnh từ database dưới dạng BLOB
            String soLuong = cursor.getString(soLuongIndex);

            MonAn monAn = new MonAn(itemId, tenMonAn, giaMonAn, hinhAnh, soLuong);
            cursor.close();
            db.close();
            return monAn;
        }
        cursor.close();
        db.close();
        return null;
    }

}