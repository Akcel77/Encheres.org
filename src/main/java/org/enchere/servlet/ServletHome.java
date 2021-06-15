package org.enchere.servlet;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.CategorieManager;
import org.enchere.bll.EnchereManager;
import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Enchere;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Renvois vers EnchereLog ou enchereNoLOg suivant si l'utilisateur est connecter ou non
 */
@WebServlet(name = "ServletHome", value = "/Encheres")
public class ServletHome extends HttpServlet {
    private static LocalDate today = LocalDate.now();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Démarre la session
        HttpSession httpSession = request.getSession();

        // Recupere la liste de toutes les categories
        CategorieManager categorieManager = new CategorieManager();
        List<Categorie> categoriesList = null;
        try {
            categoriesList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categoriesList);

        // recuperer la liste de tout les articles
        try {
            request.setAttribute("articles", ArticleManager.findAll());
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        // Si l'utilisateur est connecter redirige vers EnchereLog sinon EnchereNoLog
        if(httpSession.getAttribute("isConnected") == null ){
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
            requestDispatcher.forward(request,response);
        }else{
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
            requestDispatcher.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupere la liste de toutes les catégories
        Categorie categorie = new Categorie();
        List<Categorie> categoriesList = null;
        try {
            categoriesList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categoriesList);

        // Recupere l'utilisateur connecté
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");

        // Recupere tout les Parametre
        String choix = request.getParameter("choix");
        String numCategorie = request.getParameter("categories");
        String filter = request.getParameter("recherche");
        boolean enchereOuverte = request.getParameter("enchereOuverte").equals("on");
        boolean mesEncheres = request.getParameter("mesEncheres").equals("on");
        boolean enchereRemportee = request.getParameter("enchereRemportee").equals("on");
        boolean venteEncours = request.getParameter("venteEncours").equals("on");
        boolean nonDebute = request.getParameter("nonDebute").equals("on");
        boolean terminees = request.getParameter("terminees").equals("on");

        //DEBUG ZONE
        System.out.println(utilisateur != null ? "utilisateur connecté" : "utilisateur non connecter");
        System.out.println("choix " + choix);
        System.out.println("cat " + numCategorie);
        System.out.println("filter " + filter);
        System.out.println(enchereOuverte + " " + mesEncheres + " " + enchereRemportee + " " + venteEncours + " " + nonDebute + " " + terminees);


        // Si l'utilisateur est connecter redirige vers EnchereLog sinon EnchereNoLog
        if(httpSession.getAttribute("isConnected") == null ){
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
            requestDispatcher.forward(request,response);
        }else{
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
