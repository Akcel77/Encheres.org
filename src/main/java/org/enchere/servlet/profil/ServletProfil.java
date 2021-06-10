package org.enchere.servlet.profil;

import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletProfil", value = "/Profil")
public class ServletProfil extends HttpServlet {
    private Utilisateur utilisateur = new Utilisateur();
    private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperation de la session
        // Check if isConnected
        //

        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("isConnected") != null){
            //TODO :no_utlisateur null a fixer
            System.out.println(request.getParameter("no_utilisateur"));
            if(!checkIfUserExist(request.getParameter("no_utilisateur"))){
                //TODO : Erreur user inconnu page
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/test/testAkcel.jsp").forward(request, response);
            }else{
                try {
                    System.out.println(utilisateurManager.searchById(Integer.parseInt("no_utilisateur")));
                    request.setAttribute("User", utilisateurManager.searchById(Integer.parseInt(request.getParameter("no_utilisateur"))));
                    if(request.getParameter("no_utilisateur").equals(httpSession.getAttribute("idUser"))){
                        request.setAttribute("canModify", "yes");
                    }
                    this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profil.jsp");
                }catch (NumberFormatException | BusinessException e ){
                    this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/test/testAkcel.jsp").forward(request, response);
                    e.printStackTrace();
                }
            }
        }else{
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/test/testAkcel.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean checkIfUserExist(String id){
        boolean flag = true;
        try {
            utilisateur = utilisateurManager.searchById(Integer.parseInt(id));
            if(utilisateur.getPseudo() != null){
                flag = false;
            }
        }catch (NumberFormatException | BusinessException e ){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
