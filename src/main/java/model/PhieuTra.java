package model;
import java.sql.Date;
public class PhieuTra {
    private int id;
    private Date ngayTra;
    private float tongTienPhat;
    private TheBanDoc theBanDoc;
    private NhanVienThuVien nhanVienThuVien;
    private String maPhieuTra;
    public PhieuTra() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }
    public float getTongTienPhat() { return tongTienPhat; }
    public void setTongTienPhat(float tongTienPhat) { this.tongTienPhat = tongTienPhat; }
    public TheBanDoc getTheBanDoc() { return theBanDoc; }
    public void setTheBanDoc(TheBanDoc theBanDoc) { this.theBanDoc = theBanDoc; }
    public NhanVienThuVien getNhanVienThuVien() { return nhanVienThuVien; }
    public void setNhanVienThuVien(NhanVienThuVien nhanVienThuVien) { this.nhanVienThuVien = nhanVienThuVien; }
}