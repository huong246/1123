package model;

public class TaiLieu {
    private int id;
    private String maTaiLieu;
    private String ten;
    private String tacGia;
    private String nhaXuatBan;
    private String namXuatBan;
    private float gia;
    private String moTa;
    private int soLuong;
    private LoaiTaiLieu loaiTaiLieu;
    public TaiLieu() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMaTaiLieu() { return maTaiLieu; }
    public void setMaTaiLieu(String maTaiLieu) { this.maTaiLieu = maTaiLieu; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public String getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(String nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }

    public String getNamXuatBan() { return namXuatBan; }
    public void setNamXuatBan(String namXuatBan) { this.namXuatBan = namXuatBan; }
    public float getGia() { return gia; }
    public void setGia(float gia) { this.gia = gia; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public LoaiTaiLieu getLoaiTaiLieu() { return loaiTaiLieu; }
    public void setLoaiTaiLieu(LoaiTaiLieu loaiTaiLieu) { this.loaiTaiLieu = loaiTaiLieu; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}