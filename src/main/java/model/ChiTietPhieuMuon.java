package model;
import java.sql.Date;
public class ChiTietPhieuMuon {
    private int id;
    private Date hanTra;
    private String tinhTrangMuon;
    private String trangThai;
    private PhieuMuon phieuMuon;
    private BanSao banSao;
    private Date ngayTra;
    public ChiTietPhieuMuon() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getHanTra() { return hanTra; }
    public void setHanTra(Date hanTra) { this.hanTra = hanTra; }
    public String getTinhTrangMuon() { return tinhTrangMuon; }
    public void setTinhTrangMuon(String tinhTrangMuon) { this.tinhTrangMuon = tinhTrangMuon; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public PhieuMuon getPhieuMuon() { return phieuMuon; }
    public void setPhieuMuon(PhieuMuon phieuMuon) { this.phieuMuon = phieuMuon; }
    public BanSao getBanSao() { return banSao; }
    public void setBanSao(BanSao banSao) { this.banSao = banSao; }
    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }
}