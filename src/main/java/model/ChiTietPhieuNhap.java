package model;

public class ChiTietPhieuNhap {
    private int id;
    private int soLuong;
    private float donGia;
    private PhieuNhap phieuNhap;
    private TaiLieu taiLieu;
    public ChiTietPhieuNhap() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public float getDonGia() { return donGia; }
    public void setDonGia(float donGia) { this.donGia = donGia; }
    public PhieuNhap getPhieuNhap() { return phieuNhap; }
    public void setPhieuNhap(PhieuNhap phieuNhap) { this.phieuNhap = phieuNhap; }
    public TaiLieu getTaiLieu() { return taiLieu; }
    public void setTaiLieu(TaiLieu taiLieu) { this.taiLieu = taiLieu; }
}