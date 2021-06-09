package org.enchere.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * Servlet pour savoir si un utlisateur est connecte ou non sur la page d'acceuil
 * Si il est connecte attribute("isConnected") renvoit sur enchereLog
 * Sinon renvoit sur enchereNoLog
 */
@WebServlet(name = "ServletHome", value = "/ServletHome")
public class ServletHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check si user est connecte dans la session
        //Si il est pas co > enchereNoLog
        //Sinon EnchereLog

        HttpSession httpSession = request.getSession();

        if(httpSession.getAttribute("isConnected") == null ){
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereNoLog.jsp");
            requestDispatcher.forward(request,response);
        }else{
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enchereLog.jsp");
            requestDispatcher.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
