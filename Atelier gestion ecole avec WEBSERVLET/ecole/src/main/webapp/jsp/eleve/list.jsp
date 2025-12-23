<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.ecole.Model.Eleve" %>
<%@ page import="java.util.List" %>
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

<a href="eleves?action=new">Nouvel Élève</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Matricule</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Email</th>
        <th>Filière</th>
        <th>Actions</th>
    </tr>
    <% for (Eleve eleve : eleves) { %>
    <tr>
        <td><%= eleve.getId() %></td>
        <td><%= eleve.getMatricule() %></td>
        <td><%= eleve.getNom() %></td>
        <td><%= eleve.getPrenom() %></td>
        <td><%= eleve.getEmail() %></td>
        <td>
            <% if (eleve.getFiliere() != null) { %>
            <%= eleve.getFiliere().getNom() %> (<%= eleve.getFiliere().getCode() %>)
            <% } else { %>
            <span style="color: #999;">Aucune filière</span>
            <% } %>
        </td>
        <td>
            <a href="eleves?action=edit&id=<%= eleve.getId() %>">Modifier</a>
            |
            <a href="eleves?action=delete&id=<%= eleve.getId() %>"
               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet élève ?')">Supprimer</a>
        </td>
    </tr>
    <% } %>
</table>

<br>
<a href="index.jsp">Accueil</a>
</body>
</html>