package org.enchere.servlet.enchere;

import org.enchere.bll.ArticleManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


/**
 * Permet de lister les enchères en cours en mode déconnecté
 * Filtre la recherche par catégorie et par nom d'article
 */

@WebServlet (name = "ServletListeEncheres", value = "/ListeEncheres")
public class ServletListeEncheres extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletListeEncheres() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //forward sur la liste des enchères quand l'utilisateur est non connecté
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //                    if (request.getParameter("choix") == null){
//                        nomArticle = request.getParameter("recherche");
//                        request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
//                        request.setAttribute("articles", ArticleManager.findByNomArticle(nomArticle));
//                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
//                        rd.forward(request, response);
//                    }else if(request.getParameter("choix")!= null ){
//                        request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
//                        request.setAttribute("articles", selectCond(request.getParameter("recherche"), Integer.parseInt(request.getParameter("categories")), request.getParameter("choix"), utilisateur.getNoUtilisateur()));
//                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
//                        rd.forward(request, response);
//                    }


    }
}


