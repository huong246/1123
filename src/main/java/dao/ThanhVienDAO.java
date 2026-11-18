package dao;
import model.ThanhVien;
import murach.sql.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ThanhVienDAO extends DAO {
    public ThanhVienDAO() {
        super();
    }
    public ThanhVien kiemTraDangNhap(String username, String password) {
        ThanhVien tv = null;
        String sql = "SELECT tv.*, " +
                "CASE " +
                "    WHEN bd.id IS NOT NULL THEN 'BanDoc' " +
                "    WHEN nv.id IS NOT NULL THEN 'NhanVien' " +
                "    ELSE 'Unknown' " +
                "END AS role " +
                "FROM tblThanhVien tv " +
                "LEFT JOIN tblBanDoc bd ON tv.id = bd.tblThanhVienId " +
                "LEFT JOIN tblNhanVien nv ON tv.id = nv.tblThanhVienId " +
                "WHERE tv.username = ? AND tv.password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tv = new ThanhVien();
                tv.setId(rs.getInt("id"));
                tv.setUsername(rs.getString("username"));
                tv.setPassword(rs.getString("password"));
                tv.setHoVaTen(rs.getString("hoVaTen"));
                tv.setGioiTinh(rs.getString("gioiTinh"));
                tv.setNgaySinh(rs.getDate("ngaySinh"));
                tv.setDiaChi(rs.getString("diaChi"));
                tv.setEmail(rs.getString("email"));
                tv.setDienThoai(rs.getString("dienThoai"));
                tv.setGhiChu(rs.getString("ghiChu"));
                tv.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tv;
    }
}