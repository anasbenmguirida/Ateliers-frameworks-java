<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="org.example.first_boot_data.entity.Eleve" %>
<%
    List<Eleve> eleves = (List<Eleve>) request.getAttribute("eleves");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Élèves</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:hover { background-color: #f5f5f5; }
    </style>
</head>
<body>
<h2>Liste des Élèves</h2>

<a href="ajouter">Nouvel Élève</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Matricule</th>
        <th>Nom Cmplet</th>
        <th>Filière</th>
        <th>Actions</th>
    </tr>
    <% for (Eleve eleve : eleves) { %>
    <tr>
        <td><%= eleve.getId() %></td>
        <td><%= eleve.getMatricule() %></td>
        <td><%= eleve.getNomComplet() %></td>
        <td>
            <% if (eleve.getFiliere() != null) { %>
            <%= eleve.getFiliere().getNomFiliere() %> (<%= eleve.getFiliere().getNomFiliere() %>)
            <% } else { %>
            <span style="color: #999;">Aucune filière</span>
            <% } %>
        </td>
        <td>
            <form><a href="edit/<%= eleve.getId() %>">Modifier</a></form>
            |
            <a href="delete/<%= eleve.getId() %>"
               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet élève ?')">Supprimer</a>
        </td>
    </tr>
    <% } %>
</table>

<br>
<a href="/">Accueil</a>
</body>
</html>