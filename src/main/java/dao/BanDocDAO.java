package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.BanDoc;
import murach.sql.DAO;

public class BanDocDAO extends DAO {
    public BanDocDAO() { super(); }
    public BanDoc getThongTinBD(int thanhVienId) {
        BanDoc bd = null;
        String sql = "SELECT b.id AS banDocId, b.maSinhVien, b.khoa, b.lop, b.congNo, tv.hoVaTen, tv.email, tv.dienThoai, tv.diaChi, tv.ngaySinh " +
                "FROM tblBanDoc b JOIN tblThanhVien tv ON b.tblThanhVienId = tv.id WHERE tv.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, thanhVienId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bd = new BanDoc();
                bd.setId(rs.getInt("banDocId"));
                bd.setHoVaTen(rs.getString("hoVaTen"));
                bd.setMaSinhVien(rs.getString("maSinhVien"));
                bd.setKhoa(rs.getString("khoa"));
                bd.setLop(rs.getString("lop"));
                bd.setCongNo(rs.getFloat("congNo"));
                bd.setEmail(rs.getString("email"));
                bd.setDienThoai(rs.getString("dienThoai"));
                bd.setDiaChi(rs.getString("diaChi"));
                bd.setNgaySinh(rs.getDate("ngaySinh"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return bd;
    }
    public boolean kiemTraLienHe(String email, String sdt, int thanhVienId) {
        String sql = "SELECT COUNT(*) FROM tblThanhVien tv " +
                "JOIN tblBanDoc bd ON tv.id = bd.tblThanhVienId " +
                "JOIN tblTheBanDoc tbd ON bd.id = tbd.tblBanDocId " +
                "WHERE (tv.email = ? OR tv.dienThoai = ?) AND tbd.trangThai = 'Đang hoạt động' AND tv.id != ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, sdt);
            ps.setInt(3, thanhVienId);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
        return true;
    }
}