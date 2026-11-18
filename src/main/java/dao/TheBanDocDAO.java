package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.BanDoc;
import model.TheBanDoc;
import murach.sql.DAO;
public class TheBanDocDAO extends DAO {
    public TheBanDocDAO() { super(); }
    public TheBanDoc getTheCuaBD(int thanhVienId) {
        TheBanDoc the = null;
        String sql = "SELECT t.*, b.id AS banDocId, b.maSinhVien, b.khoa, b.lop, b.congNo, " +
                "tv.hoVaTen, tv.gioiTinh, tv.dienThoai, tv.email " +
                "FROM tblTheBanDoc t " +
                "JOIN tblBanDoc b ON t.tblBanDocId = b.id " +
                "JOIN tblThanhVien tv ON b.tblThanhVienId = tv.id " +
                "WHERE b.tblThanhVienId = ? ORDER BY t.ngayDangKy DESC LIMIT 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, thanhVienId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                the = new TheBanDoc();
                the.setId(rs.getInt("id"));
                the.setMaThe(rs.getString("maThe"));
                the.setTrangThai(rs.getString("trangThai"));
                the.setNgayDangKy(rs.getDate("ngayDangKy"));
                the.setNgayHetHan(rs.getDate("ngayHetHan"));
                BanDoc bd = new BanDoc();
                bd.setId(rs.getInt("banDocId"));
                bd.setMaSinhVien(rs.getString("maSinhVien"));
                bd.setKhoa(rs.getString("khoa"));
                bd.setLop(rs.getString("lop"));
                bd.setCongNo(rs.getFloat("congNo"));
                bd.setHoVaTen(rs.getString("hoVaTen"));
                bd.setGioiTinh(rs.getString("gioiTinh"));
                bd.setDienThoai(rs.getString("dienThoai"));
                bd.setEmail(rs.getString("email"));
                the.setBanDoc(bd);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return the;
    }
    public boolean luuThe(TheBanDoc theMoi, int thanhVienId, String diaChiMoi) {
        boolean result = false;
        try {
            con.setAutoCommit(false);
            int idBanDoc = 0;
            PreparedStatement psGet = con.prepareStatement("SELECT id FROM tblBanDoc WHERE tblThanhVienId = ?");
            psGet.setInt(1, thanhVienId);
            ResultSet rsGet = psGet.executeQuery();
            if (rsGet.next()) idBanDoc = rsGet.getInt("id");
            String updateBD = "UPDATE tblThanhVien SET email = ?, dienThoai = ?, diaChi = ? WHERE id = ?";
            PreparedStatement ps1 = con.prepareStatement(updateBD);
            ps1.setString(1, theMoi.getBanDoc().getEmail());
            ps1.setString(2, theMoi.getBanDoc().getDienThoai());
            ps1.setString(3, diaChiMoi);
            ps1.setInt(4, thanhVienId);
            ps1.executeUpdate();
            String insertThe = "INSERT INTO tblTheBanDoc (maThe, ngayDangKy, ngayHetHan, trangThai, tblBanDocId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps2 = con.prepareStatement(insertThe);
            ps2.setString(1, theMoi.getMaThe());
            ps2.setDate(2, theMoi.getNgayDangKy());
            ps2.setDate(3, theMoi.getNgayHetHan());
            ps2.setString(4, "Đang hoạt động");
            ps2.setInt(5, idBanDoc);
            ps2.executeUpdate();
            con.commit();
            result = true;
        } catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
        } finally {
            try { con.setAutoCommit(true); } catch (Exception ex) {}
        }
        return result;
    }
    public TheBanDoc getTheMoi(int thanhVienId) {
        return getTheCuaBD(thanhVienId);
    }
    public TheBanDoc getTheVaBD(String maBanDoc) {
        TheBanDoc the = null;
        String sql = "SELECT t.*, b.id AS banDocId, b.maSinhVien, b.khoa, b.lop, b.congNo, " +
                "tv.hoVaTen, tv.gioiTinh, tv.dienThoai, tv.email " +
                "FROM tblTheBanDoc t " +
                "JOIN tblBanDoc b ON t.tblBanDocId = b.id " +
                "JOIN tblThanhVien tv ON b.tblThanhVienId = tv.id " +
                "WHERE (b.maSinhVien = ? OR t.maThe = ?) AND t.trangThai = 'Đang hoạt động'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBanDoc);
            ps.setString(2, maBanDoc);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                the = new TheBanDoc();
                the.setId(rs.getInt("id"));
                the.setMaThe(rs.getString("maThe"));
                the.setTrangThai(rs.getString("trangThai"));
                the.setNgayDangKy(rs.getDate("ngayDangKy"));
                the.setNgayHetHan(rs.getDate("ngayHetHan"));
                BanDoc bd = new BanDoc();
                bd.setId(rs.getInt("banDocId"));
                bd.setMaSinhVien(rs.getString("maSinhVien"));
                bd.setKhoa(rs.getString("khoa"));
                bd.setLop(rs.getString("lop"));
                bd.setCongNo(rs.getFloat("congNo"));
                bd.setHoVaTen(rs.getString("hoVaTen"));
                bd.setGioiTinh(rs.getString("gioiTinh"));
                bd.setDienThoai(rs.getString("dienThoai"));
                bd.setEmail(rs.getString("email"));
                the.setBanDoc(bd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return the;
    }
}