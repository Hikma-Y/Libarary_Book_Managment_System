package com.example.library.servlets;

import com.example.library.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Pattern;


public class SignupServlet extends HttpServlet {

    // Simple email regex for server-side validation
    private static final Pattern EMAIL_RE = Pattern.compile("^\\S+@\\S+\\.\\S+$");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show signup page if accessed by GET
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fn = req.getParameter("firstname");
        String ln = req.getParameter("lastname");
        String em = req.getParameter("email");
        String un = req.getParameter("username");
        String pw = req.getParameter("password");

        // Basic server-side validation
        if (isEmpty(fn) || isEmpty(ln) || isEmpty(em) || isEmpty(un) || isEmpty(pw)
                || pw.length() < 6 || un.length() < 3 || !EMAIL_RE.matcher(em).matches()) {
            req.setAttribute("error", "Please provide valid details. Password ≥ 6, Username ≥ 3, valid email.");
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
            return;
        }

        // Prepare escaped and hashed values
        String fnEsc = esc(fn);
        String lnEsc = esc(ln);
        String emEsc = esc(em);
        String unEsc = esc(un);
        String pwHash = hash(pw); // hashed as hex string
        String pwEsc = esc(pwHash);

        // Build the INSERT statement by concatenation
        String insertStmt =
                "INSERT INTO librarians (firstname, lastname, email, username, password_hash) VALUES (" +
                        "'" + fnEsc + "', " +
                        "'" + lnEsc + "', " +
                        "'" + emEsc + "', " +
                        "'" + unEsc + "', " +
                        "'" + pwEsc + "'" +
                        ")";

        try (Connection c = DBUtil.getConnection();
             Statement stmt = c.createStatement()) {

            // Execute the raw insert
            stmt.executeUpdate(insertStmt);

            // Forward to success page
            req.getRequestDispatcher("signup_success.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            // Could be duplicate key, DB error, etc.
            req.setAttribute("error", "Signup failed: " + e.getMessage());
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
            return;
        }
    }

    // Helper: basic emptiness check
    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // Helper: escape single quotes by doubling them (reduces syntax errors)
    private String esc(String s) {
        if (s == null) return "";
        return s.replace("'", "''").trim();
    }

    // SHA-256 hashing (hex)
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
