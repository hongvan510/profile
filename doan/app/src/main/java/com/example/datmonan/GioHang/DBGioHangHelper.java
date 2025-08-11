package com.example.datmonan.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datmonan.ThucDonAdmin.MonAn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBGioHangHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "giohang.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GIO_HANG = "gio_hang";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MON_AN = "mon_an";
    private static final String COLUMN_KHACH_HANG_ID = "khach_hang_id"; // Thêm cột ID khách hàng

    public DBGioHangHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GIO_HANG + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MON_AN + " TEXT, " +
                COLUMN_KHACH_HANG_ID + " TEXT)"); // Thêm cột ID khách hàng
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu (nếu cần)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GIO_HANG);
        onCreate(db);
    }

    // Lưu giỏ hàng cho một khách hàng cụ thể
    public void saveGioHang(GioHang gioHang, String khachHangId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Xóa dữ liệu cũ của khách hàng này
        db.delete(TABLE_GIO_HANG, COLUMN_KHACH_HANG_ID + " = ?", new String[]{khachHangId});

        Gson gson = new Gson();
        String json = gson.toJson(gioHang.getMonAnList());

        ContentValues values = new ContentValues();
        values.put(COLUMN_MON_AN, json);
        values.put(COLUMN_KHACH_HANG_ID, khachHangId); // Lưu ID khách hàng
        db.insert(TABLE_GIO_HANG, null, values);
        db.close();
    }

    // Lấy giỏ hàng của một khách hàng cụ thể
    public GioHang loadGioHang(String khachHangId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GIO_HANG, null,
                COLUMN_KHACH_HANG_ID + " = ?", new String[]{khachHangId},
                null, null, null);
        GioHang gioHang = new GioHang();

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_MON_AN);
            if (columnIndex != -1) {
                String json = cursor.getString(columnIndex);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<MonAn>>() {}.getType();
                List<MonAn> monAnList = gson.fromJson(json, listType);
                gioHang.setMonAnList(monAnList);
            }
        }

        cursor.close();
        db.close();
        return gioHang;
    }
}