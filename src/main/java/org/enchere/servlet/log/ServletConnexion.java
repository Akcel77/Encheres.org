package org.enchere.servlet.log;

import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletConnexion", value = "/Connexion")
public class ServletConnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward sur la page de connexion
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utilisateur utilisateur = new Utilisateur();
        String erreur = null;
        String id = request.getParameter("id");
        String password = request.getParameter("psw");
        String recherche = request.getParameter("recherche");
        Integer cate = (Integer) request.getAttribute("categorie");
        boolean checkInDataBase = true;
        String rememberMe = request.getParameter("rememberMe");

        request.setAttribute("id", id);
        request.setAttribute("psw", password);

        if(rememberMe != null){
            Cookie[] cookies = request.getCookies();
            if (cookies == null || cookies.length > 0){
                Cookie cookie = new Cookie("loginName", id);
                cookie.setMaxAge(10*60*60*24*365);
                response.addCookie(cookie);
            }
        }
        if (id.contains("@")){
            utilisateur = new Utilisateur(id, password, true);
            try {
                checkInDataBase = UtilisateurManager.checkIfEmailOrPseudo(utilisateur);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
        }else {
            utilisateur = new Utilisateur(id, password, false);
            try {
                checkInDataBase = UtilisateurManager.checkIfEmailOrPseudo(utilisateur);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
        }

        if (checkInDataBase == true){
            try {
                HttpSession httpSession = request.getSession();
                utilisateur = UtilisateurManager.selectUserByPseudo(id);

                request.getParameter("recherche");

                httpSession.setAttribute("isConnected", utilisateur);
                httpSession.setAttribute("noUtilisateur", utilisateur.getNoUtilisateur());
                request.getSession().setMaxInactiveInterval(5 * 60);

                //redirection
                response.sendRedirect("Encheres");

            } catch (BusinessException businessException) {
                businessException.printStackTrace();
            }
        }else{
            request.setAttribute("erreurE", "Identifiant ou mot de passe incorrect");
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
        }
    }
}
