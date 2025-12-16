<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.library.models.Book" %>
<%
  if (session.getAttribute("user") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  List<Book> list = (List<Book>) request.getAttribute("books");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Books</title>
  <link rel="stylesheet" href="assets/styles.css">
  <script>
    function confirmDelete(id) {
      if (confirm('Delete this book?')) {
        window.location = 'BookDeleteServlet?id=' + encodeURIComponent(id);
      }
    }
  </script>
</head>
<body>
  <%@ include file="/WEB-INF/jspf/header.jspf" %>
  <div class="container">
    <div class="card">
      <h2>Books</h2>
      <table class="table">
        <thead>
          <tr><th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Amount</th><th>Actions</th></tr>
        </thead>
        <tbody>
          <%
            if (list != null) {
              for (Book b : list) {
          %>
          <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getCategory() %></td>
            <td><%= b.getAmount() %></td>
            <td>
              <form method="post" action="BookUpdateServlet" style="display:inline;">
                <input type="hidden" name="id" value="<%= b.getId() %>">
                <input type="hidden" name="title" value="<%= b.getTitle() %>">
                <input type="hidden" name="author" value="<%= b.getAuthor() %>">
                <input type="hidden" name="category" value="<%= b.getCategory() %>">
                <input type="hidden" name="amount" value="<%= b.getAmount() %>">
                <button class="btn" type="submit" name="action" value="edit">Update</button>
              </form>
              <button class="btn danger" onclick="confirmDelete(<%= b.getId() %>)">Delete</button>
            </td>
          </tr>
          <%    }
             } %>
        </tbody>
      </table>
      <div style="margin-top:12px;">
        <a class="btn success" href="addBook.jsp">Add New Book</a>
      </div>
    </div>
  </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
