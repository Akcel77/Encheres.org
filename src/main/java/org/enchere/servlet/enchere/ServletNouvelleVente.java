package org.enchere.servlet.enchere;

import org.enchere.bll.*;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Retrait;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ServletNouvelleVente", value = "/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupère la liste des catégories
        List<Categorie> categorieList = null;
        try {
            categorieList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // Récupère l'utilisateur depuis session
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");
        try {
            utilisateur = UtilisateurManager.selectUserByPseudo(utilisateur.getPseudo());
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // Bind les valeurs pour la jsp
        request.setAttribute("categorieList", categorieList);
        request.setAttribute("utilisateur", utilisateur);
        DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
        request.setAttribute("aujourdhui", dateFormatDay.format(new Date()));
        request.setAttribute("maintenant", dateFormatHour.format(new Date()));

        // Forward sur vendre.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vendre.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupère l'utilisateur depuis session
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");
        try {
            utilisateur = UtilisateurManager.selectUserByPseudo(utilisateur.getPseudo());
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // Récupère les champs de formulaire et insert en bdd
        try {
            Articles article = new Articles(
                    request.getParameter("nom"),
                    request.getParameter("description"),
                    request.getParameter("date_debut"),
                    request.getParameter("date_fin"),
                    request.getParameter("heure_debut"),
                    request.getParameter("heure_fin"),
                    Integer.parseInt(request.getParameter("prix")),
                    CategorieManager.selectById(Integer.parseInt(request.getParameter("categorie"))),
                    UtilisateurManager.selectUserByPseudo(utilisateur.getPseudo())
            );
            int noArticle = ArticleManager.insert(article);
            request.setAttribute("successVente", "Votre article a bien été ajouté");
            Retrait retrait = new Retrait(request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"),noArticle);
            RetraitManager.insert(retrait);
        } catch (BusinessException | SQLException e) {
            e.printStackTrace();
        }

        // Renvoie vers DoGet
        doGet(request, response);
    }
}
