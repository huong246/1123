package controllers;

import murach.sql.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/confirmBook")
public class ConfirmBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("back".equals(action)) {
            request.setAttribute("bookcode", request.getParameter("bookcode"));
            request.setAttribute("title", request.getParameter("title"));
            request.setAttribute("author", request.getParameter("author"));

            request.getRequestDispatcher("add_book.jsp").forward(request, response);

        } else if ("confirm".equals(action)) {
            String bookcode = request.getParameter("bookcode");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String category = request.getParameter("category");

            bookDAO.insertBook(bookcode, title, author, category);
            response.sendRedirect("add_book.jsp");
        }
    }
}