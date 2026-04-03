package controllers;

import murach.sql.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookcode = request.getParameter("bookcode");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        HttpSession session = request.getSession();
        session.setAttribute("savedCategory", category);
        System.out.println("SESSION: " + session.getAttribute("savedCategory"));

        boolean hasError = false;

        if (bookcode == null || bookcode.trim().isEmpty()) {
            request.setAttribute("errorBookcode", "Không được để trống");
            hasError = true;
        } else if (bookDAO.isBookCodeExists(bookcode)) {
            request.setAttribute("errorBookcode", "Bookcode đã tồn tại");
            hasError = true;
        }

        if (title == null || title.trim().isEmpty()) {
            request.setAttribute("errorTitle", "Không được để trống");
            hasError = true;
        }
        if (author == null || author.trim().isEmpty()) {
            request.setAttribute("errorAuthor", "Không được để trống");
            hasError = true;
        }

        if (category == null || category.trim().isEmpty()) {
            request.setAttribute("errorCategory", "Vui lòng chọn thể loại");
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("bookcode", bookcode);
            request.setAttribute("title", title);
            request.setAttribute("author", author);

            //session.removeAttribute("savedCategory");
            request.getRequestDispatcher("add_book.jsp").forward(request, response);
        } else {
            request.setAttribute("bookcode", bookcode);
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("category", category);

            request.getRequestDispatcher("confirm_book.jsp").forward(request, response);
        }
    }
}

