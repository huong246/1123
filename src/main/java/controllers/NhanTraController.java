package controllers;

import dao.*;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/NhanTraController")
public class NhanTraController extends HttpServlet {
    private TheBanDocDAO theDAO = new TheBanDocDAO();
    private ChiTietPhieuMuonDAO ctMuonDAO = new ChiTietPhieuMuonDAO();
    private ChiTietPhieuTraDAO ctTraDAO = new ChiTietPhieuTraDAO();
    private PhieuTraDAO phieuTraDAO = new PhieuTraDAO();
    private HoaDonPhatDAO hoaDonPhatDAO = new HoaDonPhatDAO();
    private BanSaoDAO banSaoDAO = new BanSaoDAO();
    private TaiLieuDAO taiLieuDAO = new TaiLieuDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            clearSessionNhanTra(session);
            request.getRequestDispatcher("GDNhanTraTaiLieu.jsp").forward(request, response);
        } else if (action.equals("search")) {
            String maBanDoc = request.getParameter("maBanDoc");
            clearSessionNhanTra(session);
            TheBanDoc theBD = theDAO.getTheVaBD(maBanDoc);
            if (theBD != null) {
                List<ChiTietPhieuMuon> dsMuon = ctMuonDAO.getDSMuon(theBD.getMaThe());
                Map<String, Float> rawMap = ctTraDAO.tinhPhatTraChamBanDau(dsMuon);
                Map<Integer, Float> mapPhatBanDau = new HashMap<>();
                for (Map.Entry<String, Float> entry : rawMap.entrySet()) {
                    mapPhatBanDau.put(Integer.parseInt(entry.getKey()), entry.getValue());
                }
                request.setAttribute("mapPhatBanDau", mapPhatBanDau);
                request.setAttribute("dsMuon", dsMuon);
                session.setAttribute("dsMuonCache", dsMuon);
                session.setAttribute("theBanDoc", theBD);
            } else {
                request.setAttribute("errorMsg", "Không tìm thấy thẻ hoặc thẻ đã bị khóa.");
            }
            request.getRequestDispatcher("GDNhanTraTaiLieu.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if ("taoPhieu".equals(action)) {
            String[] selectedIds = request.getParameterValues("ctMuonId");
            List<ChiTietPhieuMuon> dsMuonCache = (List<ChiTietPhieuMuon>) session.getAttribute("dsMuonCache");
            if (selectedIds == null || dsMuonCache == null) {
                response.sendRedirect("NhanTraController");
                return;
            }
            List<Map<String, Object>> dsHoaDon = new ArrayList<>();
            Map<Integer, String> tinhTrangTraMap = new HashMap<>();
            for (String idStr : selectedIds) {
                int id = Integer.parseInt(idStr);
                String tinhTrangTra = request.getParameter("tinhTrang_" + id);
                tinhTrangTraMap.put(id, tinhTrangTra);
                for (ChiTietPhieuMuon ct : dsMuonCache) {
                    if (ct.getId() == id) {
                        Map<String, Object> row = new HashMap<>();
                        row.put("ctMuonId", ct.getId());
                        row.put("banSaoId", ct.getBanSao().getId());
                        row.put("maBanSao", ct.getBanSao().getMaBanSao());
                        row.put("taiLieuId", ct.getBanSao().getTaiLieu().getId());
                        row.put("tenTaiLieu", ct.getBanSao().getTaiLieu().getTen());
                        row.put("ngayMuon", ct.getPhieuMuon().getNgayMuon());
                        row.put("hanTra", ct.getHanTra());
                        row.put("tinhTrangMuon", ct.getTinhTrangMuon() != null ? ct.getTinhTrangMuon().trim() : "");
                        row.put("tinhTrangTra", tinhTrangTra);
                        dsHoaDon.add(row);
                        break;
                    }
                }
            }
            ctTraDAO.tinhPhat(dsMuonCache, tinhTrangTraMap, dsHoaDon);
            float tongTienPhat = 0f;
            for (Map<String, Object> row : dsHoaDon) {
                if (row.containsKey("tongPhat")) tongTienPhat += (Float) row.get("tongPhat");
            }
            session.setAttribute("dsHoaDon", dsHoaDon);
            session.setAttribute("tongTienPhat", tongTienPhat);
            session.setAttribute("tongSoQuyen", dsHoaDon.size());
            session.setAttribute("phase", "preview");
            request.getRequestDispatcher("GDHoaDonTra.jsp").forward(request, response);
        }
        else if ("xacNhan".equals(action)) {
            List<Map<String, Object>> dsHoaDon = (List<Map<String, Object>>) session.getAttribute("dsHoaDon");
            TheBanDoc the = (TheBanDoc) session.getAttribute("theBanDoc");
            ThanhVien nv = (ThanhVien) session.getAttribute("user");
            Float tongTienPhat = (Float) session.getAttribute("tongTienPhat");
            if (dsHoaDon == null || the == null || nv == null) {
                response.sendRedirect("NhanTraController");
                return;
            }
            try {
                PhieuTra phieuTra = new PhieuTra();
                long currentMillis = System.currentTimeMillis();
                java.sql.Date sqlDate = new java.sql.Date(currentMillis);
                phieuTra.setNgayTra(sqlDate);
                phieuTra.setTongTienPhat(tongTienPhat);
                phieuTra.setTheBanDoc(the);
                NhanVienThuVien nvtv = new NhanVienThuVien();
                nvtv.setId(nv.getId());
                phieuTra.setNhanVienThuVien(nvtv);
                List<ChiTietPhieuTra> dsChiTiet = new ArrayList<>();
                List<ChiTietPhieuMuon> dsMuonCapNhat = new ArrayList<>();
                for (Map<String, Object> row : dsHoaDon) {
                    ChiTietPhieuTra ctTra = new ChiTietPhieuTra();
                    ctTra.setTinhTrangTra((String) row.get("tinhTrangTra"));
                    int soNgayTre = row.containsKey("soNgayTre") && row.get("soNgayTre") != null ? (Integer) row.get("soNgayTre") : 0;
                    float phatCham = row.containsKey("phatMuon") && row.get("phatMuon") != null ? (Float) row.get("phatMuon") : 0f;
                    float phatHong = row.containsKey("phatHong") && row.get("phatHong") != null ? (Float) row.get("phatHong") : 0f;
                    ctTra.setSoNgayTre(soNgayTre);
                    ctTra.setPhatTraCham(phatCham);
                    ctTra.setPhatHuHong(phatHong);
                    BanSao bs = new BanSao();
                    bs.setId((Integer) row.get("banSaoId"));
                    ctTra.setBanSao(bs);
                    dsChiTiet.add(ctTra);
                    ChiTietPhieuMuon ctMuon = new ChiTietPhieuMuon();
                    ctMuon.setId((Integer) row.get("ctMuonId"));
                    dsMuonCapNhat.add(ctMuon);
                }
                int maPhieuTra = phieuTraDAO.luuPhieu(phieuTra, dsChiTiet);
                if (maPhieuTra > 0) {
                    session.setAttribute("maPhieuTra", maPhieuTra);
                    ctMuonDAO.capNhatTra(dsMuonCapNhat);
                    List<BanSao> dsBanSaoCapNhat = new ArrayList<>();
                    Map<Integer, Integer> dsSoLuongTra = new HashMap<>();
                    for (Map<String, Object> row : dsHoaDon) {
                        String tinhTrangTra = (String) row.get("tinhTrangTra");
                        int banSaoId = (Integer) row.get("banSaoId");
                        int taiLieuId = (Integer) row.get("taiLieuId");
                        BanSao bs = new BanSao();
                        bs.setId(banSaoId);
                        bs.setTinhTrang(tinhTrangTra);
                        if (tinhTrangTra != null && tinhTrangTra.equalsIgnoreCase("Mất")) {
                            bs.setTrangThai("Mất");
                        } else {
                            bs.setTrangThai("Sẵn sàng");
                            dsSoLuongTra.put(taiLieuId, dsSoLuongTra.getOrDefault(taiLieuId, 0) + 1);
                        }
                        dsBanSaoCapNhat.add(bs);
                    }
                    banSaoDAO.capNhatKho(dsBanSaoCapNhat);
                    if (!dsSoLuongTra.isEmpty()) {
                        taiLieuDAO.capNhatSoLuongKho(dsSoLuongTra);
                    }
                    if (tongTienPhat > 0) {
                        float tongPhatCham = 0f, tongPhatHong = 0f;
                        for (Map<String, Object> row : dsHoaDon) {
                            tongPhatCham += row.get("phatMuon") != null ? (Float) row.get("phatMuon") : 0f;
                            tongPhatHong += row.get("phatHong") != null ? (Float) row.get("phatHong") : 0f;
                        }
                        HoaDonPhat hd = new HoaDonPhat();
                        hd.setNgayLap(sqlDate);
                        hd.setTongPhatCham(tongPhatCham);
                        hd.setTongPhatHuHong(tongPhatHong);
                        hd.setTongTienPhat(tongTienPhat);
                        hd.setTrangThai("Chưa thanh toán");
                        PhieuTra pt = new PhieuTra();
                        pt.setId(maPhieuTra);
                        hd.setPhieuTra(pt);
                        hoaDonPhatDAO.luuHD(hd);
                    }
                    session.setAttribute("phase", "confirmed");
                    response.sendRedirect("GDHoaDonTra.jsp");
                } else {
                    request.setAttribute("errorMsg", "Lưu phiếu trả không thành công. Hãy kiểm tra lại kết nối CSDL.");
                    request.getRequestDispatcher("GDHoaDonTra.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMsg", "Lỗi Server: " + e.getMessage());
                request.getRequestDispatcher("GDHoaDonTra.jsp").forward(request, response);
            }
        }
        else if ("thanhToan".equals(action)) {
            Integer maPhieuTra = (Integer) session.getAttribute("maPhieuTra");
            if (maPhieuTra != null) {
                boolean success = hoaDonPhatDAO.capNhatThanhToan(maPhieuTra);
                if (success) {
                    session.setAttribute("daThanhToan", true);
                }
            }
            response.sendRedirect("GDHoaDonTra.jsp");
        }
        else if ("hoanTat".equals(action)) {
            clearSessionNhanTra(session);
            response.sendRedirect("NhanTraController");
        }
    }
    private void clearSessionNhanTra(HttpSession session) {
        session.removeAttribute("theBanDoc");
        session.removeAttribute("dsMuonCache");
        session.removeAttribute("dsHoaDon");
        session.removeAttribute("tongTienPhat");
        session.removeAttribute("tongSoQuyen");
        session.removeAttribute("phase");
        session.removeAttribute("maPhieuTra");
        session.removeAttribute("daThanhToan");
    }
}