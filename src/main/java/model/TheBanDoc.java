package model;

import java.sql.Date;
public class TheBanDoc {
    private int id;
    private String maThe;
    private Date ngayDangKy;
    private Date ngayHetHan;
    private String trangThai;
    private BanDoc banDoc;
    public TheBanDoc() {
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMaThe() { return maThe; }
    public void setMaThe(String maThe) { this.maThe = maThe; }
    public Date getNgayDangKy() { return ngayDangKy; }
    public void setNgayDangKy(Date ngayDangKy) { this.ngayDangKy = ngayDangKy; }
    public Date getNgayHetHan() { return ngayHetHan; }
    public void setNgayHetHan(Date ngayHetHan) { this.ngayHetHan = ngayHetHan; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public BanDoc getBanDoc() { return banDoc; }
    public void setBanDoc(BanDoc banDoc) { this.banDoc = banDoc; }
}