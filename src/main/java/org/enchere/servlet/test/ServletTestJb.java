package org.enchere.servlet.test;

import org.enchere.bll.ArticleManager;
import org.enchere.bo.Articles;

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
        articleManager = new ArticleManager();
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
            // créer un article
            Articles article = new Articles(
                    (String) request.getAttribute("insert_name"),
                    (String) request.getAttribute("insert_description"),
                    (String) request.getAttribute("insert_date_debut"),
                    (String) request.getAttribute("insert_date_fin"),
                    (Integer) request.getAttribute("insert_prix")
            );
            try {
                articleManager.insert(article);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // *********************************************************
            // SELECT by id
            // *********************************************************
        }else if(request.getParameter("select_id_submit") != null){
            System.out.println("on select par id");
            try {
                articleManager.find((int) request.getAttribute("select_id"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // *********************************************************
            // Delete by id
            // *********************************************************
        }else if(request.getParameter("delete_id_submit") != null){
            System.out.println("on delete par id");
            try {
                articleManager.delete((Integer) request.getAttribute("delete_id"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        doGet(request, response);
    }
}
