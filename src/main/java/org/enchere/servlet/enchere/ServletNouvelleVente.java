package org.enchere.servlet.enchere;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.CategorieManager;
import org.enchere.bll.GlobalManager;
import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletNouvelleVente", value = "/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On recupere la liste des catégories
        List<Categorie> categorieList = null;
        try {
            categorieList = CategorieManager.selectAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // On recupere l'utilisateur
        Utilisateur utilisateur = null;
        try {
            utilisateur = UtilisateurManager.selectUserByPseudo("Akcel");
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // on bind les valeurs pour la jsp
        request.setAttribute("categorieList", categorieList);
        request.setAttribute("utilisateur", utilisateur);

        // Forward sur vendre.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vendre.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On recupere l'utilisateur
        Utilisateur utilisateur = null;
        try {
            utilisateur = UtilisateurManager.selectUserByPseudo("Akcel");
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        //Recupere les champs de formulaire et insert
        try {
            Articles article = new Articles(
                    request.getParameter("nom"),
                    request.getParameter("description"),
                    request.getParameter("date_debut"),
                    request.getParameter("date_fin"),
                    Integer.parseInt(request.getParameter("prix")),
                    CategorieManager.selectById(Integer.parseInt(request.getParameter("categorie"))),
                    UtilisateurManager.selectUserByPseudo(utilisateur.getPseudo())
            );
            if (article == null){
                System.out.println("null");
            }else{
                System.out.println(article);
            }
            ArticleManager.insert(article);

        } catch (BusinessException | SQLException e) {
            e.printStackTrace();
        }

        // renvois vers DoGet
        doGet(request, response);
    }
}
