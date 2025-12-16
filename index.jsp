<%@ page contentType="text/html;charset=UTF-8" %> 
<!DOCTYPE html>
<html>
<head>
  <title>Library Book Record</title>
  <link rel="stylesheet" href="assets/styles.css">

  <!-- Inline style just for this page -->
  <style>
    body {
      background: url('assets/library.jpg') center center / cover no-repeat fixed;
      background-color: #f5f5f5; /* fallback */
    }
  </style>
</head>
<body>
  <%@ include file="/WEB-INF/jspf/header.jspf" %>

  <!-- NEW: wrapper that centers between top fixed header and bottom of viewport -->
  <div style="min-height:calc(100vh - 70px);display:flex;align-items:center;justify-content:center;padding:24px;">
    <div class="container">
      <div class="card">
        <h1>Welcome to Library Book Record</h1>
        <p>Manage your library’s books: add, view, update, and delete records.</p>
        <div class="row" style="margin-top:12px;">
          <a class="btn primary" href="login.jsp">Login</a>
          <a class="btn success" href="signup.jsp">Sign Up</a>
        </div>
      </div>
    </div>
  </div>

  <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
