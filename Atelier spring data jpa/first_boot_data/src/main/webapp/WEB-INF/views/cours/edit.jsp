<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.first_boot_data.entity.Cour" %>
<%@ page import="org.example.first_boot_data.entity.Filiere" %>

<%
    Cour cour = (Cour) request.getAttribute("cour");
    List<Filiere> allFilieres = (List<Filiere>) request.getAttribute("allFilieres");
    List<Integer> selectedFiliereIds = (List<Integer>) request.getAttribute("selectedFiliereIds");

    boolean isEdit = cour != null && cour.getId() != 0;
    String title = isEdit ? "Modifier le Cours" : "Nouveau Cours";
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 800px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 120px; font-weight: bold; }
        input[type="text"], textarea {
            width: 300px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        textarea { width: 500px; height: 100px; }
        .checkbox-group {
            margin-top: 10px;
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 4px;
            max-height: 200px;
            overflow-y: auto;
        }
        .checkbox-item { margin-bottom: 8px; }
        .error {
            color: #d32f2f;
            background-color: #ffebee;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 15px;
        }
        .success {
            color: #388e3c;
            background-color: #e8f5e9;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 15px;
        }
        .actions { margin-top: 20px; }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary {
            background-color: #2196f3;
            color: white;
        }
        .btn-secondary {
            background-color: #757575;
            color: white;
        }
        .btn-danger {
            background-color: #f44336;
            color: white;
        }
        .btn:hover { opacity: 0.9; }
        .required { color: #f44336; }
        .form-section {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2><%= title %></h2>

    <%-- Affichage des messages --%>
    <c:if test="${not empty error}">
        <div class="error">
            <strong>Erreur :</strong> ${error}
        </div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="success">
            <strong>Succès :</strong> ${success}
        </div>
    </c:if>

    <div class="form-section">
        <form action="<%= request.getContextPath() %>/cours/save" method="post">

            <%-- Champ caché pour l'ID en mode édition --%>
            <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= cour.getId() %>">
            <% } %>

            <div class="form-group">
                <label for="code">Code <span class="required">*</span></label>
                <input type="text" id="code" name="code"
                       value="<%= isEdit ? cour.getCode() : "" %>"
                       required
                       placeholder="Ex: MTH101">
            </div>

            <div class="form-group">
                <label for="nom">Intitulé <span class="required">*</span></label>
                <input type="text" id="nom" name="nom"
                       value="<%= isEdit ? cour.getNom() : "" %>"
                       required
                       placeholder="Ex: Mathématiques">
            </div>


            <div class="form-group">
                <label>Filieres Associées</label>
                <div class="checkbox-group">
                    <%
                        if (allFilieres != null) {
                            for (Filiere filiere : allFilieres) {
                                boolean isChecked = false;
                                if (selectedFiliereIds != null) {
                                    isChecked = selectedFiliereIds.contains(filiere.getId());
                                } else if (isEdit && cour.getFilieres() != null) {
                                    isChecked = cour.getFilieres().stream()
                                            .anyMatch(f -> f.getId() == (filiere.getId()));
                                }
                    %>
                    <div class="checkbox-item">
                        <input type="checkbox"
                               id="filiere_<%= filiere.getId() %>"
                               name="filieres"
                               value="<%= filiere.getId() %>"
                            <%= isChecked ? "checked" : "" %>>
                        <label for="filiere_<%= filiere.getId() %>">
                            <%= filiere.getNomFiliere() %>
                        </label>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <p>Aucune filière disponible</p>
                    <% } %>
                </div>
                <small>Cochez les filières qui dispensent ce cours</small>
            </div>




            <div class="actions">
                <button type="submit" class="btn btn-primary">
                    <%= isEdit ? "Mettre à jour" : "Créer" %>
                </button>

                <% if (isEdit) { %>
                <a href="<%= request.getContextPath() %>/cours/delete/<%= cour.getId() %>"
                   class="btn btn-danger"
                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce cours ? Cette action est irréversible.');">
                    Supprimer
                </a>
                <% } %>

                <a href="<%= request.getContextPath() %>/cours/all" class="btn btn-secondary">
                    Annuler
                </a>
            </div>
        </form>
    </div>




</body>
</html>