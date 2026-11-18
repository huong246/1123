package controllers;

import dao.BanDocDAO;
import dao.TheBanDocDAO;
import model.BanDoc;
import model.TheBanDoc;
import model.ThanhVien;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;
@WebServlet("/DangKyTheController")
public class DangKyTheController extends HttpServlet {
    private TheBanDocDAO theDAO = new TheBanDocDAO();
    private BanDocDAO bdDAO = new BanDocDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ThanhVien currentUser = (ThanhVien) request.getSession().getAttribute("user");
        TheBanDoc theCuaToi = theDAO.getTheCuaBD(currentUser.getId());
        if (theCuaToi != null) {
            request.setAttribute("theBanDoc", theCuaToi);
        } else {
            BanDoc thongTinBD = bdDAO.getThongTinBD(currentUser.getId());
            request.setAttribute("thongTinBD", thongTinBD);
        }
        request.getRequestDispatcher("GDTheBanDoc.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ThanhVien currentUser = (ThanhVien) request.getSession().getAttribute("user");
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String sdt = request.getParameter("dienThoai");
        String diaChi = request.getParameter("diaChi");
        boolean hopLe = bdDAO.kiemTraLienHe(email, sdt, currentUser.getId());
        if (!hopLe) {
            request.setAttribute("errorMsg", "Email hoặc Số điện thoại đã được sử dụng cho thẻ khác!");
            doGet(request, response);
            return;
        }
        TheBanDoc theMoi = new TheBanDoc();
        theMoi.setMaThe("LIB-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());
        theMoi.setNgayDangKy(new java.sql.Date(System.currentTimeMillis()));
        theMoi.setNgayHetHan(new java.sql.Date(System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000)));
        BanDoc bdUpdate = new BanDoc();
        bdUpdate.setEmail(email);
        bdUpdate.setDienThoai(sdt);
        theMoi.setBanDoc(bdUpdate);
        theDAO.luuThe(theMoi, currentUser.getId(), diaChi);
        TheBanDoc theVuaTao = theDAO.getTheMoi(currentUser.getId());
        request.setAttribute("theBanDoc", theVuaTao);
        request.setAttribute("successMsg", "Đăng ký thẻ thành công!");
        request.getRequestDispatcher("GDTheBanDoc.jsp").forward(request, response);
    }
}