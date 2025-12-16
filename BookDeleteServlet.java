package com.example.library.servlets;

import com.example.library.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "BookDeleteServlet", urlPatterns = {"/BookDeleteServlet"})
public class BookDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Require login
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            // No id provided; just go back to list
            response.sendRedirect("BookListServlet");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            String sql = "DELETE FROM books WHERE id=" + id;

            try (Connection c = DBUtil.getConnection();
                 Statement stmt = c.createStatement()) {
                stmt.executeUpdate(sql);
            }
        } catch (Exception e) {
            // Optional: you can surface this to the UI if you want
            // request.setAttribute("error", "Delete failed: " + e.getMessage());
            // request.getRequestDispatcher("books.jsp").forward(request, response);
            // But simplest for now: just redirect back to list regardless.
        }

        response.sendRedirect("BookListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Support POST as well by delegating to GET
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Deletes a book by id and redirects to the list.";
    }
}
