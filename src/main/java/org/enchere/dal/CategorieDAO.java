package org.enchere.dal;

import org.enchere.bo.Categorie;
import org.enchere.outils.BusinessException;

import java.util.List;

public interface CategorieDAO {

    Categorie getById(int noCategorie) throws BusinessException;

   List<Categorie> getAll() throws BusinessException;

    void insert(Categorie categorie) throws BusinessException;

    void update(Categorie categorie) throws BusinessException;

    void delete(int noCategorie) throws BusinessException;


}
