package org.enchere.bll;

import org.enchere.bo.Enchere;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.EnchereDAO;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EnchereManager {

    public void createEnchere(Enchere enchere) throws BusinessException {
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
        enchereDAO.create(enchere);
    }


    public void updateEnchere(Enchere enchere) throws BusinessException{
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
        enchereDAO.update(enchere);
    }

    public void deleteEnchere(Enchere enchere) throws BusinessException{
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
        enchereDAO.delete(enchere);
    }

    public Enchere findEnchere (int no_enchere) throws BusinessException, SQLException {
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
        return  enchereDAO.find(no_enchere);
    }

    public List<Enchere> findAll() throws BusinessException, SQLException {
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
        return enchereDAO.findAll();
    }
}

