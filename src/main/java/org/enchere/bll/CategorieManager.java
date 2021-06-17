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

    /**
     * Retourne une liste de toutes les catégories
     * @return
     * @throws BusinessException
     */
    public static List<Categorie> selectAll() throws BusinessException {
        return categorieDAO.selectAll();
    }

    /**
     * Retourne une catégorie selon son id
     * @param noCategorie
     * @return
     * @throws BusinessException
     */
    public static Categorie selectById(int noCategorie) throws BusinessException {
        return categorieDAO.selectById(noCategorie);
    }
}
