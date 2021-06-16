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
        //Recuperation session
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("isConnected");

        // recupere le pseudo de l'utilisateur
        request.getSession().setAttribute("pseudo", utilisateur.getPseudo());
        String pseudo = (String) request.getSession().getAttribute("pseudo");

        // on recherche l'user à afficher
        int noProfil = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : utilisateur.getNoUtilisateur();
        Utilisateur utilisateurEnCours = null;
        try {
            utilisateurEnCours = UtilisateurManager.selectUserByID(noProfil);
        }catch (BusinessException e){
            request.getRequestDispatcher("/WEB-INF/jsp/test/testAkcel.jsp").forward(request,response);
            e.printStackTrace();
        }
        if (utilisateurEnCours != null && utilisateur != null){
            request.setAttribute("utilisateurEnCours", utilisateurEnCours);
            if(utilisateurEnCours.getPseudo().equals(utilisateur.getPseudo())){
                request.setAttribute("sameUSer", "sameUser");
            }
            request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
