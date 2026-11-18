package model;

public class BanSao {
    private int id;
    private String maBanSao;
    private String tinhTrang;
    private String trangThai;
    private TaiLieu taiLieu;
    public BanSao() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMaBanSao() { return maBanSao; }
    public void setMaBanSao(String maBanSao) { this.maBanSao = maBanSao; }
    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public TaiLieu getTaiLieu() { return taiLieu; }
    public void setTaiLieu(TaiLieu taiLieu) { this.taiLieu = taiLieu; }
}