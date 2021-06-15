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
import java.util.List;

@WebServlet(name = "ServletEnchere", value = "/EncheresEnCours")
public class ServletEnchere extends HttpServlet {
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
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("listeEncheresEnCours", listeEncheresEnCours);

        // Forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
        rd.forward(request, response);
    }
}
