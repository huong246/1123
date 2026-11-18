package dao;
import java.sql.PreparedStatement;
import java.util.List;
import model.BanSao;
import murach.sql.DAO;
public class BanSaoDAO extends DAO {
    public BanSaoDAO() {
        super();
    }
    public boolean capNhatKho(List<BanSao> dsBanSao) {
        boolean result = true;
        String sql = "UPDATE tblBanSao SET trangThai = ?, tinhTrang = ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            for (BanSao bs : dsBanSao) {
                ps.setString(1, bs.getTrangThai());
                ps.setString(2, bs.getTinhTrang());
                ps.setInt(3, bs.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
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