package org.example.ecole.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecole.Model.Eleve;
import org.example.ecole.Model.Filiere;
import org.example.ecole.service.EleveDAO;
import org.example.ecole.service.FiliereDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/eleves")
public class EleveController extends HttpServlet {

    private EleveDAO eleveDAO;
    private FiliereDAO filiereDAO  ;

    @Override
    public void init() throws ServletException {
        eleveDAO = new EleveDAO();
        filiereDAO = new FiliereDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteEleve(request, response);
                    break;
                case "list":
                default:
                    listEleves(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                createEleve(request, response);
            } else if ("update".equals(action)) {
                updateEleve(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listEleves(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Eleve> eleves = eleveDAO.getAll();
        request.setAttribute("eleves", eleves);
        request.getRequestDispatcher("/jsp/eleve/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filiere> filieres = filiereDAO.getAll();
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/jsp/eleve/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Eleve eleve = eleveDAO.getById(id);

        // Récupérer la liste des filières pour le formulaire
        List<Filiere> filieres = filiereDAO.getAll();

        request.setAttribute("eleve", eleve);
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/jsp/eleve/form.jsp").forward(request, response);
    }

    private void createEleve(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String filiereIdStr = request.getParameter("filiereId");

        try {
            Eleve eleve = new Eleve();
            eleve.setMatricule(matricule);
            eleve.setNom(nom);
            eleve.setPrenom(prenom);
            eleve.setEmail(email);

            // Associer la filière si une ID est fournie
            if (filiereIdStr != null && !filiereIdStr.isEmpty()) {
                Integer filiereId = Integer.parseInt(filiereIdStr);
                Filiere filiere = filiereDAO.getById(filiereId);
                if (filiere != null) {
                    eleve.setFiliere(filiere);
                }
            }

            eleveDAO.create(eleve);
            response.sendRedirect("eleves?action=list");

        } catch (Exception e) {
            // En cas d'erreur, réafficher le formulaire avec les filières
            List<Filiere> filieres = filiereDAO.getAll();
            request.setAttribute("filieres", filieres);
            request.setAttribute("error", "Erreur lors de la création: " + e.getMessage());
            request.getRequestDispatcher("/jsp/eleve/form.jsp").forward(request, response);
        }
    }

    private void updateEleve(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String filiereIdStr = request.getParameter("filiereId");

        try {
            Eleve eleve = new Eleve();
            eleve.setId(id);
            eleve.setMatricule(matricule);
            eleve.setNom(nom);
            eleve.setPrenom(prenom);
            eleve.setEmail(email);

            // Associer la filière si une ID est fournie
            if (filiereIdStr != null && !filiereIdStr.isEmpty()) {
                Integer filiereId = Integer.parseInt(filiereIdStr);
                Filiere filiere = filiereDAO.getById(filiereId);
                if (filiere != null) {
                    eleve.setFiliere(filiere);
                }
            }

            eleveDAO.update(eleve);
            response.sendRedirect("eleves?action=list");

        } catch (Exception e) {
            // En cas d'erreur, réafficher le formulaire avec les filières
            List<Filiere> filieres = filiereDAO.getAll();
            request.setAttribute("filieres", filieres);
            request.setAttribute("error", "Erreur lors de la mise à jour: " + e.getMessage());
            request.getRequestDispatcher("/jsp/eleve/form.jsp").forward(request, response);
        }
    }


    private void deleteEleve(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        eleveDAO.delete(id);
        response.sendRedirect("eleves?action=list");
    }
}
