package model;
import java.sql.Date;
public class PhieuNhap {
    private int id;
    private Date ngayNhap;
    private float tongTien;
    private NhaCungCap nhaCungCap;
    private NhanVienThuVien nhanVienThuVien;
    public PhieuNhap() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(Date ngayNhap) { this.ngayNhap = ngayNhap; }
    public float getTongTien() { return tongTien; }
    public void setTongTien(float tongTien) { this.tongTien = tongTien; }
    public NhaCungCap getNhaCungCap() { return nhaCungCap; }
    public void setNhaCungCap(NhaCungCap nhaCungCap) { this.nhaCungCap = nhaCungCap; }
    public NhanVienThuVien getNhanVienThuVien() { return nhanVienThuVien; }
    public void setNhanVienThuVien(NhanVienThuVien nhanVienThuVien) { this.nhanVienThuVien = nhanVienThuVien; }
}