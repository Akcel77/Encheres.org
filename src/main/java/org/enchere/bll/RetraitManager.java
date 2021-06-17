package org.enchere.bll;

import org.enchere.bo.Retrait;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.RetraitDAO;
import org.enchere.dal.jdbc.RetraitImpl;
import org.enchere.outils.BusinessException;

public class RetraitManager {

    private static RetraitDAO retraitDAO;
    private static BusinessException businessException = new BusinessException();

    static{
        RetraitManager.retraitDAO = DAOFactory.getRetraitDAO();
    }

    public static Retrait addLieu(Retrait retrait) throws BusinessException{
        getValidAdress(retrait, businessException);

        if(!businessException.hasErreurs()){
            retraitDAO.insert(retrait);
        }else{
            throw businessException;
        }
        return retrait;
    }

    public static Retrait selectRetraitById(int id) throws BusinessException{
        return retraitDAO.getById(id);
    }

    public static void updateRetrait(Retrait retrait) throws BusinessException {
        getValidAdress(retrait, businessException);
        if (!businessException.hasErreurs()) {
            retraitDAO.update(retrait);
        }
    }

    public static void getValidAdress(Retrait retrait , BusinessException businessException){
        if(
                retrait.getRue().trim().equals("") ||
                retrait.getCode_postal().trim().equals("")||
                retrait.getVille().trim().equals("")
        ){
            businessException.ajouterErreur(CodeErreurBLL.ERREUR_COORDONNEES_RETRAIT);
            System.out.println("Erreur ajout adresse");
        }
    }

    public static void insert(Retrait retrait) throws BusinessException {
        retraitDAO.insert(retrait);
    }

    public static Retrait selectRetraitByArticleId(int idArticle) throws BusinessException {
        return retraitDAO.selectRetraitByArticleId(idArticle);
    }
}
