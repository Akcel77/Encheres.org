package org.enchere.servlet.log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletConnexion", value = "/Connexion")
public class ServletConnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // forward sur la page de connexion
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO gestion de la connexion
        doGet(request,response);
    }
}
