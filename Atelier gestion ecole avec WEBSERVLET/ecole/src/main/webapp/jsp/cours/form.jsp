<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.ecole.Model.Cour" %>
<%@ page import="org.example.ecole.Model.Filiere" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%
    Cour cour = (Cour) request.getAttribute("cour");
    List<Filiere> filieres = (List<Filiere>) request.getAttribute("filieres");
    boolean isEdit = cour != null && cour.getId() != null;
    String action = isEdit ? "update" : "create";
    String title = isEdit ? "Modifier le Cours" : "Nouveau Cours";

    // IDs des filières actuellement associées (pour pré-sélection)
    Set<Integer> currentFiliereIds = new HashSet<>();
    if (isEdit && cour.getFilieres() != null) {
        for (Filiere filiere : cour.getFilieres()) {
            currentFiliereIds.add(filiere.getId());
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; vertical-align: top; }
        input[type="text"], select { width: 300px; padding: 5px; }
        select[multiple] { height: 150px; }
        .error { color: red; margin-bottom: 10px; }
        .help-text { font-size: 12px; color: #666; margin-top: 5px; }
    </style>
</head>
<body>
<h2><%= title %></h2>

<% if (request.getAttribute("error") != null) { %>
<div class="error"><%= request.getAttribute("error") %></div>
<% } %>

<form action="cours" method="post">
    <input type="hidden" name="action" value="<%= action %>">

    <% if (isEdit) { %>
    <input type="hidden" name="id" value="<%= cour.getId() %>">
    <% } %>

    <div class="form-group">
        <label for="code">Code:</label>
        <input type="text" id="code" name="code"
               value="<%= isEdit ? cour.getCode() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="intitule">Intitulé:</label>
        <input type="text" id="intitule" name="intitule"
               value="<%= isEdit ? cour.getIntitule() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="filiereIds">Filieres:</label>
        <select id="filiereIds" name="filiereIds" multiple>
            <% for (Filiere filiere : filieres) { %>
            <option value="<%= filiere.getId() %>"
                    <%= currentFiliereIds.contains(filiere.getId()) ? "selected" : "" %>>
                <%= filiere.getNom() %> (<%= filiere.getCode() %>)
            </option>
            <% } %>
        </select>
        <div class="help-text">
            Maintenez Ctrl (ou Cmd sur Mac) pour sélectionner plusieurs filières
        </div>
    </div>

    <div class="form-group">
        <input type="submit" value="<%= isEdit ? "Modifier" : "Créer" %>">
        <a href="cours?action=list" style="margin-left: 10px;">Annuler</a>
    </div>
</form>

<br>
<a href="index.jsp">Accueil</a>
</body>
</html>