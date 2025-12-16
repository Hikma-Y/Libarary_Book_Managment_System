<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Librarian Sign Up</title>
  <link rel="stylesheet" href="assets/styles.css">
  <script>
    function validateSignup() {
      const fn = document.getElementById('firstname').value.trim();
      const ln = document.getElementById('lastname').value.trim();
      const em = document.getElementById('email').value.trim();
      const un = document.getElementById('username').value.trim();
      const pw = document.getElementById('password').value;
      let msg = [];
      if (!fn) msg.push('First name required.');
      if (!ln) msg.push('Last name required.');
      if (!em || !/^\S+@\S+\.\S+$/.test(em)) msg.push('Valid email required.');
      if (!un || un.length < 3) msg.push('Username min 3 chars.');
      if (!pw || pw.length < 6) msg.push('Password min 6 chars.');
      document.getElementById('err').innerHTML = msg.join(' ');
      return msg.length === 0;
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
    <div class="card" style="max-width:600px;margin:0 auto;">
      <h2>Create Librarian Account</h2>
      <div id="err" class="form-error"></div>
      <form method="post" action="SignupServlet" onsubmit="return validateSignup()">
        <div class="row">
          <div>
            <label>First Name</label>
            <input id="firstname" name="firstname" required>
          </div>
          <div>
            <label>Last Name</label>
            <input id="lastname" name="lastname" required>
          </div>
        </div>
        <label>Email</label>
        <input id="email" name="email" type="email" required>
        <div class="row">
          <div>
            <label>Username</label>
            <input id="username" name="username" required minlength="3">
          </div>
          <div>
            <label>Password</label>
            <input id="password" name="password" type="password" required minlength="6">
          </div>
        </div>
        <button class="btn success" type="submit">Sign Up</button>
      </form>
      <%
        String error = (String) request.getAttribute("error");
        String ok = (String) request.getAttribute("ok");
        if (error != null) { %><p class="form-error"><%= error %></p><% }
        if (ok != null) { %><p class="success"><%= ok %></p><% }
      %>
    </div>
  </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
