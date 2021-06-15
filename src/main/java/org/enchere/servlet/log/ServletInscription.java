package org.enchere.servlet.log;

import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
        //Cree un Utilisateur
        //Check si tous les champs sont remplis
        //Si c'est ok cree un Utilisateur
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");
        Utilisateur utilisateur = null;
        List<String> listEmail = null;
        List<String> listePseudo = null;
        try {
            listEmail = UtilisateurManager.getAllMail();
            listePseudo  = UtilisateurManager.AllPseudoList();
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }







        try {
            String pseudo = request.getParameter("pseudo");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String password = request.getParameter("psw");
            String passwordConf = request.getParameter("pswConf");
            String telephone = request.getParameter("telephone");
            String rue = request.getParameter("rue");
            String ville = request.getParameter("ville");
            String codePostal = request.getParameter("codePostal");

            if (listEmail.contains(email)){
                request.setAttribute("erreurMail", "Email déjà utilisé veuillez renseigner un autre email");
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
            }else if(listePseudo.contains(pseudo)){
                request.setAttribute("erreurPseudo", "Pseudo déjà utilisé veuillez renseigner un autre pseudo");
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
            }else if (!pseudo.isEmpty() && ! nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rue.isEmpty() && !ville.isEmpty() && !codePostal.isEmpty() ) {
                utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password);
                if (passwordConf.equals(password) && !listEmail.contains(email) && !listePseudo.contains(pseudo) ) {
                    utilisateur = UtilisateurManager.signInUser(utilisateur);
                }
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("isConnected", utilisateur);
                this.getServletContext().getRequestDispatcher("/Encheres").forward(request, response);
            }


    } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }
}
