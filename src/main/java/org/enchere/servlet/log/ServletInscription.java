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

            List<String> listePseudo = UtilisateurManager.AllPseudoList();
            if (pseudo.length() == 0 || pseudo.isEmpty()){
                request.setAttribute("erreur", "Veuillez renseigner un pseudo");
            }else if (listePseudo.contains(pseudo)){
                request.setAttribute("erreur", "Pseudo deja utilise veuillez renseigner un autre pseudo");
            }else if (nom.length() == 0 || nom.isEmpty()){
                request.setAttribute("erreur", "Veuillez renseigner un nom");
            }else if (prenom.length() == 0 || prenom.isEmpty()){
                request.setAttribute("erreur", "Veuillez renseigner un prenom");
            }else if (email.length() == 0 || email.isEmpty()){
                request.setAttribute("erreur", "Veuillez renseigner un email");
            }else if (password.length() == 0 || password.isEmpty()) {
                request.setAttribute("erreur", "Veuillez renseigner un password");
            }else if (rue.length() == 0 || rue.isEmpty()){
                request.setAttribute("erreur", "Veuillez renseigner une rue");
            }else if (ville.length() == 0 || ville.isEmpty()){
                request.setAttribute("erreur", "Veuillez renseigner une ville");
            }else if (codePostal.length() == 0 || codePostal.isEmpty()) {
                request.setAttribute("erreur", "Veuillez renseigner un codePostal");
            }else if ( passwordConf.equals(password)){
                utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,password);

                System.out.println("test Ajout");

                utilisateur = UtilisateurManager.signInUser(utilisateur);

                //Connection de l'utilisateur apres son enregistrement
                if (utilisateur != null){
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("isConnected", utilisateur);
                    this.getServletContext().getRequestDispatcher("/ServletHome").forward(request, response);
                }else{
                    request.setAttribute("erreur", "Pas d'utilisateur");
                    rd.forward(request, response);
                }
            }
        }catch (BusinessException e){
            e.printStackTrace();
        }
    }
}
