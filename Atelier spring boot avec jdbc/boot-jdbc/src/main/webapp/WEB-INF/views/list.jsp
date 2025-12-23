<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Liste des Articles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Liste des Articles</h1>

    <div class="d-flex justify-content-between mb-3">
        <a href="${pageContext.request.contextPath}/ajouter" class="btn btn-success">Nouvel Article</a>
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Accueil</a>
    </div>

    <%-- Vérification si la liste "articles" existe et n'est pas vide --%>
    <c:choose>
        <c:when test="${not empty articles}">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                <tr>
                    <th>Code</th>
                    <th>Désignation</th>
                    <th>Prix</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <%-- Boucle sur la liste "articles" --%>
                <c:forEach var="article" items="${articles}">
                    <tr>
                            <%-- IMPORTANT : Utilisez les propriétés directement, pas les getters --%>
                        <td>${article.code}</td>
                        <td>${article.designation}</td>
                        <td>${article.prix} Dhs</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/editArticle?code=${article.code}"
                               class="btn btn-warning btn-sm">Modifier</a>
                            <a href="${pageContext.request.contextPath}/delete?code=${article.code}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet article ?')">
                                Supprimer
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info text-center">
                Aucun article trouvé.
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>