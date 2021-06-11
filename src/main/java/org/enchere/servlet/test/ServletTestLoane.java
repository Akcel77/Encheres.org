package org.enchere.servlet.test;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.CategorieManager;
import org.enchere.bll.EnchereManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Enchere;
import org.enchere.outils.BusinessException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="ServletTestLoane", value="/ServletTestLoane")
public class ServletTestLoane extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupere la liste de toutes les categories pour le filtre
        CategorieManager categorieManager = new CategorieManager();
        List<Categorie> categoriesList = null;
        try {
            categoriesList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categoriesList);

        // Recupere la liste de toutes les encheres/ventes en cours
        ArticleManager articleManager = new ArticleManager();
        List<Articles> listeEncheresEnCours = null;
        try {
            listeEncheresEnCours = articleManager.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("listeEncheresEnCours", listeEncheresEnCours);

        // Forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test/testLoane.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}