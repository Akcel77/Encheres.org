package org.enchere.servlet.log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeconnexion", value = "/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("isConnected");
        httpSession.invalidate();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
