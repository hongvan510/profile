package com.example.datmonan.ThongBaoAdmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.datmonan.ThucDonAdmin.MonAn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBDonHangHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "donhang.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DON_HANG = "DonHang";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_KHACH_HANG_ID = "khach_hang_id";
    private static final String COLUMN_MON_AN = "mon_an";
    private static final String COLUMN_TONG_TIEN = "tong_tien";
    private static final String COLUMN_TRANG_THAI = "trang_thai";

    public DBDonHangHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_DON_HANG + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_KHACH_HANG_ID + " TEXT, " + // Thêm cột khach_hang_id
                COLUMN_MON_AN + " TEXT, " +
                COLUMN_TONG_TIEN + " INTEGER, " +
                COLUMN_TRANG_THAI + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu (nếu cần)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DON_HANG);
        onCreate(db);
    }

    public boolean insertDonHang(DonHang donHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KHACH_HANG_ID, donHang.getKhachHangId()); // Lưu ID khách hàng
        values.put(COLUMN_MON_AN, new Gson().toJson(donHang.getMonAnList()));
        values.put(COLUMN_TONG_TIEN, donHang.getTongTien());
        values.put(COLUMN_TRANG_THAI, donHang.getTrangThai());

        long result = db.insert(TABLE_DON_HANG, null, values);
        db.close();
        return result != -1;
    }

    public boolean updateDonHang(int id, String trangThai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRANG_THAI, trangThai);
        int result = db.update(TABLE_DON_HANG, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    public boolean deleteDonHang(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_DON_HANG, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    public List<DonHang> getAllDonHang() {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DON_HANG, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String khachHangId = cursor.getString(cursor.getColumnIndex(COLUMN_KHACH_HANG_ID)); // Lấy ID khách hàng
                String monAnJson = cursor.getString(cursor.getColumnIndex(COLUMN_MON_AN));
                int tongTien = cursor.getInt(cursor.getColumnIndex(COLUMN_TONG_TIEN));
                String trangThai = cursor.getString(cursor.getColumnIndex(COLUMN_TRANG_THAI));

                // Chuyển đổi JSON về dạng List<MonAn>
                Gson gson = new Gson();
                Type listType = new TypeToken<List<MonAn>>() {}.getType();
                List<MonAn> monAnList = gson.fromJson(monAnJson, listType);

                DonHang donHang = new DonHang(id, khachHangId, monAnList, tongTien, trangThai);
                donHangList.add(donHang);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return donHangList;
    }

    public DonHang getDonHangById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DON_HANG, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            String khachHangId = cursor.getString(cursor.getColumnIndex(COLUMN_KHACH_HANG_ID));
            String monAnJson = cursor.getString(cursor.getColumnIndex(COLUMN_MON_AN));
            int tongTien = cursor.getInt(cursor.getColumnIndex(COLUMN_TONG_TIEN));
            String trangThai = cursor.getString(cursor.getColumnIndex(COLUMN_TRANG_THAI));

            // Chuyển đổi JSON về dạng List<MonAn>
            Gson gson = new Gson();
            Type listType = new TypeToken<List<MonAn>>() {}.getType();
            List<MonAn> monAnList = gson.fromJson(monAnJson, listType);

            DonHang donHang = new DonHang(id, khachHangId, monAnList, tongTien, trangThai);
            cursor.close();
            db.close();
            return donHang;
        }

        cursor.close();
        db.close();
        return null;
    }
    public boolean isDonHangExists(String khachHangId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DON_HANG, null, COLUMN_KHACH_HANG_ID + " = ?", new String[]{khachHangId}, null, null, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true; // Đã tồn tại đơn hàng
        }

        cursor.close();
        db.close();
        return false; // Không tồn tại đơn hàng
    }
}