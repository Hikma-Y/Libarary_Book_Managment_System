package com.example.library.servlets;

import com.example.library.models.Librarian;
import com.example.library.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String un = req.getParameter("username");
        String pw = req.getParameter("password");

        if (isEmpty(un) || isEmpty(pw)) {
            req.setAttribute("error", "Please provide username and password.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        String unEsc = esc(un);
        String pwHash = hash(pw);
        String pwEsc = esc(pwHash);

        String sql = "SELECT id, firstname, lastname, email, username " +
                     "FROM librarians WHERE username='" + unEsc + "' AND password_hash='" + pwEsc + "'";

        try (Connection c = DBUtil.getConnection();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                Librarian user = new Librarian(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("username")
                );
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);
                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("error", "Invalid credentials.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Login failed: " + e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    private boolean isEmpty(String s) { return s == null || s.trim().isEmpty(); }
    private String esc(String s) { return s == null ? "" : s.replace("'", "''").trim(); }

    private String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] out = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : out) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failure", e);
        }
    }
}
