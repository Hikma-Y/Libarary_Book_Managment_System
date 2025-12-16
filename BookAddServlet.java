package com.example.library.servlets;

import com.example.library.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;


public class BookAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show addBook page if accessed via GET
        Object u = req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        req.getRequestDispatcher("addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object u = req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String category = req.getParameter("category");
        String amount = req.getParameter("amount"); // "1" or "0"

        // Basic validation
        if (isEmpty(title) || isEmpty(author) || isEmpty(category)) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("addBook.jsp").forward(req, resp);
            return;
        }

        String titleEsc = esc(title);
        String authorEsc = esc(author);
        String categoryEsc = esc(category);
        int amountInt = Integer.parseInt(amount);


        // Build concatenated INSERT statement
        String insertStmt =
                "INSERT INTO books (title, author, category, amount) VALUES (" +
                        "'" + titleEsc + "', " +
                        "'" + authorEsc + "', " +
                        "'" + categoryEsc + "', " +
                        amountInt +
                        ")";

        try (Connection c = DBUtil.getConnection();
             Statement stmt = c.createStatement()) {

            stmt.executeUpdate(insertStmt);
            resp.sendRedirect("BookListServlet");
            return;
        } catch (Exception e) {
            req.setAttribute("error", "Failed to add book: " + e.getMessage());
            req.getRequestDispatcher("addBook.jsp").forward(req, resp);
            return;
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // Escape single quotes to reduce SQL syntax issues (not a full SQL-injection fix)
    private String esc(String s) {
        if (s == null) return "";
        return s.replace("'", "''").trim();
    }
}
