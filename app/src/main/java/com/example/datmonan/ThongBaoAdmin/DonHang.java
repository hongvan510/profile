package com.example.datmonan.ThongBaoAdmin;

import com.example.datmonan.ThucDonAdmin.MonAn;

import java.util.List;

public class DonHang {
    private int id;
    private String khachHangId; // Thêm thuộc tính khachHangId
    private List<MonAn> monAnList;
    private int tongTien;
    private String trangThai;

    // Constructor
    public DonHang(int id, String khachHangId, List<MonAn> monAnList, int tongTien, String trangThai) {
        this.id = id;
        this.khachHangId = khachHangId; // Khởi tạo khachHangId
        this.monAnList = monAnList;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }

    public List<MonAn> getMonAnList() {
        return monAnList;
    }

    public void setMonAnList(List<MonAn> monAnList) {
        this.monAnList = monAnList;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}