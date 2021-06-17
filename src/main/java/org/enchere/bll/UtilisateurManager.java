package org.enchere.bll;

import org.enchere.bo.Utilisateur;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.UtilisateurDAO;
import org.enchere.dal.jdbc.UtilisateurImpl;
import org.enchere.outils.BusinessException;

import java.util.List;


public class UtilisateurManager {


    //Attributs
    private static UtilisateurDAO utilisateurDAO ;
    private static Utilisateur utilisateur = new Utilisateur();
    private static BusinessException businessException = new BusinessException();
    private static UtilisateurManager instance = null;

    /**
     * Lien DAO
     */
    static {
        UtilisateurManager.utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public static UtilisateurManager getInstance() {
        if (instance == null)
            instance = new UtilisateurManager();
        return instance;
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

    public static boolean checkIfEmailOrPseudo(Utilisateur utilisateur)throws BusinessException{
        return utilisateurDAO.checkIfEmailOrPseudo(utilisateur);
    }



    /**
     * Conditions pour inscription Fonctionne avec SignInUser
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
            System.out.println("Erreur Valider coordonnees Inscription");
        }
    }

    /**
     * Update les infos User si les conditions sont acceptées cf updateInfo()
     * @param utilisateur
     * @throws BusinessException
     */
    public static void updateUser(Utilisateur utilisateur) throws BusinessException{
        updateInfo(utilisateur);
        if(!businessException.hasErreurs()){
            utilisateurDAO.update(utilisateur);
        }else{
            System.out.println("Erreur update User");
        }
    }

    /**
     * Conditions pour mise à jour des infos Fonctionne avec updateUser()
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
     * Récupère un user par son pseudo
     * @param pseudo
     * @return
     * @throws BusinessException
     */
    public static Utilisateur selectUserByPseudo(String pseudo) throws BusinessException{
        return utilisateurDAO.getByPseudo(pseudo);
    }


    /**
     * Select un User en fonction de son mail
     * @param email
     * @return
     * @throws BusinessException
     */
    public static Utilisateur selectUserByMail(String email)throws BusinessException{
        return utilisateurDAO.getByMail(email);
    }

    /**
     * Return une liste de tous les utilisateurs
     * @return
     * @throws BusinessException
     */
    public static List<Utilisateur> selectAllUser() throws BusinessException{
        return utilisateurDAO.getAllUser();
    }

    /**
     * Delete un utilisateur en fonction de son id
     * @param id
     * @return
     * @throws BusinessException
     */
    public static void deleteUser(int id) throws BusinessException{
        utilisateurDAO.delete(id);
        System.out.println("Utilisateur numero : "+ id + " Delete");
    }


    /**
     * Liste de tous les pseudos ( Utile pour vérifier si un pseudo est déjà utilisé)
     * @return
     * @throws BusinessException
     */
    public static List<String> AllPseudoList()throws BusinessException{
        return utilisateurDAO.getAllPseudo();
    }
    public static List<String> getAllMail() throws BusinessException{
        return utilisateurDAO.getAllMail();
    }

    public static Utilisateur selectUserByID(int idUser) throws BusinessException {
        return utilisateurDAO.selectUserByID(idUser);
    }

    public Utilisateur searchById(int id) throws BusinessException{
        System.out.println(id + "Manager");
        return utilisateurDAO.searchByID(id);
    }


}