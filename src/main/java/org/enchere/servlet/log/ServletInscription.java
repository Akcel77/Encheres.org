package org.enchere.servlet.log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletInscription", value = "/Inscription")
public class ServletInscription extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // forward sur la page d'inscription
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Todo gestion de l'inscription
        doGet(request,response);
    }
}
