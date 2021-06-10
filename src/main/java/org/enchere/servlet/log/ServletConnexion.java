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
 * Si Utilisateur entre un pseudo ou un mot de passe relie a pseudo Erreur
 * Si Utilisateur entre bon pseudo et mdp redirection ServletHome
 */
@WebServlet(name = "ServletConnexion", value = "/Connexion")
public class ServletConnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // forward sur la page de connexion
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check si l'identifiant n'est pas null (printWritter Test ?redirection )
        //Check si le mdp n'est pas null (printWritter Test ?redirection )
        //Si co ServletHome  (ajout session On )

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
        PrintWriter out = response.getWriter();

        HttpSession httpSession = request.getSession();

        String erreur = null;
        String id = request.getParameter("id");
        String password = request.getParameter("psw");

        if (id.isEmpty() || id.length() == 0 ){
            //erreur
            request.setAttribute("erreur", "Erreur pseudo, Entrez un champs");
            erreur = (String) httpSession.getAttribute("erreur");
            out.println(erreur);
            System.out.println("erreur id");
            //Redirection
            this.getServletContext().getRequestDispatcher("/Connexion").forward(request,response);
        }else if (password.isEmpty() || password.length() == 0 ){
            //erreur
            request.setAttribute("erreur", "Erreur password, Entrez un champs");
            erreur = (String) httpSession.getAttribute("erreur");
            out.println(erreur);
            System.out.println("erreur mdp");
            //Redirection
            this.getServletContext().getRequestDispatcher("/Connexion").forward(request,response);
        }else{
            try{
                //Check BDD
                Utilisateur utilisateur = UtilisateurManager.selectUserByPseudo(id);
                //IF ok
                if(utilisateur != null && password.equals(utilisateur.getMotDePasse())){
                    //Cookies ok
                    request.getSession().setAttribute("isConnected", utilisateur);
                    request.getSession().setAttribute("pseudo", utilisateur.getPseudo());

                    //Renvoie sur la ServletHome avec "isConnected" ok

                    this.getServletContext().getRequestDispatcher("/ServletHome").forward(request, response);
                }else{
                    //Si l'utilisateur n'est pas dans la BDD ou ni le mdp
                    request.setAttribute("erreur", "Erreur dans le pseudo ou dans le mdp");
                    System.out.println("erreur BDD");
                    erreur = (String) httpSession.getAttribute("erreur");
                    out.println(erreur);
                    //Redirection
                    this.getServletContext().getRequestDispatcher("/Connexion").forward(request,response);
                }
            }catch (BusinessException e){
                //Si autre erreur renvoie sur la page d'erreur en fonction
                request.setAttribute("erreur", e);
                this.getServletContext().getRequestDispatcher("/ServletErreur");
            }
        }


    }
}
