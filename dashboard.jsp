<%@ page contentType="text/html;charset=UTF-8" %>
<%
  if (session.getAttribute("user") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard</title>
  <link rel="stylesheet" href="assets/styles.css">
</head>
<body>
  <%@ include file="/WEB-INF/jspf/header.jspf" %>
  <div class="container">
    <div class="card">
      <h2>Dashboard</h2>
      <p>Welcome, <strong><%= ((com.example.library.models.Librarian)session.getAttribute("user")).getFirstname() %></strong>!</p>
      <div class="row" style="margin-top:12px;">
        <a class="btn success" href="addBook.jsp">Add New Book</a>
        <a class="btn" href="BookListServlet">View Books</a>
      </div>
    </div>
  </div>
      <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
