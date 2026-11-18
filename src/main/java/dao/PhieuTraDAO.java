package dao;
import java.sql.PreparedStatement;
import java.util.List;
import model.PhieuTra;
import model.ChiTietPhieuTra;
import murach.sql.DAO;
public class PhieuTraDAO extends DAO {
    public PhieuTraDAO() {
        super();
    }
    public int luuPhieu(PhieuTra phieuTra, List<ChiTietPhieuTra> dsChiTiet) {
        int phieuTraId = 0;
        String insertPT = "INSERT INTO tblPhieuTra (maPhieuTra, ngayTra, tongTienPhat, tblTheBanDocId, tblNhanVienThuVienId) " +
                "VALUES (?, ?, ?, ?, ?)";
        String insertCT = "INSERT INTO tblChiTietPhieuTra (tinhTrangTra, soNgayTre, phatTraCham, phatHuHong, tblPhieuTraId, tblBanSaoId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement psPT = con.prepareStatement(insertPT, PreparedStatement.RETURN_GENERATED_KEYS);
            String maPT = "PT" + System.currentTimeMillis();
            psPT.setString(1, maPT);
            if (phieuTra.getNgayTra() != null) {
                psPT.setDate(2, new java.sql.Date(phieuTra.getNgayTra().getTime()));
            } else {
                psPT.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            }
            psPT.setFloat(3, phieuTra.getTongTienPhat());
            psPT.setInt(4, phieuTra.getTheBanDoc().getId());
            psPT.setInt(5, phieuTra.getNhanVienThuVien().getId());
            psPT.executeUpdate();
            java.sql.ResultSet rs = psPT.getGeneratedKeys();
            if(rs.next()) {
                phieuTraId = rs.getInt(1);
            }
            if (phieuTraId > 0) {
                PreparedStatement psCT = con.prepareStatement(insertCT);
                for(ChiTietPhieuTra ct : dsChiTiet){
                    psCT.setString(1, ct.getTinhTrangTra());
                    psCT.setInt(2, ct.getSoNgayTre());
                    psCT.setFloat(3, ct.getPhatTraCham());
                    psCT.setFloat(4, ct.getPhatHuHong());
                    psCT.setInt(5, phieuTraId);
                    psCT.setInt(6, ct.getBanSao().getId());
                    psCT.addBatch();
                }
                psCT.executeBatch();
            }
            con.commit();
        } catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
            phieuTraId = 0;
        } finally {
            try { con.setAutoCommit(true); } catch (Exception ex) {}
        }
        return phieuTraId;
    }
}