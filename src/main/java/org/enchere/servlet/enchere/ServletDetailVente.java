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

        // Récupère l'id envoyé par le get
        int idArticle = Integer.parseInt(request.getParameter("id"));
        Articles article = null;
        try {
            article = ArticleManager.find(idArticle);
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        // Récupère l'id de l'utilisateur
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");
        int idUtilisateur = utilisateur.getNoUtilisateur();

        // Teste si le détail du produit est notre vente ou celle d'un autre
        boolean isMaVente = idUtilisateur == article.getUtilisateur().getNoUtilisateur();

        // Teste si l'utilisateur en cours est l'acquéreur de la vente
        boolean venteRemportee = false;
        if(article.getLastEncheres() != null){
            venteRemportee = idUtilisateur == article.getLastEncheres().getNo_utilisateur();
        }

        // Teste si la date est avant ou après la date du jour
        DateFormat dateFormatDayUS = new SimpleDateFormat("yyyy-MM-dd");
        int compareDate = 0;
        Date date1 = null;
        Date date2 = null;
        try {
            String d1 = dateFormatDayUS.format(new Date());
            date1 = dateFormatDayUS.parse(d1);
            date2 = dateFormatDayUS.parse(article.getDateFinEncheres());
            compareDate = date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean enCours = (compareDate == -1 || compareDate == 0);

        // Bind les paramètres pour la jsp
        request.setAttribute("article", article);
        request.setAttribute("maVente", isMaVente);
        request.setAttribute("enCours", enCours);
        request.setAttribute("venteRemportee", venteRemportee);

        // Forward sur jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère l'utilisateur depuis session
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");

        // Récupère les datas nécessaires à la création d'une enchère
        int enchereValue = Integer.parseInt(request.getParameter("nombreEnchere"));
        int idArticle = Integer.parseInt(request.getParameter("id_article"));
        int idUtilisateur = utilisateur.getNoUtilisateur();

        // Vérifie si l'utilisateur a assez de crédit
        try {
            int creditAcheteur = UtilisateurManager.selectUserByID(idUtilisateur).getCredit();

            // Débite l'utilisateur si assez de crédit et débite l'ancien enchérisseur
            if (creditAcheteur >= enchereValue){
                Enchere enchere = new Enchere(LocalDate.now().toString(), enchereValue, idArticle, idUtilisateur);
                try {
                    // on crédite l'ancien meilleur enchérisseur du montant de son enchère
                    Articles article = ArticleManager.find(idArticle);
                    Utilisateur compteACrediter = null;
                    Enchere lastEnchere = article.getLastEncheres();
                    if (lastEnchere != null){
                        compteACrediter = UtilisateurManager.selectUserByID(article.getLastEncheres().getNo_utilisateur());
                        compteACrediter.setCredit(compteACrediter.getCredit() + lastEnchere.getMontant_enchere());
                        UtilisateurManager.updateUser(compteACrediter);
                    }

                    // on débite l'utilisateur connecté de son enchère
                    EnchereManager.createEnchere(enchere);
                    request.setAttribute("successEnchere", "Votre enchère a bien été ajoutée.");
                    utilisateur.setCredit(UtilisateurManager.selectUserByID(idUtilisateur).getCredit() - enchereValue);
                    UtilisateurManager.updateUser(utilisateur);

                } catch (BusinessException | SQLException | ParseException e) {
                    e.printStackTrace();
                }
            }else{
                request.setAttribute("erreurEncheres", "Veuillez recharger votre crédit pour effectuer cette mise.");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        // Récupère l'id envoyé par le get
        Articles article = null;
        try {
            article = ArticleManager.find(idArticle);
        } catch (SQLException | BusinessException | ParseException throwables) {
            throwables.printStackTrace();
        }

        // Teste si le détail du produit est notre vente ou celle d'un autre
        boolean isMaVente = idUtilisateur == article.getUtilisateur().getNoUtilisateur();

        // Teste si l'utilisateur en cours est l'acquéreur de la vente
        boolean venteRemportee = false;
        if(article.getLastEncheres() != null){
            venteRemportee = idUtilisateur == article.getLastEncheres().getNo_utilisateur();
        }

        // Teste si la date est avant ou après la date du jour
        DateFormat dateFormatDayUS = new SimpleDateFormat("yyyy-MM-dd");
        int compareDate = 0;
        Date date1 = null;
        Date date2 = null;
        try {
            String d1 = dateFormatDayUS.format(new Date());
            date1 = dateFormatDayUS.parse(d1);
            date2 = dateFormatDayUS.parse(article.getDateFinEncheres());
            compareDate = date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean enCours = (compareDate == -1 || compareDate == 0);

        //bind les parametre pour la jsp
        request.setAttribute("article", article);
        request.setAttribute("maVente", isMaVente);
        request.setAttribute("enCours", enCours);
        request.setAttribute("venteRemportee", venteRemportee);

        // forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
        rd.forward(request,response);
    }
}
