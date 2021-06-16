package org.enchere.servlet.log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeconnexion", value = "/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Recupere la session puis efface la mémorisation de la connection
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("isConnected");
        httpSession.invalidate();

        //redirection
        response.sendRedirect("Encheres");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
