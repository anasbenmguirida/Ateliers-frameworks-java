<%@ page import="org.example.first_boot_data.entity.Filiere" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Filiere filiere = (Filiere) request.getAttribute("filieres");
    boolean isEdit = filiere != null && filiere.getId() !=0;
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

<form action="${pageContext.request.contextPath}/filieres/save" method="post">
    <input type="hidden" name="action" value="<%= action %>">

    <% if (isEdit) { %>
    <input type="hidden" name="id" value="<%= filiere.getId() %>">
    <% } %>

    <div class="form-group">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nomFiliere"
               value="<%= isEdit ? filiere.getNomFiliere() : "" %>" required>
    </div>
    <div class="form-group">
        <label for="description">Description:</label>
        <input type="text" id="description" name="description"
               value="<%= isEdit ? filiere.getNomFiliere() : "" %>" required>
    </div>


    <div class="form-group">
        <input type="submit" value="<%= isEdit ? "Modifier" : "Créer" %>">
        <a href="all" style="margin-left: 10px;">Annuler</a>
    </div>
</form>

<br>
<a href="index.jsp">Accueil</a>
</body>
</html>