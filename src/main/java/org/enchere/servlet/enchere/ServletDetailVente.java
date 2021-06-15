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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        // recupere l'id de l'utilisateur
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");
        int idUtilisateur = utilisateur.getNoUtilisateur();

        // test si le detail du produit est notre vente ou celle d'un autre
        boolean isMaVente = idUtilisateur == article.getUtilisateur().getNoUtilisateur();

        //Test si la date est avant ou apres la date du jour
        DateFormat dateFormatDayUS = new SimpleDateFormat("yyyy-MM-dd");
        int compareDate = 0;
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new Date();
            date2 = dateFormatDayUS.parse(article.getDateFinEncheres());
            compareDate = date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean enCours = compareDate == -1; //FIXME ne renvois pas si c'est la date du jour

        //bind les parametre pour la jsp
        request.setAttribute("article", article);
        request.setAttribute("maVente", isMaVente);
        request.setAttribute("enCours", enCours);

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
        } catch (BusinessException | SQLException | ParseException e) {
            e.printStackTrace();
        }

        // Recupere l'id envoyer par le get
        Articles article = null;
        try {
            article = ArticleManager.find(idArticle);
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        //bind les parametre pour la jsp
        request.setAttribute("article", article);

        // forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
        rd.forward(request,response);
    }
}
