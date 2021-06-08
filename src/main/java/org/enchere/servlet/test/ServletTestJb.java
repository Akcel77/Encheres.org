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
        // simule une bdd
//        Articles a1 = new Articles("machin","un super truc !","08/06/2021","18/06/2021",100,"nonvendu",new Categorie());
//        Articles a2 = new Articles("clavier","azerty et propres","18/06/2021","28/06/2021",75,"nonvendu",new Categorie());
//        List<Articles> articlesList = new ArrayList<>();
//        articlesList.add(a1);
//        articlesList.add(a2);

        //true bdd
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
        doGet(request, response);
    }
}
