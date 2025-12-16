package com.example.library.servlets;

import com.example.library.models.Book;
import com.example.library.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object u = req.getSession().getAttribute("user");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, author, category, amount FROM books ORDER BY id DESC";

        try (Connection c = DBUtil.getConnection();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("amount")
                ));
            }
        } catch (Exception e) {
            // Optional: surface error
            req.setAttribute("error", "Failed to load books: " + e.getMessage());
        }

        req.setAttribute("books", books);
        req.getRequestDispatcher("books.jsp").forward(req, resp);
    }
}
