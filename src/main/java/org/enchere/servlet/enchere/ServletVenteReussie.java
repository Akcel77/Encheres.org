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

@WebServlet(name = "ServletVenteReussie", value = "/VenteReussie")
public class ServletVenteReussie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère l'ID de l'article
        int idArticle = Integer.parseInt(request.getParameter("id"));
        Articles article = null;
        try {
            article = ArticleManager.find(idArticle);
        } catch (SQLException | BusinessException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("article", article);

        // Forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/venteReussie.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //En tant qu’enchérisseur, je deviens acquéreur si au terme de l’enchère j’ai proposé l’enchère la plus haute

        // Récupère l'utilisateur connecté
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");

        // Seul l'utilisateur connecté acquéreur peut voir le détail de la vente
        if (utilisateur != null) {
            // Forward
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/venteReussie.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
            rd.forward(request, response);
        }

    }
}