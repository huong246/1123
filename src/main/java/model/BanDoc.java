package model;
public class BanDoc extends ThanhVien {
    private String maSinhVien;
    private String khoa;
    private String lop;
    private float congNo;
    public BanDoc() {
        super();
    }
    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }

    public String getKhoa() { return khoa; }
    public void setKhoa(String khoa) { this.khoa = khoa; }

    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }

    public float getCongNo() { return congNo; }
    public void setCongNo(float congNo) { this.congNo = congNo; }
}