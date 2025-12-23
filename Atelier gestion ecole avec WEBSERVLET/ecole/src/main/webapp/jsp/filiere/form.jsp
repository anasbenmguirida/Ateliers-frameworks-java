<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.ecole.Model.Filiere" %>
<%
    Filiere filiere = (Filiere) request.getAttribute("filiere");
    boolean isEdit = filiere != null && filiere.getId() != null;
    String action = isEdit ? "update" : "create";
    String title = isEdit ? "Modifier la Filière" : "Nouvelle Filière";
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input[type="text"], textarea { width: 300px; padding: 5px; }
        textarea { height: 80px; }
    </style>
</head>
<body>
<h2><%= title %></h2>

<form action="filieres" method="post">
    <input type="hidden" name="action" value="<%= action %>">

    <% if (isEdit) { %>
    <input type="hidden" name="id" value="<%= filiere.getId() %>">
    <% } %>

    <div class="form-group">
        <label for="code">Code:</label>
        <input type="text" id="code" name="code"
               value="<%= isEdit ? filiere.getCode() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom"
               value="<%= isEdit ? filiere.getNom() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description"><%= isEdit && filiere.getDescription() != null ? filiere.getDescription() : "" %></textarea>
    </div>

    <div class="form-group">
        <input type="submit" value="<%= isEdit ? "Modifier" : "Créer" %>">
        <a href="filieres?action=list" style="margin-left: 10px;">Annuler</a>
    </div>
</form>

<br>
<a href="index.jsp">Accueil</a>
</body>
</html>