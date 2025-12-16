package com.example.library.servlets;

import com.example.library.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class BookUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object u = req.getSession().getAttribute("user");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            // preload values into edit form
            req.setAttribute("id", req.getParameter("id"));
            req.setAttribute("title", req.getParameter("title"));
            req.setAttribute("author", req.getParameter("author"));
            req.setAttribute("category", req.getParameter("category"));
            req.setAttribute("amount", req.getParameter("amount"));
            req.getRequestDispatcher("editBook.jsp").forward(req, resp);
            return;
        }

        if ("update".equals(action)) {
            String idStr = req.getParameter("id");
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String category = req.getParameter("category");
            String amountStr = req.getParameter("amount");

            if (isEmpty(idStr) || isEmpty(title) || isEmpty(author) || isEmpty(category)) {
                req.setAttribute("id", idStr);
                req.setAttribute("title", title);
                req.setAttribute("author", author);
                req.setAttribute("category", category);
                req.setAttribute("amount", amountStr);
                req.setAttribute("error", "All fields are required.");
                req.getRequestDispatcher("editBook.jsp").forward(req, resp);
                return;
            }

            int id;
            try { id = Integer.parseInt(idStr); } catch (Exception e) {
                resp.sendRedirect("BookListServlet"); return;
            }

            int amountInt = 0;
            try { amountInt = Integer.parseInt(amountStr); } catch (Exception ignored) {}

            String titleEsc = esc(title);
            String authorEsc = esc(author);
            String categoryEsc = esc(category);

            String sql = "UPDATE books SET " +
                    "title='" + titleEsc + "', " +
                    "author='" + authorEsc + "', " +
                    "category='" + categoryEsc + "', " +
                    "amount=" + amountInt + " " +
                    "WHERE id=" + id;

            try (Connection c = DBUtil.getConnection();
                 Statement stmt = c.createStatement()) {
                stmt.executeUpdate(sql);
                resp.sendRedirect("BookListServlet");
                return;
            } catch (Exception e) {
                req.setAttribute("id", id);
                req.setAttribute("title", title);
                req.setAttribute("author", author);
                req.setAttribute("category", category);
                req.setAttribute("amount", String.valueOf(amountInt));
                req.setAttribute("error", "Update failed: " + e.getMessage());
                req.getRequestDispatcher("editBook.jsp").forward(req, resp);
                return;
            }
        }

        resp.sendRedirect("BookListServlet");
    }

    private boolean isEmpty(String s) { return s == null || s.trim().isEmpty(); }
    private String esc(String s) { return s == null ? "" : s.replace("'", "''").trim(); }
}
