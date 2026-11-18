package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.BanSao;
import model.ChiTietPhieuMuon;
import model.PhieuMuon;
import model.TaiLieu;
import murach.sql.DAO;
public class ChiTietPhieuMuonDAO extends DAO {
    public ChiTietPhieuMuonDAO() {
        super();
    }
    public List<ChiTietPhieuMuon> getDSMuon(String maThe) {
        List<ChiTietPhieuMuon> list = new ArrayList<>();
        String sql = "SELECT ct.*, pm.ngayMuon, bs.maBanSao, tl.ten AS tenTaiLieu, tl.gia " +
                "FROM tblChiTietPhieuMuon ct " +
                "JOIN tblPhieuMuon pm ON ct.tblPhieuMuonId = pm.id " +
                "JOIN tblTheBanDoc tbd ON pm.tblTheBanDocId = tbd.id " +
                "JOIN tblBanSao bs ON ct.tblBanSaoId = bs.id " +
                "JOIN tblTaiLieu tl ON bs.tblTaiLieuId = tl.id " +
                "WHERE tbd.maThe = ? AND (ct.trangThai IS NULL OR ct.trangThai != 'Đã trả')";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maThe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietPhieuMuon ct = new ChiTietPhieuMuon();
                ct.setId(rs.getInt("id"));
                ct.setHanTra(rs.getDate("hanTra"));
                ct.setTinhTrangMuon(rs.getString("tinhTrangMuon"));
                ct.setTrangThai(rs.getString("trangThai"));
                ct.setNgayTra(rs.getDate("ngayTra"));
                PhieuMuon pm = new PhieuMuon();
                pm.setId(rs.getInt("tblPhieuMuonId"));
                pm.setNgayMuon(rs.getDate("ngayMuon"));
                ct.setPhieuMuon(pm);
                TaiLieu tl = new TaiLieu();
                tl.setTen(rs.getString("tenTaiLieu"));
                tl.setGia(rs.getFloat("gia"));
                BanSao bs = new BanSao();
                bs.setId(rs.getInt("tblBanSaoId"));
                bs.setMaBanSao(rs.getString("maBanSao"));
                bs.setTaiLieu(tl);
                ct.setBanSao(bs);
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean capNhatTra(List<ChiTietPhieuMuon> dsTra) {
        boolean result = true;
        String sql = "UPDATE tblChiTietPhieuMuon SET trangThai = 'Đã trả', ngayTra = ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            for (ChiTietPhieuMuon ct : dsTra) {
                ps.setDate(1, currentDate);
                ps.setInt(2, ct.getId());
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