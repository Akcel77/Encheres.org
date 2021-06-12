package org.enchere.servlet.enchere;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.EnchereManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Enchere;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "ServletDetailVente", value = "/DetailVente")
public class ServletDetailVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupere l'id envoyer par le get
        int idArticle = Integer.parseInt(request.getParameter("id"));
        Articles article = null;
        try {
            article = ArticleManager.find(idArticle);
        } catch (SQLException | BusinessException throwables) {
            throwables.printStackTrace();
        }

        //bind les parametre pour la jsp
        request.setAttribute("article", article);

        //forward sur jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // On recupere l'utilisateur depuis session
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");

        // Recupere les data necessaire à la création d'une enchere
        int enchereValue = Integer.parseInt(request.getParameter("nombreEnchere"));
        int idArticle = Integer.parseInt(request.getParameter("id_article"));
        int idUtilisateur = utilisateur.getNoUtilisateur();

        //Créer une enchere
        Enchere enchere = new Enchere(LocalDate.now().toString(), enchereValue, idArticle, idUtilisateur);
        try {
            EnchereManager.createEnchere(enchere);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // forward sur doGet
        doGet(request,response);
    }
}
