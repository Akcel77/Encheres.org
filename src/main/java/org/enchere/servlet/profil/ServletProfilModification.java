package org.enchere.servlet.profil;

import org.enchere.bll.ArticleManager;
import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletProfilModification", value = "/ProfilModification")
public class ServletProfilModification extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilModification.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur isConnected = (Utilisateur) httpSession.getAttribute("isConnected");

        try{
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String rue = request.getParameter("rue");
            String codePostal = request.getParameter("codePostal");
            String ville = request.getParameter("ville");
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String newPassConf = request.getParameter("newPassConf");

            isConnected.setNom(nom);
            isConnected.setPrenom(prenom);
            isConnected.setEmail(email);
            isConnected.setTelephone(telephone);
            isConnected.setRue(rue);
            isConnected.setCodePostal(codePostal);
            isConnected.setVille(ville);

            if (password.equals(isConnected.getMotDePasse()) && newPassword.equals(newPassConf)){
                isConnected.setMotDePasse(newPassword);
            }
            UtilisateurManager.updateUser(isConnected);
            httpSession.setAttribute("isConnected", isConnected);

            this.getServletContext().getRequestDispatcher("/Profil").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
