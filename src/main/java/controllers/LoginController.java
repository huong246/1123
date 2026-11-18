package controllers;

import dao.ThanhVienDAO;
import model.ThanhVien;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        ThanhVienDAO tvDAO = new ThanhVienDAO();
        ThanhVien thanhVien = tvDAO.kiemTraDangNhap(user, pass);

        if (thanhVien != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", thanhVien);
            if (thanhVien.getRole().equals("BanDoc")) {
                response.sendRedirect("GDChinhBD.jsp");
            } else {
                response.sendRedirect("GDChinhNV.jsp");
            }
        } else {
            request.setAttribute("errorMsg", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
