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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet pour savoir si un utlisateur est connecte ou non sur la page d'acceuil
 * Si il est connecte attribute("isConnected") renvoit sur enchereLog
 * Sinon renvoit sur enchereNoLog
 */
@WebServlet(name = "ServletHome", value = "/Encheres")
public class ServletHome extends HttpServlet {
    private static LocalDate today = LocalDate.now();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check si user est connecte dans la session
        //Si il est pas co > enchereNoLog
        //Sinon EnchereLog

        HttpSession httpSession = request.getSession();

        // Recupere la liste de toutes les categories pour le filtre
        CategorieManager categorieManager = new CategorieManager();
        List<Categorie> categoriesList = null;
        try {
            categoriesList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categoriesList);

        // recuperer la liste de tout les artciles
        List<Articles> articlesList = null;
        try {
            articlesList = ArticleManager.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listeArticles", articlesList );

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
        HttpSession httpSession = request.getSession();

        // recuperer la liste de tout les artciles
        List<Articles> articlesList = null;
        try {
            articlesList = ArticleManager.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listeArticles", articlesList );

        // apres soumission du formulaire
        String choix = request.getParameter("choix");
        List <Articles> newList = new ArrayList<>();

        // Si l'user choisie achat
        if (choix!=null && choix.equals("achat")){
            if(request.getParameter("enchereOuverte").equals("on")){
                //List<Articles> articles = new ArrayList<>();
                for (Articles item: articlesList) {
                    LocalDate dateDebut = LocalDate.parse(item.getDateDebutEncheres());
                    LocalDate dateFin =  LocalDate.parse(item.getDateFinEncheres());
                    if(dateDebut.isBefore(today) && dateFin.isAfter(today)){
                        newList.add(item);
                    }
                }
                if(request.getParameter("mesEncheres").equals("on")){
                    Utilisateur user = (Utilisateur) httpSession.getAttribute("pseudo");
                    // TODO créer une requete sql join utilisateur + articles + encheres
                    //newList.add();
                }
            }
            if(request.getParameter("enchereRemportee").equals("on")){
                for (Articles item : articlesList){
                    if(enchereRemportee(item)){
                        newList.add(item);
                    }
                }
            }
        }else if (choix!=null && choix.equals("vente")){
            if (request.getParameter("venteEncours").equals("on")){
                for(Articles item : articlesList){
                    //TODO récupérer les articles associés
                    // récupérer le no utilisateur
                    // filtrer si date du jour entre date de début et fin de vente

                    if(true){
                    }
                }
            }
            if (request.getParameter("nonDebute").equals("on")){
                //TODO récupérer les articles associés
                // récupérer le no utilisateur
                // filtrer si date de début après date du jour
                for(Articles item : articlesList){
                    if(true){
                    }
                }
            }
            if (request.getParameter("terminees").equals("on")){
                //TODO récupérer les articles associés
                // récupérer le no utilisateur
                // filtrer si date de début avant date du jour
                for(Articles item : articlesList){
                    if(true){
                    }
                }
            }
        }

        if(httpSession.getAttribute("isConnected") == null ){
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
            requestDispatcher.forward(request,response);
        }else{
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    // methode pour définir quand enchère est remportée
    private boolean enchereRemportee( Articles item){
        boolean estRemportee = true;
        //TODO récupérer le no utilisateur
        // récupérer les enchères associées
        // filtrer si date de fin avant date du jour
        // vérifier si enchère de l'utilisateur est la dernière sur l'article
        if(true){

        }else{
            estRemportee =false;
        }
        return estRemportee;
    }
}
