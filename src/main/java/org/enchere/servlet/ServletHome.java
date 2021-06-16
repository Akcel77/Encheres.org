package org.enchere.servlet;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.CategorieManager;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Renvois vers EnchereLog ou enchereNoLOg suivant si l'utilisateur est connecter ou non
 */
@WebServlet(name = "ServletHome", value = "/Encheres")
public class ServletHome extends HttpServlet {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Date ojd = new Date();
    private Date debut = null;
    private Date fin = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Démarre la session
        HttpSession httpSession = request.getSession();

        // Recupere la liste de toutes les categories
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

        // Recupere tout les Parametres du formulaire
        String choix = request.getParameter("choix");
        int numCategorie = Integer.parseInt(request.getParameter("categories"));
        String filter = request.getParameter("recherche").toLowerCase();
        String enchereOuverte = request.getParameter("enchereOuverte");
        String mesEncheres = request.getParameter("mesEncheres");
        String enchereRemportee = request.getParameter("enchereRemportee");
        String venteEncours = request.getParameter("venteEncours");
        String nonDebute = request.getParameter("nonDebute");
        String terminees = request.getParameter("terminees");

        // recuperer la liste de tout les articles
        List<Articles> articles = null;
        try {
            articles = ArticleManager.findAll();
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        //***************
        //   Filtre
        //***************

        //tri mot clé
        if(!filter.equals("")) {
            List<Articles> temporaryList = new ArrayList<>();
            for (Articles article : articles) {
                if (article.getNomArticles().toLowerCase().contains(filter)) {
                    temporaryList.add(article);
                }
                articles = temporaryList;

                /*}else if(!article.getNomArticles().toLowerCase().contains(filter)){
                    try {
                        articles = ArticleManager.findAll();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("articlesNull", "Aucun article ne correspond à votre recherche.");
                    }
                }*/
            }
        }

        //tri par catégories
        if(numCategorie > 0){
            List<Articles> temporaryList = new ArrayList<>();
            for (Articles article : articles) {
                if (article.getCaterogie().getNoCategorie() == numCategorie){
                    temporaryList.add(article);
                }
            }
            articles = temporaryList;
        }

        // tri par mes ventes
        if(choix != null && choix.equals("vente")){
            List<Articles> temporaryList = new ArrayList<>();
            for (Articles article : articles) {
                if (article.getUtilisateur().getNoUtilisateur() == utilisateur.getNoUtilisateur()){
                    temporaryList.add(article);
                }
            }
            articles = temporaryList;

            // Ce sont mes ventes en cours
            if(venteEncours != null && venteEncours.equals("on")){
                temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    try {
                        debut = sdf.parse(article.getDateDebutEncheres() + " " + article.getHeureDebut() + ":00");
                        fin = sdf.parse(article.getDateFinEncheres() + " " + article.getHeureFin() + ":00");
                        if (fin.compareTo(ojd) >= 0 && debut.compareTo(ojd) <= 0 ){
                            temporaryList.add(article);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                articles = temporaryList;
            }

            // Ce sont mes ventes non débuté
            else if(nonDebute != null && nonDebute.equals("on")){
                temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    try {
                        debut = sdf.parse(article.getDateDebutEncheres() + " " + article.getHeureDebut() + ":00");
                        fin = sdf.parse(article.getDateFinEncheres() + " " + article.getHeureFin() + ":00");
                        if (debut.compareTo(ojd) == 1 ){
                            temporaryList.add(article);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                articles = temporaryList;
            }

            // Ce sont mes ventes déja terminé
            else if(terminees != null && terminees.equals("on")){
                temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    try {
                        debut = sdf.parse(article.getDateDebutEncheres() + " " + article.getHeureDebut() + ":00");
                        fin = sdf.parse(article.getDateFinEncheres() + " " + article.getHeureFin() + ":00");
                        if (fin.compareTo(ojd) == -1 ){
                            temporaryList.add(article);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                articles = temporaryList;
            }

        // choix par défaut affiche tout les articles
        }else {

            // tri par articles disponible à l'achat
            if (choix != null && choix.equals("achat")){
                List<Articles> temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    if (article.getUtilisateur().getNoUtilisateur() != utilisateur.getNoUtilisateur()){
                        temporaryList.add(article);
                    }
                }
                articles = temporaryList;
            }

            // Aucune enchere en cours
            if(enchereOuverte != null && enchereOuverte.equals("on")){
                List<Articles> temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    if(article.getEncheres().size() == 0){
                        temporaryList.add(article);
                    }
                }
                articles = temporaryList;
            }

            // j'ai placer une enchere
            else if(mesEncheres != null && mesEncheres.equals("on")){
                List<Articles> temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    boolean placeEnchere = false;
                    for (Enchere enchere: article.getEncheres()) {
                        if(enchere.getNo_utilisateur() == utilisateur.getNoUtilisateur()){
                            placeEnchere = true;
                        }
                    }
                    if (placeEnchere){
                        temporaryList.add(article);
                    }
                }
                articles = temporaryList;
            }

            // Je suis le meilleurs encherisseur
            else if(enchereRemportee != null && enchereRemportee.equals("on")){
                List<Articles> temporaryList = new ArrayList<>();
                for (Articles article : articles) {
                    if(article.getLastEncheres() != null && article.getLastEncheres().getNo_utilisateur() == utilisateur.getNoUtilisateur()){
                        temporaryList.add(article);
                    }
                }
                articles = temporaryList;
            }
        }

        // Teste si la recherche n'a aucun résultat
        if(articles.isEmpty()){
            request.setAttribute("articlesNull", articles);
            try {
                articles = ArticleManager.findAll();
            } catch (SQLException | BusinessException | ParseException throwables) {
                throwables.printStackTrace();
            }
        }

        //bind les values
        request.setAttribute("articles", articles);

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
