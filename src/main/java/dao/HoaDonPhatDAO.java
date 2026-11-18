package dao;
import java.sql.PreparedStatement;
import model.HoaDonPhat;
import murach.sql.DAO;
public class HoaDonPhatDAO extends DAO {
    public HoaDonPhatDAO() {
        super();
    }
    public boolean luuHD(HoaDonPhat hoaDon) {
        boolean result = false;
        String sql = "INSERT INTO tblHoaDonPhat (maHoaDon, ngayLap, tongPhatCham, tongPhatHuHong, tongTienPhat, trangThai, tblPhieuTraId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "HD" + System.currentTimeMillis());
            ps.setDate(2, new java.sql.Date(hoaDon.getNgayLap().getTime()));
            ps.setFloat(3, hoaDon.getTongPhatCham());
            ps.setFloat(4, hoaDon.getTongPhatHuHong());
            ps.setFloat(5, hoaDon.getTongTienPhat());
            ps.setString(6, hoaDon.getTrangThai());
            ps.setInt(7, hoaDon.getPhieuTra().getId());
            if(ps.executeUpdate() > 0) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean capNhatThanhToan(int maPhieuTra) {
        boolean result = false;
        String sqlUpdateHoaDon = "UPDATE tblHoaDonPhat SET trangThai = ? WHERE tblPhieuTraId = ?";
        String sqlUpdateCongNo = "UPDATE tblBanDoc bd " +
                "JOIN tblTheBanDoc tbd ON bd.id = tbd.tblBanDocId " +
                "JOIN tblPhieuTra pt ON tbd.id = pt.tblTheBanDocId " +
                "JOIN tblHoaDonPhat hd ON pt.id = hd.tblPhieuTraId " +
                "SET bd.congNo = bd.congNo - hd.tongTienPhat " +
                "WHERE hd.tblPhieuTraId = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement psHD = con.prepareStatement(sqlUpdateHoaDon);
            psHD.setString(1, "Đã thanh toán");
            psHD.setInt(2, maPhieuTra);
            psHD.executeUpdate();
            PreparedStatement psCN = con.prepareStatement(sqlUpdateCongNo);
            psCN.setInt(1, maPhieuTra);
            psCN.executeUpdate();
            con.commit();
            result = true;
        } catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
            result = false;
        } finally {
            try { con.setAutoCommit(true); } catch (Exception ex) {}
        }
        return result;
    }
}