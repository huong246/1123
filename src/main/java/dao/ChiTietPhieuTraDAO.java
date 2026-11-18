package dao;
import java.util.List;
import java.util.Map;
import model.ChiTietPhieuMuon;
import murach.sql.DAO;
public class ChiTietPhieuTraDAO extends DAO {
    public ChiTietPhieuTraDAO() {
        super();
    }
    public Map<String, Float> tinhPhatTraChamBanDau(List<ChiTietPhieuMuon> dsMuon) {
        java.util.Map<String, Float> mapPhat = new java.util.HashMap<>();
        for (ChiTietPhieuMuon ct : dsMuon) {
            long diff = System.currentTimeMillis() - ct.getHanTra().getTime();
            int daysLate = (int) Math.ceil((double) diff / (1000 * 3600 * 24));
            float phatMuon = daysLate > 0 ? daysLate * 5000f : 0f;
            mapPhat.put(String.valueOf(ct.getId()), phatMuon);
        }
        return mapPhat;
    }
    public void tinhPhat(List<ChiTietPhieuMuon> dsMuon, Map<Integer, String> tinhTrangTraMap,List<Map<String, Object>> dsHoaDon) {
        for (ChiTietPhieuMuon ct : dsMuon) {
            String tinhTrangTra = tinhTrangTraMap.get(ct.getId());
            if (tinhTrangTra == null) continue;

            String tinhTrangMuonGoc = ct.getTinhTrangMuon() != null ? ct.getTinhTrangMuon().trim() : "";
            tinhTrangTra = tinhTrangTra.trim();

            long diff = System.currentTimeMillis() - ct.getHanTra().getTime();
            int daysLate = (int) Math.ceil((double) diff / (1000 * 3600 * 24));
            float phatMuon = daysLate > 0 ? daysLate * 5000 : 0;

            float phatHong = 0;
            float gia = ct.getBanSao().getTaiLieu().getGia();

            if (!tinhTrangTra.equalsIgnoreCase(tinhTrangMuonGoc)) {
                if (tinhTrangTra.equalsIgnoreCase("Bẩn")) phatHong = gia * 0.05f;
                else if (tinhTrangTra.equalsIgnoreCase("Rách bìa")) phatHong = gia * 0.10f;
                else if (tinhTrangTra.equalsIgnoreCase("Rách trang")) phatHong = gia * 0.20f;
                else if (tinhTrangTra.equalsIgnoreCase("Mất sách")) phatHong = gia;
            }

            for (Map<String, Object> row : dsHoaDon) {
                if (row.get("maBanSao").equals(ct.getBanSao().getMaBanSao())) {
                    row.put("tinhTrangTra", tinhTrangTra);
                    row.put("phatMuon", phatMuon);
                    row.put("phatHong", phatHong);
                    row.put("tongPhat", phatMuon + phatHong);
                    break;
                }
            }
        }
    }
}