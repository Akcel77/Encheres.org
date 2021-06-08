package org.enchere.dal.jdbc;

import org.enchere.bo.Categorie;
import org.enchere.dal.CategorieDAO;
import org.enchere.outils.BusinessException;

import java.util.List;

public class CategorieImpl implements CategorieDAO {

    private final String INSERT_CATEGORIE = "INSERT INTO categories(libelle) VALUES(?)";
    private final String SELECT_ALL = "SELECT * FROM categories";
    private final String SELECT_CATEGORIE = "SELECT * FROM categories WHERE id=?";
    private final String UPDATE_LIBELLE = "UPDATE categories SET libelle=? WHERE id=?";


    @Override
    public Categorie getById(int noCategorie) throws BusinessException {
        return null;
    }

    @Override
    public List<Categorie> getAll() throws BusinessException {
        return null;
    }

    @Override
    public void insert(Categorie categorie) throws BusinessException {

    }

    @Override
    public void update(Categorie categorie) throws BusinessException {

    }

    @Override
    public void delete(int noCategorie) throws BusinessException {

    }
}