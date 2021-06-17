package org.enchere.servlet.log;

import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet de login
 * Si Utilisateur entre pas de pseudo ni de mdp Erreur
 * Si Utilisateur entre un pseudo ou un mot de passe relié à pseudo Erreur
 * Si Utilisateur entre bon pseudo et mdp redirection ServletHome
 */

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
        //Check si l'identifiant n'est pas null (printWritter Test ?redirection )
        //Check si le mdp n'est pas null (printWritter Test ?redirection )
        //Si co ServletHome (ajout session On)

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

//        if (id.isEmpty() || id.length() == 0 ){
//            //erreur
//            request.setAttribute("erreur", "Erreur pseudo, Entrez un champs");
//            //Redirection
//            this.getServletContext().getRequestDispatcher("/Connexion").forward(request,response);
//        }else if (password.isEmpty() || password.length() == 0 ){
//            //erreur
//            request.setAttribute("erreur", "Erreur password, Entrez un champs");
//            //Redirection
//            this.getServletContext().getRequestDispatcher("/Connexion").forward(request,response);
//        }else{
//            try{
//                //Check BDD
//                Utilisateur utilisateur = UtilisateurManager.selectUserByPseudo(id);
//                //IF ok
//                if(utilisateur != null && password.equals(utilisateur.getMotDePasse())){
//                    //Cookies ok
//                    request.getSession().setAttribute("isConnected", utilisateur);
//                    request.getSession().setAttribute("pseudo", utilisateur.getPseudo());
//                    request.getSession().setMaxInactiveInterval(5 * 60);
//
//                    //Renvoie sur la ServletHome avec "isConnected" ok
//
//                    this.getServletContext().getRequestDispatcher("/Encheres").forward(request, response);
//                }else{
//
//
//                    this.getServletContext().getRequestDispatcher("/Connexion").forward(request,response);
//                }
//            }catch (BusinessException e){
//                //Si autre erreur renvoie sur la page d'erreur en fonction
//                request.setAttribute("erreur", e);
//                this.getServletContext().getRequestDispatcher("/ServletErreur");
//            }
//        }


    }
}
