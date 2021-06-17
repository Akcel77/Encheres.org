package org.enchere.bll;

import org.enchere.bo.Categorie;
import org.enchere.dal.CategorieDAO;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.jdbc.CategorieImpl;
import org.enchere.outils.BusinessException;

import java.util.List;

public class CategorieManager {

    private static CategorieDAO categorieDAO = new CategorieImpl();
    private static Categorie categorie = new Categorie();

    public CategorieManager(){
        CategorieManager.categorieDAO = DAOFactory.getCategorieDAO();
    }

    public static Categorie getNumCat(String libelle) throws BusinessException{
       return categorieDAO.getNumCat(libelle);
    }
    /**
     * Insert
     * @param c
     * @throws BusinessException
     */
    public static void insert(Categorie c) throws BusinessException{
        categorieDAO.insert(c);
    }

    /**
     * Update
     * @param c
     * @throws BusinessException
     */

    public static void update(Categorie c) throws BusinessException{
        categorieDAO.update(c);
    }

    /**
     * Return a list of all Categories
     * @return
     * @throws BusinessException
     */

    public static List<Categorie> selectAll() throws BusinessException {
        return categorieDAO.selectAll();
    }

    /**
     * Return a list of Categories by ID
     * @param noCategorie
     * @return
     * @throws BusinessException
     */

    public static Categorie selectById(int noCategorie) throws BusinessException {
        return categorieDAO.selectById(noCategorie);
    }

    /**
     * Delete a list of Categories by ID
     * @param id
     * @throws BusinessException
     */

    public static void delete(int id) throws BusinessException {
        categorieDAO.delete(id);
    }

    public static List<Categorie> searchByLibelle() throws BusinessException{
        return categorieDAO.selectByLibelle();
    }

}
