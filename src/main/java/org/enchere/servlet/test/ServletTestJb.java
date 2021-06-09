package org.enchere.servlet.test;

import org.enchere.bll.ArticleManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletTestJb", value = "/TestJb")
public class ServletTestJb extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArticleManager articleManager = new ArticleManager();

        // Recupere la liste de tout les articles
        List<Articles> articlesList = null;
        try {
            articlesList = articleManager.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Envois les parametre
        request.setAttribute("articles",articlesList);

        // forward
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test/testjb.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArticleManager articleManager = new ArticleManager();
        // *********************************************************
        // INSERT
        // *********************************************************
        if (request.getParameter("insert_submit") != null){
            System.out.println("On insere dans la bdd");

            try {
                // créer un article
                Articles article = new Articles(
                        request.getParameter("insert_name"),
                        request.getParameter("insert_description"),
                        request.getParameter("insert_date_debut"),
                        request.getParameter("insert_date_fin"),
                        Integer.parseInt(request.getParameter("insert_prix")),
                        new Categorie(),
                        new Utilisateur()
                        );

                articleManager.insert(article);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // *********************************************************
            // SELECT by id
            // *********************************************************
        }else if(request.getParameter("select_id_submit") != null){
            System.out.println("on select par id : " + request.getParameter("select_id"));
            try {
                Articles article = articleManager.find(Integer.parseInt(request.getParameter("select_id")));
                System.out.println(article.toString());
                request.setAttribute("select_article", article);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // *********************************************************
            // Delete by id
            // *********************************************************
        }else if(request.getParameter("delete_id_submit") != null){
            System.out.println("on delete par id : " + request.getParameter("delete_id") );
            try {
                articleManager.delete(Integer.parseInt(request.getParameter("delete_id")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        doGet(request, response);
    }
}
