package org.enchere.servlet.profil;

import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import javax.rmi.CORBA.Util;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "ServletSupprimerCompte", value = "/SupprimerCompte")
public class ServletSupprimerCompte extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur isConnected = (Utilisateur) httpSession.getAttribute("isConnected");

        try {
            UtilisateurManager.deleteUser(isConnected.getNoUtilisateur());
            httpSession.removeAttribute("isConnected");
            httpSession.invalidate();

            RequestDispatcher rd = request.getRequestDispatcher("/ServletHome");
            rd.forward(request,response);
        }catch (BusinessException e){
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/Profil");
            rd.forward(request,response);
        }
    }
}
