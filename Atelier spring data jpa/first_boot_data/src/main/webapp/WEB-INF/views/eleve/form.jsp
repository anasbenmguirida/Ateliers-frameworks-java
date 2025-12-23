<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="org.example.first_boot_data.entity.Eleve" %>
<%@ page import="org.example.first_boot_data.entity.Filiere" %>
<%
    Eleve eleve = (Eleve) request.getAttribute("eleve");

    boolean isEdit = eleve != null && eleve.getId() != 0;
    String action = isEdit ? "update" : "create";
    String title = isEdit ? "Modifier l'Élève" : "Nouvel Élève";
    List<Filiere> filieres = (List<Filiere>) request.getAttribute("filieres");

    // ID de la filière actuelle (pour pré-sélectionner dans le select)
    Integer currentFiliereId = null;
    if (isEdit && eleve.getFiliere() != null) {
        currentFiliereId = eleve.getFiliere().getId();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input[type="text"], input[type="email"], select { width: 300px; padding: 5px; }
        .error { color: red; margin-bottom: 10px; }
    </style>
</head>
<body>
<h2><%= title %></h2>

<%-- Affichage des erreurs --%>
<% if (request.getAttribute("error") != null) { %>
<div class="error"><%= request.getAttribute("error") %></div>
<% } %>

<form action=${pageContext.request.contextPath}/eleve/save method="post">
    <input type="hidden" name="action" value="<%= action %>">

    <% if (isEdit) { %>
    <input type="hidden" name="id" value="<%= eleve.getId() %>">
    <% } %>

    <div class="form-group">
        <label for="matricule">Matricule:</label>
        <input type="text" id="matricule" name="matricule"
               value="<%= isEdit ? eleve.getMatricule() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="nom">Nom Complet:</label>
        <input type="text" id="nom" name="nomComplet"
               value="<%= isEdit ? eleve.getNomComplet() : "" %>" required>
    </div>



    <div class="form-group">
        <label for="filiereId">Filière:</label>
        <select id="filiereId" name="filiere">
            <option value="">-- Sélectionnez une filière --</option>
            <% for (Filiere filiere : filieres) { %>
            <option value="<%= filiere.getId() %>"
                    <%= (currentFiliereId != null && currentFiliereId.equals(filiere.getId())) ? "selected" : "" %>>
                <%= filiere.getNomFiliere() %> 
            </option>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <input type="submit" value="<%= isEdit ? "Modifier" : "Créer" %>">
        <a href="all" style="margin-left: 10px;">Annuler</a>
    </div>
</form>

<br>
<a href="all">Accueil</a>
</body>
</html>