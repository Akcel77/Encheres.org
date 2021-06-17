package org.enchere.dal;

import org.enchere.bo.Categorie;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.util.List;

public interface CategorieDAO {

    Categorie selectById(int noCategorie) throws BusinessException;
    List<Categorie> selectAll() throws BusinessException;
    void insert(Categorie c) throws BusinessException;
    void update(Categorie c) throws BusinessException;
    void delete(int id) throws BusinessException;
    List<Categorie> selectByLibelle() throws BusinessException;
    Categorie getNumCat(String libelle) throws BusinessException;

}
