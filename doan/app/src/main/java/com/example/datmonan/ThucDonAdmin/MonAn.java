package com.example.datmonan.ThucDonAdmin;

import android.os.Parcel;
import android.os.Parcelable;

public class MonAn implements Parcelable {
    private int id;
    private String tenMonAn;
    private String gia;
    private byte[] hinhAnh;
    private String soLuong;

    // Constructor không có ID (sử dụng khi thêm món ăn mới)
    public MonAn(String tenMonAn, String gia, byte[] hinhAnh, String soLuong) {
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
    }

    // Constructor có ID (sử dụng khi lấy danh sách món ăn từ cơ sở dữ liệu)
    public MonAn(int id, String tenMonAn, String gia, byte[] hinhAnh, String soLuong) {
        this.id = id;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "id=" + id +
                ", tenMonAn='" + tenMonAn + '\'' +
                ", gia='" + gia + '\'' +
                ", hinhAnh=" + hinhAnh +
                ", soLuong='" + soLuong + '\'' +
                '}';
    }

    // Implement Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tenMonAn);
        dest.writeString(gia);
        dest.writeByteArray(hinhAnh);
        dest.writeString(soLuong);
    }

    public static final Parcelable.Creator<MonAn> CREATOR = new Parcelable.Creator<MonAn>() {
        @Override
        public MonAn createFromParcel(Parcel in) {
            return new MonAn(in.readInt(), in.readString(), in.readString(), in.createByteArray(), in.readString());
        }

        @Override
        public MonAn[] newArray(int size) {
            return new MonAn[size];
        }
    };
}