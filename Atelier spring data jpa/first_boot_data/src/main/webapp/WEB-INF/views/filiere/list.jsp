<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.first_boot_data.entity.Filiere" %>
<%
    List<Filiere> filieres = (List<Filiere>) request.getAttribute("filieres");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Filières</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h2>Liste des Filières</h2>

<a href="ajouter">Nouvelle Filière</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Nom Filiere</th>
        <th>Description </th>
        <th>Actions</th>
    </tr>
    <% for (Filiere filiere : filieres) { %>
    <tr>
        <td><%= filiere.getId() %></td>
        <td><%= filiere.getNomFiliere() %></td>
        <td><%= filiere.getDescription() != null ? filiere.getDescription() : "" %></td>
        <td>
            <a href="edit/<%= filiere.getId() %>">Modifier</a>
            |
            <a href="delete/<%= filiere.getId() %>"
               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette filière ?')">Supprimer</a>
        </td>
    </tr>
    <% } %>
</table>

<br>
<a href="/">Accueil</a>
</body>
</html>