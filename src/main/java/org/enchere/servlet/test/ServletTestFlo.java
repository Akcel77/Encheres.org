package org.enchere.servlet.test;

import org.enchere.bll.EnchereManager;
import org.enchere.bo.Enchere;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@WebServlet("/ServletTestFlo")
public class ServletTestFlo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test/jspTestFlo.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 - Récupérer les données qui sont envoyées dans la requête

        if (request.getParameter("valider")!= null){
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
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test/jspTestFlo.jsp");
            rd.forward(request, response);
        }
    }
}
