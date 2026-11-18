package dao;
import java.sql.PreparedStatement;
import murach.sql.DAO;
import java.util.Map;
public class TaiLieuDAO extends DAO {
    public TaiLieuDAO() {
        super();
    }
    public boolean capNhatSoLuongKho(Map<Integer, Integer> dsSoLuongTra) {
        boolean result = true;
        String sql = "UPDATE tblTaiLieu SET soLuong = soLuong + ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);

            for (Map.Entry<Integer, Integer> entry : dsSoLuongTra.entrySet()) {
                ps.setInt(1, entry.getValue());
                ps.setInt(2, entry.getKey());
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