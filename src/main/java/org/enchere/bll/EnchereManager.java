package org.enchere.bll;

import org.enchere.bo.Enchere;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.EnchereDAO;
import org.enchere.outils.BusinessException;

import java.util.ArrayList;


public class EnchereManager {

    public void createEnchere(Enchere enchere) throws BusinessException {
        EnchereDAO enchereDAO = DAOFactory.createEnchereDAO();
        enchereDAO.create(enchere);
    }

/*
    public void updateEnchere(Enchere enchere) throws BusinessException{
        //TODO
    }

    public void deleteEnchere(Enchere enchere) throws BusinessException{
        //TODO
    }


    public Enchere findEnchere (Enchere enchere) throws BusinessException{
        //TODO
    }


    public ArrayList<Enchere> findAll() throws BusinessException{
        //TODO
    }
 */
}
