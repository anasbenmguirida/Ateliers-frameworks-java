package org.example.ecole.controllers;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecole.Model.Cour;
import org.example.ecole.Model.Filiere;
import org.example.ecole.service.CoursDAO;
import org.example.ecole.service.FiliereDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cours")
public class CoursController extends HttpServlet {

    private CoursDAO coursDAO;
    private FiliereDAO filiereDAO ;

    @Override
    public void init() throws ServletException {
        coursDAO = new CoursDAO();
        filiereDAO = new FiliereDAO() ;
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
                    deleteCours(request, response);
                    break;
                case "list":
                default:
                    listCours(request, response);
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
                createCours(request, response);
            } else if ("update".equals(action)) {
                updateCours(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listCours(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cour> cours = coursDAO.getAll();
        System.out.println(cours);
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/jsp/cours/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer toutes les filières pour la sélection multiple
        List<Filiere> filieres = filiereDAO.getAll();
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/jsp/cours/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Récupérer le cours avec ses filières
        Cour cour = coursDAO.getByIdWithFilieres(id);
        List<Filiere> filieres = filiereDAO.getAll();

        request.setAttribute("cour", cour);
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/jsp/cours/form.jsp").forward(request, response);
    }

    private void createCours(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String code = request.getParameter("code");
        String intitule = request.getParameter("intitule");
        String[] filiereIds = request.getParameterValues("filiereIds");

        try {
            // Créer le cours
            Cour cour = new Cour();
            cour.setCode(code);
            cour.setIntitule(intitule);

            coursDAO.create(cour);

            // Associer les filières sélectionnées
            if (filiereIds != null && filiereIds.length > 0) {
                List<Integer> ids = new ArrayList<>();
                for (String id : filiereIds) {
                    ids.add(Integer.parseInt(id));
                }
                coursDAO.associateFilieresToCour(cour.getId(), ids);
            }

            response.sendRedirect("cours?action=list");

        } catch (Exception e) {
            // En cas d'erreur, réafficher le formulaire
            List<Filiere> filieres = filiereDAO.getAll();
            request.setAttribute("filieres", filieres);
            request.setAttribute("error", "Erreur lors de la création: " + e.getMessage());
            request.getRequestDispatcher("/jsp/cours/form.jsp").forward(request, response);
        }
    }

    private void updateCours(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String code = request.getParameter("code");
        String intitule = request.getParameter("intitule");
        String[] filiereIds = request.getParameterValues("filiereIds");

        try {
            // Mettre à jour le cours
            Cour cour = new Cour();
            cour.setId(id);
            cour.setCode(code);
            cour.setIntitule(intitule);

            coursDAO.update(cour);

            // Mettre à jour les associations de filières
            if (filiereIds != null) {
                List<Integer> ids = new ArrayList<>();
                for (String filiereId : filiereIds) {
                    ids.add(Integer.parseInt(filiereId));
                }
                coursDAO.associateFilieresToCour(id, ids);
            } else {
                // Si aucune filière sélectionnée, vider les associations
                coursDAO.associateFilieresToCour(id, new ArrayList<>());
            }

            response.sendRedirect("cours?action=list");

        } catch (Exception e) {
            // En cas d'erreur, réafficher le formulaire
            List<Filiere> filieres = filiereDAO.getAll();
            request.setAttribute("filieres", filieres);
            request.setAttribute("error", "Erreur lors de la mise à jour: " + e.getMessage());
            request.getRequestDispatcher("/jsp/cours/form.jsp").forward(request, response);
        }
    }

    private void deleteCours(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        coursDAO.delete(id);
        response.sendRedirect("cours?action=list");
    }
}