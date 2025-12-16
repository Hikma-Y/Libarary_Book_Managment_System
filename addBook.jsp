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
  <title>Add Book</title>
  <link rel="stylesheet" href="assets/styles.css">
  <script>
    function validateBook() {
      const t = document.getElementById('title').value.trim();
      const a = document.getElementById('author').value.trim();
      const c = document.getElementById('category').value.trim();
      const m = document.getElementById('amount').value.trim();
      if (!t || !a || !c || !m) {
        document.getElementById('err').textContent = 'All fields are required.';
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
  <%@ include file="/WEB-INF/jspf/header.jspf" %>
  <div class="container">
    <div class="card" style="max-width:640px;margin:0 auto;">
      <h2>Add New Book</h2>
      <div id="err" class="form-error"></div>
      <form method="post" action="BookAddServlet" onsubmit="return validateBook()">
        <label>Title</label>
        <input id="title" name="title" required>

        <label>Author</label>
        <input id="author" name="author" required>

        <label>Category</label>
        <select id="category" name="category" required>
          <option value="">-- Select Category --</option>
          <option value="Fiction">Fiction</option>
          <option value="Non Fiction">Non Fiction</option>
          <option value="Academic">Academic</option>
          <option value="Poetry">Poetry</option>
          <option value="Memoir/Biography">Memoir/Biography</option>
          <option value="Thriller">Thriller</option>
          <option value="Classics">Classics</option>
          <option value="Religious">Religious</option>
        </select>

        <label>Amount</label>
        <input id="amount" name="amount" type="number" min="1" required>

        <button class="btn success" type="submit">Add Book</button>
      </form>

      <%
        String msg = (String) request.getAttribute("msg");
        String err = (String) request.getAttribute("error");
        if (msg != null) { %><p class="success"><%= msg %></p><% }
        if (err != null) { %><p class="form-error"><%= err %></p><% }
      %>
    </div>
  </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
