package model;

import java.sql.Date;

public class ThanhVien {
    private int id;
    private String username;
    private String password;
    private String hoVaTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String email;
    private String dienThoai;
    private String ghiChu;

    public ThanhVien() {
    }
    public ThanhVien(int id, String username, String password, String hoVaTen, String gioiTinh, Date ngaySinh, String diaChi, String email, String dienThoai, String ghiChu) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        this.dienThoai = dienThoai;
        this.ghiChu = ghiChu;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getHoVaTen() { return hoVaTen; }
    public void setHoVaTen(String hoVaTen) { this.hoVaTen = hoVaTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    private String role;

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}