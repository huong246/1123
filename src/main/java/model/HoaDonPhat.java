package model;
import java.sql.Date;
public class HoaDonPhat {
    private int id;
    private Date ngayLap;
    private float tongPhatCham;
    private float tongPhatHuHong;
    private float tongTienPhat;
    private String trangThai;
    private PhieuTra phieuTra;
    private String maHoaDon;
    public HoaDonPhat() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap; }
    public float getTongPhatCham() { return tongPhatCham; }
    public void setTongPhatCham(float tongPhatCham) { this.tongPhatCham = tongPhatCham; }

    public float getTongPhatHuHong() { return tongPhatHuHong; }
    public void setTongPhatHuHong(float tongPhatHuHong) { this.tongPhatHuHong = tongPhatHuHong; }
    public float getTongTienPhat() { return tongTienPhat; }
    public void setTongTienPhat(float tongTienPhat) { this.tongTienPhat = tongTienPhat; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public PhieuTra getPhieuTra() { return phieuTra; }
    public void setPhieuTra(PhieuTra phieuTra) { this.phieuTra = phieuTra; }
}