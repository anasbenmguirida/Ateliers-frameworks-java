<%@ page import="org.example.bootjdbc.model.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Article article = (Article) request.getAttribute("article");
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>Modifier Article</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Modifier l'Article</h1>

    <% if (article != null) { %>
    <form action="<%= contextPath %>/modifier" method="post" class="card p-4">
        <input type="hidden" name="oldCode" value="<%= article.getCode() %>">

        <div class="mb-3">
            <label for="code" class="form-label">Code *</label>
            <input type="text" class="form-control" id="code" name="code"
                   value="<%= article.getCode() %>" required>
        </div>

        <div class="mb-3">
            <label for="designation" class="form-label">Désignation *</label>
            <input type="text" class="form-control" id="designation" name="designation"
                   value="<%= article.getDesignation() %>" required>
        </div>

        <div class="mb-3">
            <label for="prix" class="form-label">Prix *</label>
            <input type="number" step="0.01" class="form-control" id="prix" name="prix"
                   value="<%= article.getPrix() %>" required>
        </div>

        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Mettre à jour</button>
            <a href="<%= contextPath %>/list" class="btn btn-secondary">Retour</a>
        </div>
    </form>
    <% } else { %>
    <div class="alert alert-danger">Article non trouvé</div>
    <a href="<%= contextPath %>/list" class="btn btn-secondary">Retour</a>
    <% } %>
</div>
</body>
</html>