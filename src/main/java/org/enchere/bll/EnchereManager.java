package org.enchere.bll;

import org.enchere.bo.Enchere;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.EnchereDAO;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EnchereManager {

    private static EnchereDAO enchereDAO;

    static{
        EnchereManager.enchereDAO = DAOFactory.getEnchereDAO();
    }

    /**
     * Crée une nouvelle enchère
     * @param enchere
     * @throws BusinessException
     */
    public static void createEnchere(Enchere enchere) throws BusinessException {
        enchereDAO.create(enchere);
    }

    /**
     * Retourne la liste de toutes les enchères pour un article donné
     * @param id
     * @return
     * @throws BusinessException
     * @throws SQLException
     */
    public static List<Enchere> findAllByArticleId(int id) throws BusinessException, SQLException {
        return enchereDAO.findAllByArticleId(id);
    }
}
