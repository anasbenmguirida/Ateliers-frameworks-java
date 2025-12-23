<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.first_boot_data.entity.Eleve" %>
<%@ page import="org.example.first_boot_data.entity.Filiere" %>

<%
    Eleve eleve = (Eleve) request.getAttribute("eleve");
    List<Filiere> allFilieres = (List<Filiere>) request.getAttribute("allFilieres");

    boolean isEdit = eleve != null && eleve.getId() != 0;
    String title = isEdit ? "Modifier l'Élève" : "Nouvel Élève";

    // ID de la filière actuelle de l'élève (pour pré-sélection)
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
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 800px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 150px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="date"], select {
            width: 300px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        select { width: 320px; }
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
            font-size: 14px;
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
        .info-box {
            background-color: #e3f2fd;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 15px;
        }
        .field-hint {
            font-size: 12px;
            color: #666;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2><%= title %></h2>

    <%-- Affichage des messages --%>
    <% if (request.getAttribute("error") != null) { %>
    <div class="error">
        <strong>Erreur :</strong> <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <% if (request.getAttribute("success") != null) { %>
    <div class="success">
        <strong>Succès :</strong> <%= request.getAttribute("success") %>
    </div>
    <% } %>

    <div class="form-section">
        <form action="<%= request.getContextPath() %>/eleve/save" method="post">

            <%-- Champ caché pour l'ID en mode édition --%>
            <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= eleve.getId() %>">
            <% } %>

            <div class="form-group">
                <label for="matricule">Matricule <span class="required">*</span></label>
                <input type="text" id="matricule" name="matricule"
                       value="<%= isEdit ? eleve.getMatricule() : "" %>"
                       required
                       placeholder="Ex: E2024001"
                    <%= isEdit ? "readonly" : "" %>>
                <div class="field-hint">
                    <% if (isEdit) { %>
                    Le matricule ne peut pas être modifié
                    <% } else { %>
                    Code unique identifiant l'élève
                    <% } %>
                </div>
            </div>

            <div class="form-group">
                <label for="nomComplet">Nom Complet <span class="required">*</span></label>
                <input type="text" id="nomComplet" name="nomComplet"
                       value="<%= isEdit ? eleve.getNomComplet() : "" %>"
                       required
                       placeholder="Ex: Jean Dupont">
            </div>









            <div class="form-group">
                <label for="filiereId">Filière <span class="required">*</span></label>
                <select id="filiereId" name="filiere" required>
                    <option value="">-- Sélectionnez une filière --</option>
                    <%
                        if (allFilieres != null) {
                            for (Filiere filiere : allFilieres) {
                                boolean isSelected = false;
                                if (currentFiliereId != null) {
                                    isSelected = currentFiliereId.equals(filiere.getId());
                                }
                    %>
                    <option value="<%= filiere.getId() %>"
                            <%= isSelected ? "selected" : "" %>>
                        <%= filiere.getNomFiliere() %>
                    </option>
                    <%
                        }
                    } else {
                    %>
                    <option value="" disabled>Aucune filière disponible</option>
                    <% } %>
                </select>
                <div class="field-hint">La filière à laquelle l'élève est inscrit</div>
            </div>



            <div class="actions">
                <button type="submit" class="btn btn-primary">
                    <%= isEdit ? "Mettre à jour" : "Créer" %>
                </button>

                <a href="<%= request.getContextPath() %>/eleve/all" class="btn btn-secondary">
                    Annuler
                </a>
            </div>
        </form>
    </div>


</div>


</body>
</html>