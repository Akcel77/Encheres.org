package org.enchere.servlet.test;

import org.enchere.bll.ArticleManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletTestJb", value = "/TestJb")
public class ServletTestJb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //controleur
        String choice = request.getParameter("choice");

        // recupere tout les article dans la bases de données
        ArticleManager articleManager = new ArticleManager();
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
        String test = request.getParameter("sub_1");
        String test2 = request.getParameter("sub_2");
        System.out.println(test);
        System.out.println(test2);
        doGet(request, response);
    }
}
