package org.enchere.bll;

import org.enchere.bo.Utilisateur;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.UtilisateurDAO;
import org.enchere.dal.jdbc.UtilisateurImpl;
import org.enchere.outils.BusinessException;

import java.util.List;


public class UtilisateurManager {

    private static UtilisateurDAO utilisateurDAO ;
    private static Utilisateur utilisateur = new Utilisateur();
    private static BusinessException businessException = new BusinessException();

    static {
        UtilisateurManager.utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    /**
     * Permet à un utilisateur de s'enregistrer si les conditions de validInscription sont bonnes
     * @param utilisateur
     * @return utilisateur
     * @throws BusinessException
     */
    public static Utilisateur signInUser(Utilisateur utilisateur)throws BusinessException{
        validInscription(utilisateur);
        if(!businessException.hasErreurs()){
            utilisateurDAO.insert(utilisateur);
        }else{
            System.out.println("Erreur update User");
        }
        return utilisateur;
    }

    /**
     * Vérifie si l'email ou le pseudo appartient bien à un utilisateur
     * @param utilisateur
     * @return
     * @throws BusinessException
     */
    public static boolean checkIfEmailOrPseudo(Utilisateur utilisateur)throws BusinessException{
        return utilisateurDAO.checkIfEmailOrPseudo(utilisateur);
    }



    /**
     * Vérifie les Conditions préalables à l'inscription
     * @param utilisateur
     */
    private static void validInscription(Utilisateur utilisateur){
        if (utilisateur.getPseudo().trim().equals("") ||
                utilisateur.getNom().trim().equals("") ||
                utilisateur.getPrenom().trim().equals("") ||
                utilisateur.getEmail().trim().equals("") ||
                utilisateur.getRue().trim().equals("") ||
                utilisateur.getCodePostal().trim().equals("") ||
                utilisateur.getVille().trim().equals("") ||
                utilisateur.getMotDePasse().trim().equals("")
        ) {
            businessException.ajouterErreur(CodeErreurBLL.ERREUR_COORDONNEES);
        }
    }

    /**
     * Met à jour les informations d'un utilisateur si les conditions sont acceptées (cf updateInfo())
     * @param utilisateur
     * @throws BusinessException
     */
    public static void updateUser(Utilisateur utilisateur) throws BusinessException{
        updateInfo(utilisateur);
        if(!businessException.hasErreurs()){
            utilisateurDAO.update(utilisateur);
        }
    }

    /**
     * Vérifie les Conditions préalables à la mise à jour des infos (cf updateUser())
     * @param utilisateur
     */
    private static void updateInfo(Utilisateur utilisateur){
        if(
                utilisateur.getNom().trim().equals("") ||
                utilisateur.getPrenom().trim().equals("") ||
                utilisateur.getEmail().trim().equals("") ||
                utilisateur.getRue().trim().equals("") ||
                utilisateur.getCodePostal().trim().equals("") ||
                utilisateur.getVille().trim().equals("") ||
                utilisateur.getMotDePasse().trim().equals("")
        ){
            businessException.ajouterErreur(CodeErreurBLL.ERREUR_COORDONNEES_RETRAIT);
        }
    }

    /**
     * Retourne un utilisateur pour un pseudo donné
     * @param pseudo
     * @return
     * @throws BusinessException
     */
    public static Utilisateur selectUserByPseudo(String pseudo) throws BusinessException{
        return utilisateurDAO.getByPseudo(pseudo);
    }

    /**
     * Suprimme un utilisateur en fonction de son id
     * @param id
     * @return
     * @throws BusinessException
     */
    public static void deleteUser(int id) throws BusinessException{
        utilisateurDAO.delete(id);
    }

    /**
     * Retourne la liste de tous les pseudos
     * @return
     * @throws BusinessException
     */
    public static List<String> AllPseudoList()throws BusinessException{
        return utilisateurDAO.getAllPseudo();
    }

    /**
     * Retourne une liste de tous les e-mails
     * @return
     * @throws BusinessException
     */
    public static List<String> getAllMail() throws BusinessException{
        return utilisateurDAO.getAllMail();
    }

    /**
     * Retourne un utilisateur en fonction de son id
     * @param idUser
     * @return
     * @throws BusinessException
     */

    public static Utilisateur selectUserByID(int idUser) throws BusinessException {
        return utilisateurDAO.selectUserByID(idUser);
    }
}