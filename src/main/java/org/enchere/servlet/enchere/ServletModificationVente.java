package org.enchere.servlet.enchere;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.CategorieManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletModificationVente", value = "/ModificationVente")
public class ServletModificationVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperer l'id envoyé par le get

        Integer id_article = Integer.parseInt(request.getParameter("id"));

        Articles article = null;
        try {
            article = ArticleManager.find(id_article);
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        //Recupere les categorie
        List<Categorie> categorieList = new ArrayList<>();
        try {
            categorieList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        //bind les parametre pour la jsp
        request.setAttribute("article", article);
        request.setAttribute("categorieList", categorieList);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationVente.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // recupere les saisie de l'utilisateur
        Articles article = new Articles();
        article.setId(Integer.parseInt(request.getParameter("id_article")));
        article.setNomArticles(request.getParameter("nom"));
        article.setDescription(request.getParameter("description"));
        article.setDateDebutEncheres(request.getParameter("date_debut"));
        article.setDateFinEncheres(request.getParameter("date_fin"));
        article.setHeureDebut(request.getParameter("heure_debut"));
        article.setHeureFin(request.getParameter("heure_fin"));
        article.setMiseAprix(Integer.parseInt(request.getParameter("prix")));
        try {
            article.setCaterogie(CategorieManager.selectById(Integer.parseInt(request.getParameter("categorie"))));
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // met à jour l'article dans la bdd
        try {
            ArticleManager.update(article);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // redirection sur la servlet detail vente
        response.sendRedirect("DetailVente?id=" + request.getParameter("id_article"));
    }
}