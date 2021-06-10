package org.enchere.servlet.test;

import org.enchere.bll.EnchereManager;
import org.enchere.bo.Enchere;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/ServletTestFlo")
public class ServletTestFlo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Afficher la liste de toutes les enchères
         */
        // 1 - Récupérer la liste de tous les articles
        EnchereManager enchereManager = new EnchereManager();
        List<Enchere> listeEnchere = null;
        try {
            listeEnchere = enchereManager.findAll();
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 2 Afficher la liste
        request.setAttribute("listeEnchere", listeEnchere);

        // 3 - renvoyer sur la page jsp pour l'affichage
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test/jspTestFlo.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Insertion d'une nouvelle enchère
         */

        // 1 - Récupérer les données qui sont envoyées dans la requête
        if (request.getParameter("validerAjout")!= null){
            String dateEnchere = request.getParameter("date");
            String montant = request.getParameter("montant");
            String no_article = request.getParameter("no_article");
            String no_utilisateur = request.getParameter("no_utilisateur");

            // 2- Convertir les données dans leur bon type

            Integer montantEnchere = Integer.parseInt(montant);
            Integer noArticle = Integer.parseInt(no_article);
            Integer noUtilisateur = Integer.parseInt(no_utilisateur);

            //3 - Appeler le service métier pour déclencher la sauvegarde d'une nouvelle enchère
            EnchereManager enchere=new EnchereManager();
            Enchere newEnchere = new Enchere(dateEnchere, montantEnchere, noArticle, noUtilisateur);
            try {
                enchere.createEnchere(newEnchere);
                System.out.println(enchere);

            } catch (BusinessException e) {
                e.printStackTrace();
            }

            //4 - Redirection vers la JSP qui affiche la liste des enchères
            request.setAttribute("nouvelleEnchere", newEnchere );

            request.setAttribute("messageAjout","L'enchère a été ajoutée");
            //TODO mettre un message "enchère ajoutée" et l'ajouter à la liste affichée?

        }
        /**
         * Affichage d'une enchère par son numéro
         */

        // 1 - Récupérer l'identifiant envoyé dans la requête
        else if(request.getParameter("ValiderSelection")!= null){
            System.out.println("Numéro enchère selectionnée "+request.getParameter("selectionParNo"));
            String noEnchere = request.getParameter("selectionParNo");
            // 2- Convertir dans le bon type
            Integer no_enchere = Integer.parseInt(noEnchere);
            System.out.println("Numéro enchère :"+ noEnchere);
            //3 - Appeler le service métier pour déclencher l'affichage de la selection
            EnchereManager enchereManager = new EnchereManager();
            try {
                Enchere enchere = enchereManager.findEnchere(no_enchere);
                request.setAttribute("enchereParNo",enchere);
            } catch (BusinessException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(enchereManager);
            //4 - Redirection vers la JSP qui affiche la selection

        }
        /**
         * Suppression d'une enchère par son numéro
         */

        // 1 - Récupérer l'identifiant envoyé dans la requête
        else if(request.getParameter("validerSuppression")!= null){
            System.out.println("Numéro enchère selectionnée "+request.getParameter("suppressionParNo"));
            String noEnchere = request.getParameter("suppressionParNo");
            // 2- Convertir dans le bon type
            Integer no_enchere = Integer.parseInt(noEnchere);
            System.out.println("Numéro enchère :"+ no_enchere);
            //3 - Appeler le service métier pour déclencher l'affichage de la selection
            EnchereManager enchereManager = new EnchereManager();
            try {
                enchereManager.deleteEnchere(no_enchere);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            System.out.println(enchereManager);

        }
        doGet(request, response);
    }
}
