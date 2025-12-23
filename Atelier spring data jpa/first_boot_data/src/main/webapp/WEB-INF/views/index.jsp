<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion Académique</title>
    <style>
        .menu { list-style-type: none; padding: 0; }
        .menu li { margin: 10px 0; }
        .menu a {
            display: block;
            padding: 10px;
            background-color: #f0f0f0;
            text-decoration: none;
            border-radius: 5px;
            width: 200px;
        }
        .menu a:hover { background-color: #e0e0e0; }
    </style>
</head>
<body>
<h1>Gestion Académique</h1>

<ul class="menu">
    <li><a href="eleve/all">Gestion des Élèves</a></li>
    <li><a href="filieres/all">Gestion des Filières</a></li>
    <li><a href="cours/all">Gestion des Cours</a></li>
</ul>
</body>
</html>