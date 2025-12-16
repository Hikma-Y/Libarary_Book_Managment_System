<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Librarian Login</title>
  <link rel="stylesheet" href="assets/styles.css">
  <script>
    function validateLogin() {
      const u = document.getElementById('username').value.trim();
      const p = document.getElementById('password').value;
      let ok = true, msg = '';
      if (!u) { ok = false; msg += 'Username is required. '; }
      if (!p) { ok = false; msg += 'Password is required.'; }
      document.getElementById('err').textContent = ok ? '' : msg;
      return ok;
    }
  </script>

  <!-- Inline style just for this page -->
  <style>
    body {
      margin: 0;
      height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      background: linear-gradient(180deg, #0f172a 0%, #1e293b 100%);
    }
  </style>
</head>
<body>
  <%@ include file="/WEB-INF/jspf/header.jspf" %>

  <div class="container">
    <div class="card" style="max-width:480px;margin:0 auto;">
      <h2>Login</h2>
      <form method="post" action="LoginServlet" onsubmit="return validateLogin()">
        <div id="err" class="form-error"></div>
        <label>Username</label>
        <input id="username" name="username" required>
        <label>Password</label>
        <input id="password" type="password" name="password" required minlength="6">
        <button class="btn primary" type="submit">Sign In</button>
      </form>
      <p class="center" style="margin-top:12px;">No account? <a class="btn" href="signup.jsp">Create one</a></p>
      <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
      %>
        <p class="form-error"><%= error %></p>
      <% } %>
    </div>
  </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
