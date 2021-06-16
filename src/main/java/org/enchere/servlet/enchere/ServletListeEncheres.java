//package org.enchere.servlet;
//
//import org.enchere.bll.ArticleManager;
//import org.enchere.bll.CategorieManager;
//import org.enchere.bll.EnchereManager;
//import org.enchere.bll.UtilisateurManager;
//import org.enchere.bo.Articles;
//import org.enchere.bo.Categorie;
//import org.enchere.bo.Enchere;
//import org.enchere.bo.Utilisateur;
//import org.enchere.outils.BusinessException;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.SQLException;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
///**
// * Renvois vers EnchereLog ou enchereNoLOg suivant si l'utilisateur est connecter ou non
// */
//@WebServlet(name = "ServletHome", value = "/Encheres")
//public class ServletHome extends HttpServlet {
//    private static LocalDate today = LocalDate.now();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        // Démarre la session
//        HttpSession httpSession = request.getSession();
//
//        // Recupere la liste de toutes les categories
//        CategorieManager categorieManager = new CategorieManager();
//        List<Categorie> categoriesList = null;
//        try {
//            categoriesList = CategorieManager.selectAll();
//        } catch (BusinessException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("categories", categoriesList);
//
//        // recuperer la liste de tout les articles
//        try {
//            request.setAttribute("articles", ArticleManager.findAll());
//        } catch (SQLException | BusinessException | ParseException throwables) {
//            throwables.printStackTrace();
//        }
//
//        // Si l'utilisateur est connecter redirige vers EnchereLog sinon EnchereNoLog
//        if(httpSession.getAttribute("isConnected") == null ){
//            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
//            requestDispatcher.forward(request,response);
//        }else{
//            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
//            requestDispatcher.forward(request,response);
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        // Recupere la liste de toutes les catégories
//        Categorie categorie = new Categorie();
//        List<Categorie> categoriesList = null;
//        try {
//            categoriesList = CategorieManager.selectAll();
//        } catch (BusinessException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("categories", categoriesList);
//
//        // Recupere l'utilisateur connecté
//        HttpSession httpSession = request.getSession();
//        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");
//        String nomArticle;
//
//        // Si l'utilisateur est connecter
//        System.out.println("test" + request.getParameter("recherche").equals(""));
//        if(httpSession.getAttribute("isConnected") != null ){
//            try {
//                if (request.getParameter("recherche") == null || request.getParameter("recherche").equals("null") ||  request.getParameter("recherche").isEmpty() ){
//
//                    if(request.getParameter("categories") == null ){
//
//                        request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
//                        request.setAttribute("articles", ArticleManager.findAll());
//                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
//                        rd.forward(request, response);
//                    }else{
//
//                        request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
//                        request.setAttribute("articles", ArticleManager.findByCategorie(Integer.parseInt(request.getParameter("categories"))));
//                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
//                        rd.forward(request, response);
//
//                    }
//                }else{
//                    System.out.println("test 2");
//                    nomArticle = request.getParameter("recherche");
//                    request.setAttribute("articles", ArticleManager.findByNomArticle(nomArticle));
//                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
//                    rd.forward(request, response);
//                }
//
////                if (request.getParameter("recherche") != null ||!request.getParameter("recherche").equals("null") || !request.getParameter("recherche").isEmpty() ){
////                    System.out.println("test 2");
////                    nomArticle = request.getParameter("recherche");
////                    request.setAttribute("articles", ArticleManager.findByNomArticle(nomArticle));
////                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
////                    rd.forward(request, response);
////                }
////                else {
////                    if(request.getAttribute("categories").equals("-1")){
////                        System.out.println("test 3");
////                        request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
////                        request.setAttribute("articles", ArticleManager.findAll());
////                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
////                        rd.forward(request, response);
////                    }else{
////                        System.out.println("test 4");
////                        request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
////                        request.setAttribute("articles", ArticleManager.findByCategorie(Integer.parseInt(request.getParameter("categories"))));
////                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
////                        rd.forward(request, response);
////                    }
////                }
//
//
//
//
//
//            } catch (SQLException | BusinessException | ParseException sqlException) {
//                sqlException.printStackTrace();
//            }
//        }else{
//            try {
//                if (!request.getParameter("recherche").isEmpty()){
//                    nomArticle = request.getParameter("recherche");
//                    request.setAttribute("articles", ArticleManager.findByNomArticle(nomArticle));
//                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
//                    rd.forward(request, response);
//                }else{
//                    if (request.getParameter("categories").equals("-1")){
//                        request.setAttribute("articles", ArticleManager.findAll());
//                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
//                        rd.forward(request, response);
//                    }else{
//                        request.setAttribute("articles", ArticleManager.findByCategorie(Integer.parseInt(request.getParameter("categories"))));
//                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
//                        rd.forward(request, response);
//                    }
//                }
//
//            } catch (SQLException | BusinessException | ParseException sqlException) {
//                sqlException.printStackTrace();
//            }
//
//        }
//    }
//
//
//
////        }else{
////            try {
////                System.out.println("test1");
////                if(request.getParameter("choix").equals("achat")|| request.getParameter("choix").equals("vente")){
////                    String checkbox;
////                    if (request.getParameter("choix").equals("achat")){
////                        checkbox="achats";
////                    }else {
////                        checkbox="ventes";
////                    }
////
////                    request.setAttribute("choix", selectCond(request.getParameter("nom"), Integer.parseInt(request.getParameter("categories")),request.getParameter(checkbox), 2));
////                }
////                request.setAttribute("utilisateur", httpSession.getAttribute("isConnected"));
////                request.setAttribute("articles", ArticleManager.findAll());
////                this.getServletContext().getRequestDispatcher("/WEB_INF/jsp/enchereLog.jsp").forward(request,response);
////            }catch (SQLException | BusinessException b){
////                b.printStackTrace();
////            }
//
//    private ArrayList<Articles> selectCond (String nom , int noCategorie, String checkbox, int noUtilisateur) throws BusinessException{
//        ArrayList<Articles> articles  = null;
//        String cond;
//        Date date = new Date();
//
//        String dateFormat = "yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
//        String dateformatee = "" + simpleDateFormat.format(date);
//
//        switch (checkbox) {
//            case "enchereOuverte":
//                cond = "AND a.date_debut_encheres<=" + dateformatee + " AND a.date_fin_encheres>" + dateformatee + " AND a.no_utilisateur<>" + noUtilisateur;
//                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
//                break;
//            case "mesEncheres":
//                cond = "AND a.date_debut_encheres<" + dateformatee + " AND a.date_fin_encheres>" + dateformatee + " AND a.no_utilisateur=" + noUtilisateur;
//                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
//                break;
//            case "enchereRemportee":
//                cond = "AND a.date_debut_encheres<" + dateformatee +" AND a.no_utilisateur=" + noUtilisateur;
//                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
//                break;
//            case "vente":
//                cond = "AND a.date_debut_encheres<=" + dateformatee + " AND a.date_fin_encheres>" + dateformatee + " AND a.no_utilisateur=" + noUtilisateur;
//                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
//                break;
//            case "nonDebute":
//                cond = "AND a.date_debut_encheres>" + dateformatee +" AND a.no_utilisateur<>" + noUtilisateur;
//                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
//                break;
//            case "terminees":
//                cond = "AND a.date_debut_encheres<" + dateformatee +" AND a.no_utilisateur<>" + noUtilisateur;
//                articles = ArticleManager.findWithCond(nom, noCategorie, cond);
//                break;
//
//        }return articles;
//    }
//}
