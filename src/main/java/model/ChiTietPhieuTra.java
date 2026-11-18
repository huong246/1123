package model;
public class ChiTietPhieuTra {
    private int id;
    private String tinhTrangTra;
    private int soNgayTre;
    private float phatTraCham;
    private float phatHuHong;
    private PhieuTra phieuTra;
    private BanSao banSao;
    public ChiTietPhieuTra() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTinhTrangTra() { return tinhTrangTra; }
    public void setTinhTrangTra(String tinhTrangTra) { this.tinhTrangTra = tinhTrangTra; }
    public int getSoNgayTre() { return soNgayTre; }
    public void setSoNgayTre(int soNgayTre) { this.soNgayTre = soNgayTre; }
    public float getPhatTraCham() { return phatTraCham; }
    public void setPhatTraCham(float phatTraCham) { this.phatTraCham = phatTraCham; }
    public float getPhatHuHong() { return phatHuHong; }
    public void setPhatHuHong(float phatHuHong) { this.phatHuHong = phatHuHong; }
    public PhieuTra getPhieuTra() { return phieuTra; }
    public void setPhieuTra(PhieuTra phieuTra) { this.phieuTra = phieuTra; }
    public BanSao getBanSao() { return banSao; }
    public void setBanSao(BanSao banSao) { this.banSao = banSao; }
}