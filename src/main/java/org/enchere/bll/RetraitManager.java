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

    /**
     * Insère un retrait
     * @param retrait
     * @throws BusinessException
     */
    public static void insert(Retrait retrait) throws BusinessException {
        retraitDAO.insert(retrait);
    }

    /**
     *  Retourne un retrait pour un numéro d'article donné
     * @param idArticle
     * @return
     * @throws BusinessException
     */
    public static Retrait selectRetraitByArticleId(int idArticle) throws BusinessException {
        return retraitDAO.selectRetraitByArticleId(idArticle);
    }
}
