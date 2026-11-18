package model;

import java.sql.Date;

public class PhieuMuon {
    private int id;
    private Date ngayMuon;
    private String trangThai;
    private TheBanDoc theBanDoc;
    private NhanVienThuVien nhanVienThuVien;
    private String maPhieuMuon;
    public PhieuMuon() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(Date ngayMuon) { this.ngayMuon = ngayMuon; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public TheBanDoc getTheBanDoc() { return theBanDoc; }
    public void setTheBanDoc(TheBanDoc theBanDoc) { this.theBanDoc = theBanDoc; }
    public NhanVienThuVien getNhanVienThuVien() { return nhanVienThuVien; }
    public void setNhanVienThuVien(NhanVienThuVien nhanVienThuVien) { this.nhanVienThuVien = nhanVienThuVien; }
}