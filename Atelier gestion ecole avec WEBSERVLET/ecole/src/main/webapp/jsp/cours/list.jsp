<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.ecole.Model.Cour" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.ecole.Model.Filiere" %>
<%
    List<Cour> cours = (List<Cour>) request.getAttribute("cours");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Cours</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .filiere-badge {
            display: inline-block;
            background: #e0e0e0;
            padding: 2px 8px;
            margin: 2px;
            border-radius: 12px;
            font-size: 12px;
        }
    </style>
</head>
<body>
<h2>Liste des Cours</h2>

<a href="cours?action=new">Nouveau Cours</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Intitulé</th>
        <th>Filieres Associées</th>
        <th>Actions</th>
    </tr>
    <% for (Cour cour : cours) { %>
    <tr>
        <td><%= cour.getId() %></td>
        <td><%= cour.getCode() %></td>
        <td><%= cour.getIntitule() %></td>
        <td>
            <% if (cour.getFilieres() != null && !cour.getFilieres().isEmpty()) { %>
            <% for (Filiere filiere : cour.getFilieres()) { %>
            <span class="filiere-badge">
                                <%= filiere.getNom() %> (<%= filiere.getCode() %>)
                            </span>
            <% } %>
            <% } else { %>
            <span style="color: #999;">Aucune filière</span>
            <% } %>
        </td>
        <td>
            <a href="cours?action=edit&id=<%= cour.getId() %>">Modifier</a>
            |
            <a href="cours?action=delete&id=<%= cour.getId() %>"
               onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce cours ?')">Supprimer</a>
        </td>
    </tr>
    <% } %>
</table>

<br>
<a href="index.jsp">Accueil</a>
</body>
</html>