<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.ecole.Model.Eleve" %>
<%@ page import="org.example.ecole.Model.Filiere" %>
<%@ page import="java.util.List" %>
<%
    Eleve eleve = (Eleve) request.getAttribute("eleve");
    List<Filiere> filieres = (List<Filiere>) request.getAttribute("filieres");
    boolean isEdit = eleve != null && eleve.getId() != null;
    String action = isEdit ? "update" : "create";
    String title = isEdit ? "Modifier l'Élève" : "Nouvel Élève";

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

<form action="eleves" method="post">
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
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom"
               value="<%= isEdit ? eleve.getNom() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom"
               value="<%= isEdit ? eleve.getPrenom() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"
               value="<%= isEdit ? eleve.getEmail() : "" %>">
    </div>

    <div class="form-group">
        <label for="filiereId">Filière:</label>
        <select id="filiereId" name="filiereId">
            <option value="">-- Sélectionnez une filière --</option>
            <% for (Filiere filiere : filieres) { %>
            <option value="<%= filiere.getId() %>"
                    <%= (currentFiliereId != null && currentFiliereId.equals(filiere.getId())) ? "selected" : "" %>>
                <%= filiere.getNom() %> (<%= filiere.getCode() %>)
            </option>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <input type="submit" value="<%= isEdit ? "Modifier" : "Créer" %>">
        <a href="eleves?action=list" style="margin-left: 10px;">Annuler</a>
    </div>
</form>

<br>
<a href="index.jsp">Accueil</a>
</body>
</html>