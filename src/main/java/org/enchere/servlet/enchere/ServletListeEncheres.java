package org.enchere.servlet.enchere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Permet de lister les enchères en cours en mode déconnecté
 * Filtre la recherche par catégorie et par nom d'article
 */

@WebServlet (name = "ServletListeEncheres", value = "/ListeEncheres")
public class ServletListeEncheres extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletListeEncheres(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //forward sur la liste des enchères quand l'utilisateur est non connecté
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //l'utilisateur filtre la recherche
    }


}

