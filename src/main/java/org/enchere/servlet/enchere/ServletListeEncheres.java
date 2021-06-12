package org.enchere.servlet.enchere;

import org.enchere.bll.ArticleManager;
import org.enchere.bo.Articles;
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
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("isConnected") != null){

            try {
                if (request.getParameter("encheres") != null && request.getParameter("ventes") != null){
                    //TODO redirection
                    System.out.println("erreur");
                }else if(request.getParameter("encheres") != null || request.getParameter("ventes") != null){
                    String checkbox;
                    if (request.getParameter("encheres") != null){
                        checkbox="encheres";
                    }else {
                        checkbox="ventes";
                    }
                    request.setAttribute("encheres", selectCond(request.getParameter("nom"), Integer.parseInt(request.getParameter("categorie")),request.getParameter(checkbox), (int ) httpSession.getAttribute("isConnected")));
                }


                request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
                request.setAttribute("articles", ArticleManager.findAll());
                this.getServletContext().getRequestDispatcher("/WEB_INF/jsp/enchereLog.jsp").forward(request,response);

            }catch (SQLException | BusinessException b){
                b.printStackTrace();
            }
        }

    }
    private ArrayList<Articles> selectCond (String nom , int noCategorie, String checkbox, int noUtilisateur) throws BusinessException{
        ArrayList<Articles> articles  = null;
        String cond;
        Date date = new Date();
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String dateformatee = "" + simpleDateFormat.format(date);


        switch (checkbox) {
            case "enchereOuverte":
                cond = "AND a.date_debut_encheres<=" + dateformatee + " AND a.date_fin_encheres>" + dateformatee + " AND a.no_utilisateur<>" + noUtilisateur;
                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
                break;
            case "mesEncheres":
                cond = "AND a.date_debut_encheres<" + dateformatee + " AND a.date_fin_encheres>" + dateformatee + " AND a.no_utilisateur=" + noUtilisateur;
                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
                break;
            case "enchereRemportee":
                cond = "AND a.date_debut_encheres<" + dateformatee +" AND a.no_utilisateur=" + noUtilisateur;
                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
                break;
            case "vente":
                cond = "AND a.date_debut_encheres<=" + dateformatee + " AND a.date_fin_encheres>" + dateformatee + " AND a.no_utilisateur=" + noUtilisateur;
                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
                break;
            case "nonDebute":
                cond = "AND a.date_debut_encheres>" + dateformatee +" AND a.no_utilisateur<>" + noUtilisateur;
                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
                break;
            case "terminees":
                cond = "AND a.date_debut_encheres<" + dateformatee +" AND a.no_utilisateur<>" + noUtilisateur;
                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
                break;

        }return articles;


    }

}





