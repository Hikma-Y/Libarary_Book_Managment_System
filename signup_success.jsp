<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Sign Up Successful</title>
  <link rel="stylesheet" href="assets/styles.css">
</head>
<body>
  <%@ include file="/WEB-INF/jspf/header.jspf" %>
  <div class="container">
    <div class="card center">
      <h2 class="success">Account created successfully!</h2>
      <p>You can now log in.</p>
      <a class="btn primary" href="login.jsp">Go to Login</a>
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
