<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Book</title>
  <link rel="stylesheet" href="assets/styles.css">
  <script>
    function validateEdit() {
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
      <h2>Edit Book</h2>
      <div id="err" class="form-error"></div>
      <form method="post" action="BookUpdateServlet" onsubmit="return validateEdit()">
        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">

        <label>Title</label>
        <input id="title" name="title" value="<%= request.getAttribute("title") %>" required>

        <label>Author</label>
        <input id="author" name="author" value="<%= request.getAttribute("author") %>" required>

        <label>Category</label>
        <select id="category" name="category" required>
          <option value="Fiction" <%= "Fiction".equals(request.getAttribute("category")) ? "selected" : "" %>>Fiction</option>
          <option value="Non Fiction" <%= "Non Fiction".equals(request.getAttribute("category")) ? "selected" : "" %>>Non Fiction</option>
          <option value="Academic" <%= "Academic".equals(request.getAttribute("category")) ? "selected" : "" %>>Academic</option>
          <option value="Poetry" <%= "Poetry".equals(request.getAttribute("category")) ? "selected" : "" %>>Poetry</option>
          <option value="Memoir/Biography" <%= "Memoir/Biography".equals(request.getAttribute("category")) ? "selected" : "" %>>Memoir/Biography</option>
          <option value="Thriller" <%= "Thriller".equals(request.getAttribute("category")) ? "selected" : "" %>>Thriller</option>
          <option value="Classics" <%= "Classics".equals(request.getAttribute("category")) ? "selected" : "" %>>Classics</option>
          <option value="Religious" <%= "Religious".equals(request.getAttribute("category")) ? "selected" : "" %>>Religious</option>
        </select>

        <label>Amount</label>
        <input id="amount" name="amount" type="number" min="0" value="<%= request.getAttribute("amount") %>" required>

        <button class="btn primary" type="submit" name="action" value="update">Save Changes</button>
      </form>
    </div>
  </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
