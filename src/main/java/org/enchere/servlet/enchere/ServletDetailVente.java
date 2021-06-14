package org.enchere.servlet.enchere;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.EnchereManager;
import org.enchere.bll.UtilisateurManager;
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
import java.util.List;

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

        //Vérifie si l'utilisateur as assez de crédit
        try {
            int creditAcheteur = UtilisateurManager.selectUserByID(idUtilisateur).getCredit();
            int lastEncherePrice = 0;
            List<Enchere> enchereList = ArticleManager.find(idArticle).getEncheres();
            for (Enchere encheres: enchereList) {
                if (encheres.getNo_utilisateur() == idUtilisateur){
                    lastEncherePrice = encheres.getMontant_enchere();
                }
            }
            int sommeADepenser = enchereValue - lastEncherePrice;
            System.out.println("************************************");
            System.out.println("DEBUG Gestion crédit");
            System.out.println("************************************");
            System.out.println("credit acheteur : " + creditAcheteur);
            System.out.println("enchere placer : " + enchereValue);
            System.out.println("valeur de sa dernieres enchere sur cette article " + lastEncherePrice);
            System.out.println("Valeur du portefeuille apres enchere : " + (creditAcheteur - sommeADepenser));
            System.out.println("************************************");
            if (creditAcheteur >= sommeADepenser){
                //Créer une enchere si il as suffisament de crédit et le soustrait a son compte
                Enchere enchere = new Enchere(LocalDate.now().toString(), enchereValue, idArticle, idUtilisateur);
                try {
                    EnchereManager.createEnchere(enchere);
                    utilisateur.setCredit(UtilisateurManager.selectUserByID(idUtilisateur).getCredit() - sommeADepenser );
                    UtilisateurManager.updateUser(utilisateur);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        } catch (BusinessException | SQLException e) {
            e.printStackTrace();
        }

        // Recupere l'id envoyer par le get
        Articles article = null;
        try {
            article = ArticleManager.find(idArticle);
        } catch (SQLException | BusinessException throwables) {
            throwables.printStackTrace();
        }

        //bind les parametre pour la jsp
        request.setAttribute("article", article);

        // forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
        rd.forward(request,response);
    }
}
