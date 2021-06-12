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

    public static void createEnchere(Enchere enchere) throws BusinessException {
        enchereDAO.create(enchere);
    }


    public static void updateEnchere(Enchere enchere) throws BusinessException{
        enchereDAO.update(enchere);
    }

    public static void deleteEnchere(int no_enchere) throws BusinessException{
        enchereDAO.delete(no_enchere);
    }

    public static Enchere findEnchere (int no_enchere) throws BusinessException, SQLException {
        return  enchereDAO.find(no_enchere);
    }

    public static List<Enchere> findAll() throws BusinessException, SQLException {
        return enchereDAO.findAll();
    }

    public static List<Enchere> findAllByArticleId(int id) throws BusinessException, SQLException {
        return enchereDAO.findAllByArticleId(id);
    }

}

