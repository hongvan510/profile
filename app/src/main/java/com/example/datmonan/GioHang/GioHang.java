package com.example.datmonan.GioHang;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.datmonan.ThucDonAdmin.MonAn;

import java.util.ArrayList;
import java.util.List;

public class GioHang implements Parcelable {
    private List<MonAn> monAnList;
    private String khachHangId;
    public GioHang() {
        monAnList = new ArrayList<>();
    }

    public List<MonAn> getMonAnList() {
        return monAnList;
    }
    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }
    public void addMonAn(MonAn monAn) {
        monAnList.add(monAn);
    }

    // Phương thức removeMonAn()
    public void removeMonAn(MonAn monAn) {
        monAnList.remove(monAn);
    }

    public int getTongSoLuong() {
        int tongSoLuong = 0;
        for (MonAn monAn : monAnList) {
            tongSoLuong += Integer.parseInt(monAn.getSoLuong());
        }
        return tongSoLuong;
    }

    public int getTongTien() {
        int tongTien = 0;
        for (MonAn monAn : monAnList) {
            tongTien += Integer.parseInt(monAn.getGia()) * Integer.parseInt(monAn.getSoLuong());
        }
        return tongTien;
    }
    // Phương thức kiểm tra xem món ăn đã có trong giỏ hàng chưa
    public boolean isMonAnExists(String tenMonAn) {
        for (MonAn monAn : monAnList) {
            if (monAn.getTenMonAn().equals(tenMonAn)) {
                return true;
            }
        }
        return false;
    }

    // Thêm phương thức setMonAnList()
    public void setMonAnList(List<MonAn> monAnList) {
        this.monAnList = monAnList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(monAnList);
    }

    public static final Parcelable.Creator<GioHang> CREATOR = new Parcelable.Creator<GioHang>() {
        @Override
        public GioHang createFromParcel(Parcel in) {
            GioHang gioHang = new GioHang();
            gioHang.monAnList = in.createTypedArrayList(MonAn.CREATOR);
            return gioHang;
        }

        @Override
        public GioHang[] newArray(int size) {
            return new GioHang[size];
        }
    };
}