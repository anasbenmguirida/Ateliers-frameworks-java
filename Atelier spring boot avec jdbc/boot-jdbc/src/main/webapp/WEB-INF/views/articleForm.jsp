<%@ page import="org.example.bootjdbc.model.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
  String error = (String) request.getAttribute("error");
  Article article = (Article) request.getAttribute("article");
  if (article == null) {
    article = new Article();
  }
%>
<html>
<head>
  <title>Nouvel Article</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center mb-4">Nouvel Article</h1>

  <% if (error != null) { %>
  <div class="alert alert-danger"><%= error %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/ajouter" method="post" class="card p-4">
    <div class="mb-3">
      <label for="code" class="form-label">Code *</label>
      <input type="text" class="form-control" id="code" name="code"
             value="<%= article.getCode() !=0? article.getCode() : "" %>"
             required maxlength="50">
      <div class="form-text">Le code doit être unique.</div>
    </div>

    <div class="mb-3">
      <label for="designation" class="form-label">Désignation *</label>
      <input type="text" class="form-control" id="designation" name="designation"
             value="<%= article.getDesignation() != null ? article.getDesignation() : "" %>"
             required maxlength="255">
    </div>

    <div class="mb-3">
      <label for="prix" class="form-label">Prix (Dhs) *</label>
      <input type="number" step="0.01" min="0" class="form-control" id="prix" name="prix"
             value="<%= article.getPrix() > 0 ? article.getPrix() : "" %>"
             required>
    </div>

    <div class="d-flex justify-content-between">
      <button type="submit" class="btn btn-primary">Créer</button>
      <a href="${pageContext.request.contextPath}/app/list" class="btn btn-secondary">Retour à la liste</a>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>