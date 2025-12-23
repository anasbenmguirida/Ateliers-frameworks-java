package org.example.ecole.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecole.Model.Filiere;
import org.example.ecole.service.FiliereDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/filieres")
public class FiliereController extends HttpServlet {

    private FiliereDAO filiereDAO;

    @Override
    public void init() throws ServletException {
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
                    deleteFiliere(request, response);
                    break;
                case "list":
                default:
                    listFilieres(request, response);
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
                createFiliere(request, response);
            } else if ("update".equals(action)) {
                updateFiliere(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listFilieres(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filiere> filieres = filiereDAO.getAll();
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/jsp/filiere/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/filiere/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Filiere filiere = filiereDAO.getById(id);
        request.setAttribute("filiere", filiere);
        request.getRequestDispatcher("/jsp/filiere/form.jsp").forward(request, response);
    }

    private void createFiliere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        Filiere filiere = new Filiere();
        filiere.setCode(code);
        filiere.setNom(nom);
        filiere.setDescription(description);

        filiereDAO.create(filiere);
        response.sendRedirect("filieres?action=list");
    }

    private void updateFiliere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        Filiere filiere = new Filiere();
        filiere.setId(id);
        filiere.setCode(code);
        filiere.setNom(nom);
        filiere.setDescription(description);

        filiereDAO.update(filiere);
        response.sendRedirect("filieres?action=list");
    }

    private void deleteFiliere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        filiereDAO.delete(id);
        response.sendRedirect("filieres?action=list");
    }
}